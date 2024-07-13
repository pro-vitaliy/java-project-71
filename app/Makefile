setup:
	./gradlew wrapper --gradle-version 8.5

clean:
	./gradlew clean

install:
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

.PHONY: build test