package com.frank.mytest.service;

import com.frank.mytest.dto.TestRequest;
import com.frank.mytest.dto.TestResponse;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class HttpService1 {

    private final WebClient webClient;
    private final Gson gson;

    public void callApi() {
        WebClient webClient1 = webClient.mutate()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        System.out.println(Thread.currentThread().getName() + " web client is " + webClient);
        System.out.println(Thread.currentThread().getName() + " web client is " + webClient1);
        TestRequest testRequest = new TestRequest("myTitle", "write something", 77);
        webClient1
                .post()
                .uri("https://jsonplaceholder.typicode.com/posts")
                .bodyValue(BodyInserters.fromValue(gson.toJson(testRequest)))
                .retrieve()
                .onStatus((HttpStatusCode::is4xxClientError), res -> Mono.empty())
                .onStatus(HttpStatusCode::is5xxServerError, res -> Mono.empty())
                .toEntity(TestResponse.class)
                // 異步請求
                .subscribe(
                        // 成功時處理
                        responseEntity -> {
                            int statusCode = responseEntity.getStatusCode().value();
                            Object responseBody = responseEntity.getBody();
                            log.info(
                                Thread.currentThread().getName() + " status code is " + statusCode
                            );
//                            log.debug("write audit log success, httpStatusCode: {}, response body: {}", statusCode, responseBody);
                        },
                        // 錯誤時處理
                        error -> {
                            if (error instanceof WebClientResponseException exception) {
                                int statusCode = exception.getStatusCode().value();
                                log.error("write audit log error, httpStatusCode: {}, error message: {}", statusCode, exception.getMessage());
                            } else {
                                log.error("write audit log error, unexpected exception happened, error message: {}", error.getMessage());
                            }
                        }
                );
//                .block();
//        if (responseEntity != null) {
//            System.out.println(Thread.currentThread().getName() + " status code is " + responseEntity.getStatusCode().value());
//        }
        System.out.println("print line");
    }
}
