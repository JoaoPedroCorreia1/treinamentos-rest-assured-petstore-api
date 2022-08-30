package treinamentorestassured.tests.pet;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;
import treinamentorestassured.bases.TestBase;

import java.awt.peer.SystemTrayPeer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Test
    public void PesquisarPorPetsComStatusPending() {
        // parameters
        String status = "pending";

        // headers
        List<Header> headerlist = new ArrayList<Header>();
        headerlist.add(new Header("accept", "application/json"));
        Headers headers = new Headers(headerlist);

        // test
        given().
                basePath("/pet/findByStatus").
                queryParam("status", status).
                headers(headers).
                when().
                get().
                then().
                statusCode(200).
                body("'status'", hasItems(status));
    }
}
