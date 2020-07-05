package spring.boot.webflux.template.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryKeyValueStoreServiceImpl implements KeyValueStoreService {
    private Map<String, String> store = new ConcurrentHashMap<>();

    @Override
    public Mono<Void> store(String key, String value) {
        if (!store.containsKey(key)) {
            store.put(key, value);
        }
        return Mono.create(MonoSink::success);
    }

    @Override
    public Mono<Optional<String>> retrieve(String key) {
        if (store.containsKey(key)) {
            return Mono.just(Optional.of(store.get(key)));
        }
        return Mono.just(Optional.empty());
    }

    @Override
    public Mono<Void> delete(String key) {
        store.remove(key);
        return Mono.create(MonoSink::success);
    }
}
