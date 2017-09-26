package main;

import java.io.InputStream;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class Test {	
	
	public static String testPost(String link, String para, String target) {
		try {
			
			HttpClient httpClient = HttpClientBuilder.create().build();
		    HttpPost request = new HttpPost(link);
		    StringEntity params =new StringEntity(para + "=" + target);
		    request.addHeader("content-type", "application/x-www-form-urlencoded");
		    request.setEntity(params);
		    HttpResponse response = httpClient.execute(request);
		    InputStream stream = response.getEntity().getContent();
		    Scanner sc = new Scanner(stream);
		    return sc.nextLine();
		
		}catch (Exception e) {		
		}
		return null;
	}

}
