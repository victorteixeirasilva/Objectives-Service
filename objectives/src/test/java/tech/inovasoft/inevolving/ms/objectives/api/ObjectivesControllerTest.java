package tech.inovasoft.inevolving.ms.objectives.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ObjectivesControllerTest {

    @LocalServerPort
    private int port;

    @Test
    public void add_ok() {
        //TODO: Desenvolver teste do End-Point
    }

    @Test
    public void update_ok() {
        //TODO: Desenvolver teste do End-Point
    }

    @Test
    public void completeObjective_ok() {
        //TODO: Desenvolver teste do End-Point
    }

    @Test
    public void getObjectiveById_ok() {
        //TODO: Desenvolver teste do End-Point
    }

    @Test
    public void getObjectivesByIdUser_ok() {
        //TODO: Desenvolver teste do End-Point
    }

    @Test
    public void getObjectivesByIdUserToDo_ok() {
        //TODO: Desenvolver teste do End-Point
    }

    @Test
    public void getObjectivesByIdUserDone_ok() {
        //TODO: Desenvolver teste do End-Point
    }

}
