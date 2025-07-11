package tech.inovasoft.inevolving.ms.objectives.api;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tech.inovasoft.inevolving.ms.objectives.domain.dto.request.RequestCreateObjectiveDTO;
import tech.inovasoft.inevolving.ms.objectives.domain.model.Objective;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ObjectivesControllerTest {

    @LocalServerPort
    private int port;

    private static final UUID idUser = UUID.randomUUID();

    @Test
    public void add_ok() {
        RequestSpecification requestSpecification = given()
                .contentType(ContentType.JSON);

        ValidatableResponse response = requestSpecification
                .body(new RequestCreateObjectiveDTO(
                        "Name Objective",
                        "Description Objective",
                        idUser
                ))
                .when()
                .post("http://localhost:" + port + "/ms/objectives")
                .then();

        response.assertThat().statusCode(200).and()
                .body("id", notNullValue());
    }

    private UUID add(UUID idUser) {
        RequestSpecification requestSpecification = given()
                .contentType(ContentType.JSON);

        ValidatableResponse response = requestSpecification
                .body(new RequestCreateObjectiveDTO(
                        "Name Objective",
                        "Description Objective",
                        idUser
                ))
                .when()
                .post("http://localhost:" + port + "/ms/objectives")
                .then();

        response.assertThat().statusCode(200).and()
                .body("id", notNullValue());

        return UUID.fromString(response.extract().body().jsonPath().get("id"));
    }

    @Test
    public void update_ok() {

        UUID idObjective = add(idUser);

        RequestSpecification requestSpecification = given()
                .contentType(ContentType.JSON);

        ValidatableResponse response = requestSpecification
                .body(new RequestCreateObjectiveDTO(
                        "Update Name Objective",
                        "Update Description Objective",
                        idUser
                ))
                .when()
                .put("http://localhost:" + port + "/ms/objectives/" + idObjective + "/" + idUser)
                .then();

        response.assertThat().statusCode(200).and()
                .body("id", equalTo(idObjective.toString())).and()
                .body("nameObjective", equalTo("Update Name Objective")).and()
                .body("descriptionObjective", equalTo("Update Description Objective")).and()
                .body("idUser", equalTo(idUser.toString()));
    }

    @Test
    public void completeObjective_ok() {
        UUID idObjective = add(idUser);

        RequestSpecification requestSpecification = given()
                .contentType(ContentType.JSON);

        String url = "http://localhost:" + port + "/ms/objectives/" + idObjective + "/" + LocalDate.now() + "/" + idUser;

        ValidatableResponse response = requestSpecification
                .when()
                .patch(url)
                .then();

        response.assertThat().statusCode(200);
    }

    @Test
    public void getObjectiveById_ok() {
        UUID idObjective = add(idUser);

        RequestSpecification requestSpecification = given()
                .contentType(ContentType.JSON);

        String url = "http://localhost:"+port+"/ms/objectives/"+idObjective+"/"+idUser;

        ValidatableResponse response = requestSpecification
                .when()
                .get(url)
                .then();

        response.assertThat().statusCode(200).and()
                .body("id", equalTo(idObjective.toString()));
    }

    @Test
    public void getObjectivesByIdUser_ok() {
        UUID idUser = UUID.randomUUID();

        for (int i = 1; i <= 5; i++){
            add(idUser);
        }

        RequestSpecification requestSpecification = given()
                .contentType(ContentType.JSON);

        String url = "http://localhost:"+port+"/ms/objectives/user/"+idUser;

        ValidatableResponse response = requestSpecification
                .when()
                .get(url)
                .then();

        response.assertThat().statusCode(200);

        List<Objective> objectiveList = response.extract().body().jsonPath().get();

        Assertions.assertEquals(5, objectiveList.size());
    }

    @Test
    public void getObjectivesByIdUserToDo_ok() {
        UUID idUser = UUID.randomUUID();

        for (int i = 1; i <= 3; i++){
            add(idUser);
        }

        for (int i = 1; i <= 3; i++){
            UUID idObjective = add(idUser);
            completeObjective(idObjective, idUser);
        }

        RequestSpecification requestSpecification = given()
                .contentType(ContentType.JSON);

        String url = "http://localhost:"+port+"/ms/objectives/status/todo/user/"+idUser;

        ValidatableResponse response = requestSpecification
                .when()
                .get(url)
                .then();

        response.assertThat().statusCode(200);

        List<Objective> objectiveList = response.extract().body().jsonPath().get();

        Assertions.assertEquals(3, objectiveList.size());
    }

    private void completeObjective(UUID idObjective, UUID idUser) {

        RequestSpecification requestSpecification = given()
                .contentType(ContentType.JSON);

        String url = "http://localhost:" + port + "/ms/objectives/" + idObjective + "/" + LocalDate.now() + "/" + idUser;

        ValidatableResponse response = requestSpecification
                .when()
                .patch(url)
                .then();

        response.assertThat().statusCode(200);
    }

    @Test
    public void getObjectivesByIdUserDone_ok() {
        UUID idUser = UUID.randomUUID();

        for (int i = 1; i <= 3; i++){
            add(idUser);
        }

        for (int i = 1; i <= 3; i++){
            UUID idObjective = add(idUser);
            completeObjective(idObjective, idUser);
        }

        RequestSpecification requestSpecification = given()
                .contentType(ContentType.JSON);

        String url = "http://localhost:"+port+"/ms/objectives/status/done/user/"+idUser;

        ValidatableResponse response = requestSpecification
                .when()
                .get(url)
                .then();

        response.assertThat().statusCode(200);

        List<Objective> objectiveList = response.extract().body().jsonPath().get();

        Assertions.assertEquals(3, objectiveList.size());
    }

}
