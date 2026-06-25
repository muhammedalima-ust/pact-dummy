package com.citesting.contract.pos;
 
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
 
import java.net.http.HttpClient;
 
import static io.restassured.RestAssured.given;
 
final class OmsClient {
 
 
    private String baseUrl;
    private HttpClient client;
 
    private static final String BASE_URL = System.getProperty(
            "baseUrl",
            System.getenv().getOrDefault("BASE_URL", "http://localhost:4010/")
    );

    @BeforeEach
    void setup() {
        this.baseUrl = BASE_URL;
        this.client = HttpClient.newHttpClient();
    }
 
    public Order getOrder(int id) {
        
        Response response = given()
                .baseUri(baseUrl)
                .basePath("/orders/" + id)
                .get();
        response.then().statusCode(200);

        int orderId = response.then().extract().path("id");
        String status = response.then().extract().path("status");
        double total = response.then().extract().path("total");

        return new Order(response.statusCode(),orderId,status,total);
    }

    
 
    record Order(int statuscode,int orderId,String status,double total){}
 
 
}