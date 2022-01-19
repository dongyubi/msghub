package com.uplus.msghub.common;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

public class HttpClientUtil {
	public static HttpResponse<String> get(String uri) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create(uri))
              .GET()
              .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        System.out.println("get response==>"+response);
        System.out.println("get response.body()==>"+response.body());
        return response;
    }
    
	public static HttpResponse<String> post(String uri, String data) throws Exception {
		System.out.println("post request uri==>"+uri);
	    System.out.println("post request data==>"+data);
	    
		HttpClient client = HttpClient.newBuilder().build();
	    HttpRequest request = HttpRequest.newBuilder()
	    		.uri(URI.create(uri))
	    		.header("accept", "*/*")
			    .header("Content-Type", "application/json")
	            .POST(BodyPublishers.ofString(data))
	            .build();
	    
	    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
	    
	    System.out.println("post response==>"+response);
	    System.out.println("post response.body()==>"+response.body());
	    return response;
}
	 
    public static HttpResponse<String> post(String uri, String data, String auth) throws Exception {
    	System.out.println("post request uri==>"+uri);
	    System.out.println("post request data==>"+data);
	    System.out.println("post request auth==>"+auth);
	    
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("accept", "*/*")
    		    .header("Content-Type", "application/json")
    		    .header("Authorization", "Bearer " + auth)
                .POST(BodyPublishers.ofString(data))
                .build();

        
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        
        System.out.println("post response==>"+response);
	    System.out.println("post response.body()==>"+response.body());
		return response;
    }
    
    public static HttpResponse<String> put(String uri, String data) throws Exception {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("accept", "*/*")
    		    .header("Content-Type", "application/json")
                .PUT(BodyPublishers.ofString(data))
                .build();

        
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        
        System.out.println("put response==>"+response);
        System.out.println("put response.body()==>"+response.body());
		return response;
    }
    
    public static HttpResponse<String> delete(String uri) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create(uri))
              .DELETE()
              .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        System.out.println("get response==>"+response);
        System.out.println("get response.body()==>"+response.body());
        return response;
    }
}
