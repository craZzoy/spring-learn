package com.segfault;

import org.springframework.web.client.RestTemplate;

/**
 * Created by zwz on 2019/9/10.
 */
public class RestTempleTest {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 10000; i++) {
            restTemplate.getForObject("http://localhost:8080/single", String.class);
        }
    }
}
