package michaelmdonato.gohealth_bug_tracker.controllertests;

import michaelmdonato.gohealth_bug_tracker.controllers.TrackerController;
import michaelmdonato.gohealth_bug_tracker.services.TrackerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackerControllerTests {
    @Mock
    private TrackerService service;

    @InjectMocks
    private TrackerController controller;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addBugWorks() throws IOException {
        doNothing().when(service).addBug(any(), any());

        ResponseEntity<String> result = controller.addBug("Description goes here", "www.google.com");
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Bug added successfully", result.getBody());
    }

    @Test
    void addBugFails() throws Exception {
        IOException ioException = new IOException("Failed to add bug");

        doThrow(ioException).when(service).addBug(anyString(), anyString());

        ResponseEntity<String> result = controller.addBug(anyString(), anyString());
        assertEquals("Error adding: Failed to add bug", result.getBody());
    }



    @Test
    void closeBugWorks() throws IOException {
        doNothing().when(service).closeBug(any());

        ResponseEntity<String> result = controller.closeBug("1");
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Bug has been closed", result.getBody());
    }

}