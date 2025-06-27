package tech.inovasoft.inevolving.ms.objectives.integration;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.net.URISyntaxException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskServiceClientTest {

    private final String baseUrl;

    public TaskServiceClientTest(@Value("${inevolving.uri.ms.task}") String fullUrl) throws URISyntaxException {
        URI uri = new URI(fullUrl);
        this.baseUrl = uri.getScheme() + "://" + uri.getHost() + ":" + uri.getPort();
    }

    @Test
    public void integrationTest_Ok() {

        RequestSpecification requestSpecification = given()
                .contentType(ContentType.JSON);

        ValidatableResponse response = requestSpecification
                .when()
                .get(baseUrl + "/actuator/health")
                .then();

        response.assertThat().statusCode(200).and()
                .body("status", equalTo("UP"));

    }

}
