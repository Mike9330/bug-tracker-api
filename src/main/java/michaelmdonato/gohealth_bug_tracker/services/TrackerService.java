package michaelmdonato.gohealth_bug_tracker.services;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

@Service
public class TrackerService {

    private long nextId = 1L;

    private String fileName = "bugtracker.csv";

    public void newBug(String description, String link) throws IOException {
        Bug bug = new Bug(description, link);

        bug.setId(String.valueOf(nextId++));
        long currentTimeInMillis = System.currentTimeMillis();

        Date currentDate = new Date(currentTimeInMillis);
        bug.setTimestamp(currentDate);

        PrintWriter out = new PrintWriter(new FileWriter(fileName, true));
        bug.setStatus("open");

        out.printf("%s, %s, %s, %s, %s\n", bug.getId(), bug.getDescription(), bug.getStatus(), bug.getTimestamp(), bug.getLink());

        out.close();
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

