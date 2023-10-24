package com.devsuperior.demo.controller;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService service;

    @GetMapping
    public ResponseEntity<Page<EventDTO>> findAllEvents(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllCities(pageable));
    }

    @PreAuthorize("hasAnyRole('ROLE_CLIENT','ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<EventDTO> insert(@Valid @RequestBody EventDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(dto));
    }
}
