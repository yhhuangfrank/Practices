package com.frank.mytest.service;

import com.frank.mytest.dto.TestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class HttpService2 {

    private final WebClient webClient;

    public void callApi() {
        System.out.println(Thread.currentThread().getName() + " web client is " + webClient);
        ResponseEntity<TestResponse> responseEntity = webClient
                .get()
                .uri("https://jsonplaceholder.typicode.com/posts/1")
//                .bodyValue(BodyInserters.fromValue(testDto))
                .retrieve()
                .onStatus((HttpStatusCode::is4xxClientError), res -> Mono.empty())
                .onStatus(HttpStatusCode::is5xxServerError, res -> Mono.empty())
                .toEntity(TestResponse.class)
                .block();
        System.out.println(Thread.currentThread().getName() + " response code " + responseEntity.getBody());
        if (responseEntity != null) {
            System.out.println(Thread.currentThread().getName() + " response body is " + responseEntity.getBody());
        }
    }
}
