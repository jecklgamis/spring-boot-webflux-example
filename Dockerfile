FROM ubuntu:24.04
LABEL org.opencontainers.image.authors="jecklgamis@gmail.com"

RUN apt update -y && apt install -y openjdk-21-jre-headless && rm -rf /var/lib/apt/lists/*

ENV APP_HOME=/app

RUN groupadd -r app && useradd -r -gapp app
RUN mkdir -m 0755 -p ${APP_HOME}/bin
RUN mkdir -m 0755 -p ${APP_HOME}/config
RUN mkdir -m 0755 -p ${APP_HOME}/logs/

COPY target/spring-boot-webflux-example.jar ${APP_HOME}/bin
COPY docker-entrypoint.sh /

RUN chown -R app:app ${APP_HOME}
RUN chmod +x /docker-entrypoint.sh

EXPOSE 8080
EXPOSE 8443

WORKDIR ${APP_HOME}
CMD ["/docker-entrypoint.sh"]

