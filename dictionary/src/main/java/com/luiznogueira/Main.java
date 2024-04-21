package com.luiznogueira;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Objects;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    String apiKey = Objects.requireNonNull(System.getenv("KEY"));
    String url = Objects.requireNonNull(System.getenv("URL"));
    Scanner sc = new Scanner(System.in);
    System.out.println("Type desired word:");
    String input = sc.nextLine();
    String requestUrl = buildUrl(url, input, apiKey);
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI(requestUrl))
          .GET()
          .build();
      HttpClient client = HttpClient.newHttpClient();
      HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    } catch (URISyntaxException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      sc.close();
    }
  }

  private static String buildUrl(String url, String input, String apiKey) {
    return url + input + "?key=" + apiKey;
  }
}