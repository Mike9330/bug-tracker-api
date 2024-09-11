package michaelmdonato.gohealth_bug_tracker.servicetests;

import michaelmdonato.gohealth_bug_tracker.Bug;
import michaelmdonato.gohealth_bug_tracker.services.CSVService;
import michaelmdonato.gohealth_bug_tracker.services.TrackerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackerServiceTests {

    @Mock
    private CSVService csvService;

    @InjectMocks
    private TrackerService service;


    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addBugWorks() throws IOException {
        String desc = "test desc";
        String link = "test link";

        service.addBug(desc, link);
        verify(csvService).addBug(any(Bug.class), eq("bugtracker.csv"));

    }
}
