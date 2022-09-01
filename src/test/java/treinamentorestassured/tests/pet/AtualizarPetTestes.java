package treinamentorestassured.tests.pet;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;
import treinamentorestassured.bases.TestBase;
import treinamentorestassured.flows.FlowCadastrarPet;
import treinamentorestassured.jsonObjects.pet.Category;
import treinamentorestassured.jsonObjects.pet.Pet;
import treinamentorestassured.jsonObjects.pet.Tag;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AtualizarPetTestes extends TestBase {

    @Test
    public void AtualizarDadosDePetExistente() {
        // object instances
        FlowCadastrarPet flowCadastrarPet = new FlowCadastrarPet();

        // parameters
        long idPetExistente;

        Category categoryParaEditar = new Category();
        categoryParaEditar.setName("category editada");

        final String nameParaEditar = "nome editado";
        final String[] photoUrlsParaEditar = new String[]{"url editada"};

        Tag tag1Editada = new Tag();
        tag1Editada.setName("tag editada");

        final Tag[] tagsParaEditar = new Tag[]{tag1Editada};

        final String statusParaEditar = "pending";

        //before
        idPetExistente = flowCadastrarPet.CadastrarPetRetornaId(new Pet());

        // body
        Pet petParaEditar = new Pet();

        petParaEditar.setId(idPetExistente);

        petParaEditar.setCategory(categoryParaEditar);
        petParaEditar.setName(nameParaEditar);

        petParaEditar.setPhotoUrls(photoUrlsParaEditar);
        petParaEditar.setTags(tagsParaEditar);

        petParaEditar.setStatus(statusParaEditar);

        // headers
        List<Header> headerlist = new ArrayList<Header>();
        headerlist.add(new Header("Content-Type", "application/json"));
        headerlist.add(new Header("accept", "application/json"));
        Headers headers = new Headers(headerlist);

        // test
        given().
                basePath("/pet").
                headers(headers).
                body(petParaEditar).
                when().
                put().
                then().log().everything().
                statusCode(200).
                body("id", equalTo(idPetExistente),
                        "category.id", equalTo(categoryParaEditar.getId()),
                        "category.name", equalTo(categoryParaEditar.getName()),
                        "name", equalTo(nameParaEditar),
                        "photoUrls[0]", equalTo(photoUrlsParaEditar[0]),
                        "tags[0].id", equalTo(tag1Editada.getId()),
                        "tags[0].name", equalTo(tag1Editada.getName()),
                        "status", equalTo(statusParaEditar));
    }

    @Test
    public void AtualizarPetComIdComFormatoInvalido() {
        // parameters
        final String idComFomatoInvalido = "string";

        // body
        JSONObject pet = new JSONObject();
        JSONObject category = new JSONObject();
        JSONObject tag1 = new JSONObject();
        JSONObject tag2 = new JSONObject();
        JSONArray tags = new JSONArray();
        JSONArray photoURLs = new JSONArray();

        pet.put("id", idComFomatoInvalido); // formato inválido
        pet.put("name", "Shepherd");
        pet.put("status", "available");

        category.put("id", 99998);
        category.put("name", "felino");
        pet.put("category", category);

        tag1.put("id", 99998);
        tag1.put("name", "Sem raça definida");
        tag2.put("id", 99999);
        tag2.put("name", "Amarelo");
        tags.add(tag1);
        tags.add(tag2);
        pet.put("tags", tags);

        photoURLs.add("fotosdegato.com.br/foto1.png");
        photoURLs.add("fotosdegato.com.br/foto2.png");

        pet.put("photoUrls", photoURLs);
        // headers
        List<Header> headerlist = new ArrayList<Header>();
        headerlist.add(new Header("Content-Type", "application/json"));
        headerlist.add(new Header("accept", "application/json"));
        Headers headers = new Headers(headerlist);

        // test
        given().
                basePath("/pet").
                headers(headers).
                body(pet).
                when().
                put().
                then().
                statusCode(400);
    }

    @Test
    public void RealizarRequisicaoComMetodoInvalido() {
        // test
        given().
                basePath("/pet").
                when().
                patch().
                then().
                statusCode(405);
    }

}
