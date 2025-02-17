import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Scanner;
import java.net.URI;

public class JavaCLI {
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private static void addbug() throws IOException, InterruptedException {
        Scanner reader = new Scanner(System.in);
        System.out.print("In a few words, please describe your issue: ");
        String description = reader.nextLine();
        description = description.replaceAll(" ", "%20");

        System.out.print("Please provide a link to where your issue can be seen: ");
        String link = reader.nextLine();
        String baseURL = "http://localhost:8080/addBug/";
        String fullURL = baseURL.concat(description).concat("/").concat(link);


        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(fullURL))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println("""
        
                        
                        """ + response.body() + """
                        
                        Someone will be in touch shortly to follow up on your issue


                        """);
        } else {
            System.out.println("""
        
                        
                        
                        There was an issue creating your bug


                        """);
        }



    }

    public static void closeBug(String id) throws IOException, InterruptedException {
        String baseURL = "http://localhost:8080/closeBug/";
        String fullURL = baseURL.concat(id);


        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(fullURL))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println("""
        
                        
                        """ + response.body() + """
                        
                        Thank you for using the GoHealth Bug Tracking system


                        """);
        } else {
            System.out.println("""
        
                        
                        
                        There was an issue creating your bug


                        """);
            }


    }

    public static void main(String args[]) throws IOException, InterruptedException {
        System.out.println("Hello! And welcome to the GoHealth Bug-Tracking CLI");
        Scanner reader = new Scanner(System.in);
        String response = "";


        while (!response.equals("exit")) {
            System.out.print("Please enter 'new' to create new bug or 'close' to close an existing one (or exit to quit CLI): ");
            response = reader.next();

            if (response.equals("new")) {
                addbug();
            } else if (response.equals("close")) {
                System.out.print("Please enter the ID of the bug you'd like to close: ");
                response = reader.next();
                closeBug(response);

            } else if (!response.equals("exit")) {
                System.out.println("""



                        Invalid input - Please enter 'new' or 'update'


                        """);
            }
        }

        System.out.println("Thank you for using the GoHealth Bug Tracker!");
    }
}
