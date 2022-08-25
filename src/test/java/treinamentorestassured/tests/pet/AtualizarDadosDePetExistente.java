package treinamentorestassured.tests.pet;

import org.testng.annotations.Test;
import treinamentorestassured.flows.FlowCadastrarPet;
import treinamentorestassured.jsonObjects.pet.Pet;

public class AtualizarDadosDePetExistente {

    @Test
    public void teste() {
        // objs
        FlowCadastrarPet flowCadastrarPet = new FlowCadastrarPet();

        //before
        int idPet = flowCadastrarPet.CadastrarPetRetornaId(new Pet());

        // parameters
        final int idPetExistente = idPet;

        // body
        // headers

        // test
    }

}
