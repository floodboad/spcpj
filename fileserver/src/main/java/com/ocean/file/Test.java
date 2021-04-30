package com.ocean.file;

import com.ocean.G;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Test {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<G> res = restTemplate.exchange("http://127.0.0.1:8002/tttt", HttpMethod.DELETE,null,G.class);
        System.out.println("res.getBody()============");
        System.out.println(res.getBody());

    }
}
