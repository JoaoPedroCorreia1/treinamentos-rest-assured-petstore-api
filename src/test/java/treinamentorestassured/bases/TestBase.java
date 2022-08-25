package treinamentorestassured.bases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import treinamentorestassured.GlobalParameters;

public class TestBase {

    @BeforeSuite
    public void BeforeSuite() {
        new GlobalParameters();
    }

    @BeforeTest
    public void beforeTest(){
    }

    @AfterTest
    public void afterTest(){
    }

    @AfterSuite
    public static void afterSuite(){
    }

}
