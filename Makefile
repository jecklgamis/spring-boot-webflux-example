IMAGE_NAME:=spring-boot-webflux-example
IMAGE_TAG:=$(shell git rev-parse --abbrev-ref HEAD)

default:
	cat ./Makefile
dist: keystore
	./mvnw clean package
image:
	docker build -t $(IMAGE_NAME):$(IMAGE_TAG) .
run:
	docker run -p 8080:8080  -p 8443:8443 $(IMAGE_NAME):$(IMAGE_TAG)
run-bash:
	docker run -i -t $(IMAGE_NAME):$(IMAGE_TAG) /bin/bash
keystore:
	@./generate-keystore.sh
all: dist image
up: all run

