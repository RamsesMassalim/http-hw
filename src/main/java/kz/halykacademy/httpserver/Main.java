package kz.halykacademy.httpserver;

import org.json.simple.JSONObject;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        newEmployee();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://dummy.restapiexample.com/api/v1/create"))
                .headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofFile(Paths.get("file.json")))
                .build();

        System.out.println(request);

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    private static void newEmployee() throws IOException {
        Scanner scanner = new Scanner(System.in);

        String name = scanner.next();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);

        FileWriter file = new FileWriter("file.json");
        file.write(jsonObject.toJSONString());
        file.close();
    }
}
