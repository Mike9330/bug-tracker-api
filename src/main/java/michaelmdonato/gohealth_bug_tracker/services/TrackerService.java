package michaelmdonato.gohealth_bug_tracker.services;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

@Service
public class TrackerService {

    private long nextId = 1L;

    public void newBug(String description, String link) throws IOException {
        Bug bug = new Bug(description, link);

        bug.setId(String.valueOf(nextId++));
        long currentTimeInMillis = System.currentTimeMillis();

        Date currentDate = new Date(currentTimeInMillis);
        bug.setTimestamp(currentDate);

        PrintWriter out = new PrintWriter(new FileWriter("bugtracker.csv", true));
        bug.setStatus("active");

        System.out.println("bug.getLink ===> " + bug.getLink());

        out.printf("%s, %s, %s, %s, %s\n", bug.getId(), bug.getDescription(), bug.getStatus(), bug.getTimestamp(), bug.getLink());

        out.close();
    }
}

