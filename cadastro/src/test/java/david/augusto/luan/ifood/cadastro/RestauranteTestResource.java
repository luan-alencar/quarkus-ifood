package david.augusto.luan.ifood.cadastro;


import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import david.augusto.luan.ifood.cadastro.domain.Restaurante;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(CadastroTestLifecycleManager.class)
public class RestauranteTestResource {

    private static final String RESPONSE = "/api/restaurantes";

    @Test
    @DataSet("restaurantes-cenario-1.yml")
    public void testBuscarRestaurantes() {
        given()
                .when().get(RESPONSE)
                .then()
                .statusCode(200)
                .extract().asString();
    }

    @Test

    public void salvar() {
        given()
                .body(new Restaurante())
                .header("Content-type", "application/json")
                .when()
                .post(RESPONSE)
                .then()
                .statusCode(201);
    }
}