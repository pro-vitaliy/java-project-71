.DEFAULT_GOAL := install-run

setup:
	./gradlew wrapper --gradle-version 8.5

clean:
	./gradlew clean

install-dist:
	./gradlew installDist

run-dist:
	./build/install/app/bin/app $(args)

build:
	./gradlew clean build

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain

check-deps:
	./gradlew dependencyUpdates -Drevision=release

install-run: install-dist run-dist
.PHONY: build test