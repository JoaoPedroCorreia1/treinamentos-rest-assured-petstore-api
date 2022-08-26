package treinamentorestassured.tests.pet;

import org.testng.annotations.Test;
import treinamentorestassured.bases.TestBase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PesquisarPetTestes extends TestBase {

    @Test
    public void PesquisarPetInexistente() {
        // parameters
        final int idPetInexistente = 99998;

        // test
        given().
                basePath("/pet/{petId}").
                pathParams("petId", idPetInexistente).
                when().
                get().
                then().
                statusCode(404).
                body("code", equalTo(1),
                        "type", equalTo("error"),
                        "message", equalTo("Pet not found"));
    }

}
