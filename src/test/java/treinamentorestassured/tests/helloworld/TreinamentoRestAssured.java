package treinamentorestassured.tests.helloworld;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.http.*;
import treinamentorestassured.bases.TestBase;

import java.util.ArrayList;
import java.util.List;

public class TreinamentoRestAssured extends TestBase {

    @Test
    public void helloWorld() {
        List<Header> headerlist = new ArrayList<Header>();
        headerlist.add(new Header("content-type", "application/json"));
        headerlist.add(new Header("Authorization", "bearer eyTILNdondiantoe"));
        Headers headers = new Headers(headerlist);

        given().
                headers(headers).
                basePath("/pet/{petId}").
                pathParam("petId", 1).
                when().
                get().
                then().
                statusCode(200);
    }
}
