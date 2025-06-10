package tech.inovasoft.inevolving.ms.objectives.integration;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskServiceClientTest {

    @Test
    public void integrationTest_Ok() {

        RequestSpecification requestSpecification = given()
                .contentType(ContentType.JSON);

        ValidatableResponse response = requestSpecification
                .when()
                .get("http://localhost:8085/actuator/health")
                .then();

        response.assertThat().statusCode(200).and()
                .body("status", equalTo("UP"));

    }

}
