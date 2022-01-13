package com.uplus.msghub.common;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

public class HttpClientUtil {
	public static void get(String uri) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create(uri))
              .build();

        HttpResponse<String> response =
              client.send(request, BodyHandlers.ofString());

        System.out.println("get response==>"+response);
        System.out.println("get response.body()==>"+response.body());
    }
    
    public static HttpResponse<String> post(String uri, String data) throws Exception {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("accept", "*/*")
    		    .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(data))
                .build();

        
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        
        System.out.println("post response"+response);
        System.out.println("post response.body()"+response.body());
		return response;
    }
}
