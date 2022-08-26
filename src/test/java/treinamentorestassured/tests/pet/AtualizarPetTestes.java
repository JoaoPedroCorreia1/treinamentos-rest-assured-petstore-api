package treinamentorestassured.tests.pet;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;
import treinamentorestassured.bases.TestBase;
import treinamentorestassured.flows.FlowCadastrarPet;
import treinamentorestassured.jsonObjects.pet.Category;
import treinamentorestassured.jsonObjects.pet.Pet;
import treinamentorestassured.jsonObjects.pet.Tag;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AtualizarPetTestes extends TestBase {

    @Test
    public void AtualizarDadosDePetExistente() {
        // objs
        FlowCadastrarPet flowCadastrarPet = new FlowCadastrarPet();

        //before
        long idPet = flowCadastrarPet.CadastrarPetRetornaId(new Pet());

        // parameters
        final long idPetExistente = idPet;

        Category categoryParaEditar = new Category();
        categoryParaEditar.setName("category editada");

        final String nameParaEditar = "nome editado";
        final String[] photoUrlsParaEditar = new String[]{"url editada"};

        Tag tag1Editada = new Tag();
        tag1Editada.setName("tag editada");

        final Tag[] tagsParaEditar = new Tag[]{tag1Editada};

        final String statusParaEditar = "pending";

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

}
