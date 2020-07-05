package spring.boot.webflux.template.service;

import reactor.core.publisher.Mono;

import java.util.Optional;

public interface KeyValueStoreService {
    Mono<Void> store(String key, String value);

    Mono<Optional<String>> retrieve(String key);

    Mono<Void> delete(String key);
}

