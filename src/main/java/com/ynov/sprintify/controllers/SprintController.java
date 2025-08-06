package com.ynov.sprintify.controllers;

import com.ynov.sprintify.dto.SprintDTO;
import com.ynov.sprintify.payloads.SprintCreationPayload;
import com.ynov.sprintify.services.SprintService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@RestController
@RequestMapping("/api/sprints")
public class SprintController {

    private final SprintService sprintService;

    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @PostMapping("/{name}/add")
    public ResponseEntity<SprintDTO> addSprintToProject(@PathVariable String name, @RequestBody SprintCreationPayload sprintPayload) throws UnsupportedEncodingException {

        String decodedName = URLDecoder.decode(name, "UTF-8");

        return ResponseEntity.ok(sprintService.createSprintToAProject(sprintPayload, decodedName));
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<SprintDTO>> getSprintsForAProject(@PathVariable String name) {
        return ResponseEntity.ok(sprintService.getSprintsForAProject(name));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteSprint(@PathVariable String name) {
        sprintService.deleteSprint(name);

        return ResponseEntity.ok().build();
    }



}
