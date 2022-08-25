package treinamentorestassured.tests.pet;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.hamcrest.Matcher;
import org.testng.annotations.Test;
import treinamentorestassured.bases.TestBase;
import treinamentorestassured.jsonObjects.order.Order;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CadastrarNovoPedidoDePetComSucesso extends TestBase {

    @Test
    public void teste() {
        // parameters
        final int petId = 0;
        final int quantity = 0;
        final String shipDate = "2022-08-23T21:18:49.448Z";
        final String shipDateParaTestar = shipDate.replace("Z", "+0000");
        final String status = "placed";
        final boolean complete = true;

        //body
        Order order = new Order();
        order.setPetId(petId);
        order.setQuantity(quantity);
        order.setShipDate(shipDate);
        order.setStatus(status);
        order.setComplete(complete);

        // headers
        List<Header> headerlist = new ArrayList<Header>();
        headerlist.add(new Header("Content-Type", "application/json"));
        headerlist.add(new Header("accept", "application/json"));
        Headers headers = new Headers(headerlist);

        // test
        given().log().everything().
                basePath("/store/order").
                headers(headers).
                body(order).
                when().
                post().
                then().
                statusCode(200).
                body("petId", equalTo(petId),
                     "quantity", equalTo(quantity),
                     "shipDate", equalTo(shipDateParaTestar),
                     "status", equalTo(status),
                     "complete", equalTo(complete));
    }

}
