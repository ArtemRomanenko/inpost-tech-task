FROM maven:3.8.8-eclipse-temurin-21-alpine

WORKDIR /app

COPY pom.xml .
COPY src ./src

ARG ENV="test"
ARG TAGS="@UI or @API"
ENV ENV=${ENV}
ENV TAGS=${TAGS}

CMD sh -c "mvn test -Denv=${ENV} -Dremote=true -Dcucumber.filter.tags=\"${TAGS}\"; \
           cp -r target/lockers /lockers; \
           mvn allure:report"