package treinamentorestassured;

import io.restassured.RestAssured;

import java.io.*;
import java.util.Properties;

public class GlobalParameters {
    private Properties properties;

    public GlobalParameters() {
        properties = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("globalParameters.properties");
            properties.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RestAssured.baseURI = properties.getProperty("url.default");
    }

}
