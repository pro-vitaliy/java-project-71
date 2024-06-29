run-dist:
	./build/install/app/bin/app $(ARGS)

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain
check-deps:

	./gradlew dependencyUpdates -Drevision=release

.PHONY: build