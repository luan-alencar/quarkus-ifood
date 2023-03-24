package david.augusto.luan.ifood.cadastro;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
@QuarkusTestResource(CadastroTestResourceLifecycleManager.class)
public class RestauranteTestResource {

    @Test
    public void testBuscarRestaurantes() {
        given()
                .when().get("/api/restaurantes")
                .then()
                .statusCode(200)
                .extract().asString();
    }
}
