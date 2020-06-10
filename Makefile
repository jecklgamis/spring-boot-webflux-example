default:
	cat ./Makefile
dist:
	./mvnw clean package
image:
	docker build -t spring-boot-webflux-template:latest .
run:
	docker run -p 8080:8080  -p 8443:8443 spring-boot-webflux-template:latest
run-bash:
	docker run -i -t spring-boot-webflux-template:latest /bin/bash
up: dist image run
