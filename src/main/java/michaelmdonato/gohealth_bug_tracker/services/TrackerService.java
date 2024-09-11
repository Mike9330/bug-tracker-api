package michaelmdonato.gohealth_bug_tracker.services;
import michaelmdonato.gohealth_bug_tracker.Bug;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Date;
import java.util.Scanner;

@Service
public class TrackerService {

    private final CSVService writer;

    public long nextId = 1L;

    private final String fileName = "bugtracker.csv";

    public TrackerService(CSVService writer) {
        this.writer = writer;
    }

    public void addBug(String description, String link) throws IOException {
        Bug bug = new Bug(description, link);

        bug.setId(String.valueOf(nextId++));
        long currentTimeInMillis = System.currentTimeMillis();

        Date currentDate = new Date(currentTimeInMillis);
        bug.setTimestamp(currentDate);

        this.writer.addBug(bug, fileName);
    }

    public void closeBug(String idToChange) throws IOException {
        String tempFile = "temp.csv";
        File oldFile = new File(fileName);
        File newFile = new File(tempFile);
        String id; String desc; String status; String timeStamp; String link;

        try {
            FileWriter writer = new FileWriter(tempFile,true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            PrintWriter out = new PrintWriter(bufferedWriter);
            Scanner scanner = new Scanner(new File(fileName));
            scanner.useDelimiter(("[,\n]"));

            while(scanner.hasNext()) {
                id = scanner.next();
                desc = scanner.next();
                status = scanner.next();
                timeStamp = scanner.next();
                link = scanner.next();

                if (id.equals(idToChange)) {
                    out.printf("%s, %s, %s, %s, %s\n", id, desc, "closed", timeStamp, link);
                }
                else {
                    out.printf("%s, %s, %s, %s, %s\n", id, desc, status, timeStamp, link);
                }
            }
            scanner.close();
            out.flush();
            out.close();
            oldFile.delete();
            File dump = new File(fileName);
            newFile.renameTo(dump);

        } catch (Exception e) {
            System.out.println("Error updating bug" + idToChange);
        }
    }
}

