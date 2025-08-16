package com.ynov.sprintify.controllers;

import com.ynov.sprintify.dto.sprint.SprintOverviewDTO;
import com.ynov.sprintify.payloads.SprintCreationPayload;
import com.ynov.sprintify.services.SprintService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api/sprints")
public class SprintController {

    private final SprintService sprintService;

    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @PostMapping("/{name}/add")
    public ResponseEntity<SprintOverviewDTO> addSprintToProject(@PathVariable String name, @RequestBody SprintCreationPayload sprintPayload) {

        String decodedName = URLDecoder.decode(name, StandardCharsets.UTF_8);

        return ResponseEntity.ok(sprintService.createSprintToAProject(sprintPayload, decodedName));
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<SprintOverviewDTO>> getSprintsForAProject(@PathVariable String name) {

        String decodedName = URLDecoder.decode(name, StandardCharsets.UTF_8);


        return ResponseEntity.ok(sprintService.getSprintsForAProject(decodedName));
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deleteSprint(@PathVariable String name) {
        String decodedName = URLDecoder.decode(name, StandardCharsets.UTF_8);

        sprintService.deleteSprint(decodedName);

        return ResponseEntity.ok().build();
    }



}
