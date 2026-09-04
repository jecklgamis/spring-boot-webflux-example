package spring.boot.webflux.template;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ExampleWebClient {
    private WebClient client = WebClient.create("http://localhost:8080");

    private Mono<String> result = client.get()
            .uri("/")
            .accept(MediaType.APPLICATION_JSON)
            .exchangeToMono(res -> res.bodyToMono(String.class));

    public String getResult() {
        return ">> result = " + result.block();
    }

    public static void main(String[] args) {
        System.out.println(new ExampleWebClient().getResult());
    }
}