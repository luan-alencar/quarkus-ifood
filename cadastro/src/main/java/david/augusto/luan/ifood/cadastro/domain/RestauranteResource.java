package david.augusto.luan.ifood.cadastro.domain;

import io.jaegertracing.internal.metrics.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("/api/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteResource {

    @GET
    public List<Restaurante> listarRestaurantes() {
        return Restaurante.listAll();
    }
}
