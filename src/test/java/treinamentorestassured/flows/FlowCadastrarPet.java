package treinamentorestassured.flows;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import treinamentorestassured.jsonObjects.pet.Pet;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class FlowCadastrarPet {

    public int CadastrarPetRetornaId(Pet pet) {
        // headers
        List<Header> headerlist = new ArrayList<Header>();
        headerlist.add(new Header("Content-Type", "application/json"));
        headerlist.add(new Header("accept", "application/json"));
        Headers headers = new Headers(headerlist);

        int idCadastrado = given().
                basePath("/pet").
                body(pet).
                headers(headers).
                when().
                post().
                then().
                statusCode(200).
                extract().
                path("id");

        return idCadastrado;
    }
}
