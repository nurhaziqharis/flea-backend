How to run?
source .env && ./mvnw spring-boot:run

To run with debugger
source .env && ./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005"

To kill debugger
lsof -ti tcp:5005 | xargs kill

To kill server
lsof -ti:8080 | xargs kill -9