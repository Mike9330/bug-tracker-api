package michaelmdonato.gohealth_bug_tracker.controllers;

import michaelmdonato.gohealth_bug_tracker.services.TrackerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class TrackerController {
    private final TrackerService service;

    public TrackerController(TrackerService service) {
        this.service = service;
    }

    @RequestMapping("/addBug/{description}/{link}")
    public ResponseEntity<String> addBug(@PathVariable String description, @PathVariable String link) {
        try {
            service.newBug(description, link);
            return ResponseEntity.ok("Bug added successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error adding: " + e.getMessage());
        }
    }
}
