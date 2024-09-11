package michaelmdonato.gohealth_bug_tracker.services;

import michaelmdonato.gohealth_bug_tracker.Bug;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Scanner;

@Service
public class CSVService {

    public void addBug(Bug bug, String fileName) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(fileName, true));
        bug.setStatus("open");

        out.printf("%s, %s, %s, %s, %s\n", bug.getId(), bug.getDescription(), bug.getStatus(), bug.getTimestamp(), bug.getLink());

        out.close();
    }
}
