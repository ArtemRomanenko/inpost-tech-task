FROM maven:3.8.8-eclipse-temurin-21-alpine

WORKDIR /app

# Copy only the pom.xml first to leverage caching
COPY pom.xml .

# Pre-fetch all Maven dependencies (go-offline)
RUN mvn dependency:go-offline

# Now copy the rest of the source code
COPY src ./src

ARG ENV="test"
ARG TAGS="@UI or @API"
ENV ENV=${ENV}
ENV TAGS=${TAGS}

CMD sh -c "mvn test -Denv=${ENV} -Dremote=true -Dcucumber.filter.tags=\"${TAGS}\" && \
           cp -r target/lockers /lockers && \
           mvn allure:report"
