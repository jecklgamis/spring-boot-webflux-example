package spring.boot.webflux.template.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.util.UUID;

import static java.lang.String.format;

@Component
public class FileUploadHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadHandler.class);

    public Mono<ServerResponse> upload(ServerRequest request) {
        final Flux<Part> parts = request.bodyToFlux(Part.class);
        final Mono<Boolean> result = parts.onErrorMap(t -> {
            LOGGER.error(t.getMessage(), t);
            return t;
        }).flatMap(part -> {
            if (part instanceof FilePart) {
                return store((FilePart) part).map(f -> true);
            } else {
                return Mono.just(true);
            }
        }).all(r -> r);
        return result.flatMap(r -> ServerResponse.ok().build());
    }

    private Mono<File> store(FilePart part) {
        final File file = new File(UUID.randomUUID().toString().substring(0, 8) + "-" + part.filename());
        return part.transferTo(file).doOnNext(t -> LOGGER.info(format("File %s stored on %s",
                part.filename(), file.getAbsolutePath()))).flatMap(t -> Mono.just(file));
    }
}


