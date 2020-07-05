package spring.boot.webflux.template.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import spring.boot.webflux.template.service.KeyValueStoreService;

@Component
public class KeyValueStoreHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadHandler.class);
    private KeyValueStoreService service;

    @Autowired
    public KeyValueStoreHandler(KeyValueStoreService service) {
        this.service = service;
    }

    public Mono<ServerResponse> store(ServerRequest request) {
        final String key = request.pathVariable("key");
        final Mono<String> valueMono = request.bodyToMono(String.class);
        return valueMono
                .flatMap(v -> service.store(key, v))
                .doOnError(t -> LOGGER.error(t.getMessage(), t))
                .flatMap(v -> ServerResponse.ok().build());
    }

    public Mono<ServerResponse> retrieve(ServerRequest request) {
        final String key = request.pathVariable("key");
        return service.retrieve(key)
                .doOnError(t -> LOGGER.error(t.getMessage(), t))
                .flatMap(v -> {
                    if (v.isPresent())
                        return ServerResponse.ok().body(BodyInserters.fromValue(v));
                    return ServerResponse.notFound().build();

                });
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        final String key = request.pathVariable("key");
        return service.delete(key)
                .flatMap(v -> ServerResponse.ok().build());
    }

}



