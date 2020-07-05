package spring.boot.webflux.template.handler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class KeyValueStoreHandlerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testStoreAndRetrieve() {
        final String key = "someKey";
        webTestClient.post().uri("/kv/" + key)
                .contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("someValue"))
                .exchange()
                .expectStatus().isOk();
        webTestClient.get().uri("/kv/" + key)
                .exchange()
                .expectStatus().isOk();
}

    @Test
    public void testStoreAndDelete() {
        final String key = "someKey";
        webTestClient.post().uri("/kv/" + key)
                .contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("someValue"))
                .exchange()
                .expectStatus().isOk();
        webTestClient.delete().uri("/kv/" + key)
                .exchange()
                .expectStatus().isOk();
        webTestClient.get().uri("/kv/" + key)
                .exchange()
                .expectStatus().isNotFound();
    }

}