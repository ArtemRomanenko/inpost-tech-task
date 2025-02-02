# InPost Parcel Status Check Test Automation

Test automation framework for verifying InPost parcel and lockers via UI and API.
## 🛠 Technologies
- **Java 21** - Core programming language
- **Selenium** - Browser automation for UI tests
- **RestAssured** - API testing library
- **Cucumber** - BDD test framework
- **Maven** - Dependency management and build tool
- **JUnit 5** - Test runner and assertions
- **Docker** - Containerized test execution
- **Allure** - Test reporting framework

## 🚀 Running Tests

### Local Execution

To run the tests locally, update the property file (env-test.properties or env-prod.properties) and set:

remote=false

This ensures the tests run on your local machine instead of a Selenium Grid.

```bash
# Run UI tests and generate Allure report
mvn clean test -Denv=test -Dcucumber.filter.tags='@UI' allure:serve

# Run API tests and generate Allure report
mvn clean test -Denv=test -Dcucumber.filter.tags='@API' allure:serve

# Run ALL tests and generate Allure report
mvn clean test allure:serve
```

### Docker Execution
```bash
# Run UI tests in Docker
ENV=test TAGS='@UI' docker compose -f docker-compose.ui.yml up --build

# Run API tests in Docker
ENV=test TAGS='@API' docker compose -f docker-compose.api.yml up --build

# Run ALL tests in Docker
ENV=test TAGS='@UI or @API' docker compose -f docker-compose.ui.yml up --build
```

### Project Structure
```
.
├── src/test/java
│   ├── step_definitions     # Cucumber step definitions
│   └── runners              # Test runners
├── src/test/resources
│   ├── features             # Cucumber feature files
│   └── config               # Environment configuration
├── dockerfile               # Docker configuration
├── docker-compose.ui.yml    # Docker Compose for UI tests (with Selenium Grid)
└── docker-compose.api.yml   # Docker Compose for API tests (without Selenium Grid)

```