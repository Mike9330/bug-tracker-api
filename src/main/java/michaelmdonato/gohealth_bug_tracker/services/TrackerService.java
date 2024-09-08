package michaelmdonato.gohealth_bug_tracker.services;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Scanner;

@Service
public class TrackerService {

    private long nextId = 1L;

    public void newBug(String description, String link) throws IOException {
        Bug bug = new Bug(description, link);

        bug.setId(nextId++);
        long currentTimeInMillis = System.currentTimeMillis();

        Date currentDate = new Date(currentTimeInMillis);
        bug.setTimestamp(currentDate);

        PrintWriter out = new PrintWriter(new FileWriter("bugtracker.csv", true));
        bug.setStatus("active");

        System.out.println("bug.getLink ===> " + bug.getLink());

        out.printf("%s, %s, %s, %s, %s\n", bug.getId(), bug.getDescription(), bug.getStatus(), bug.getTimestamp(), bug.getLink());

        out.close();
    }

    public void updateBug(Bug bug) {
        String filePath = "bugtracker.csv";
        String searchId = bug.getId();

        boolean found = false;
        String Id = "";
        String name1 = "";

        try {
            Scanner x = new Scanner(new File(filePath));
            x.useDelimiter("[,\n]");

            while (x.hasNext() && !found) {
                Id = x.next();
                name1 = x.next();

                if (Id.equals(searchId)) {
                    found = true;
                }

            }

        }
        catch (Exception e) {

        }

    }

    public void getAllBugs() {

    }
}

