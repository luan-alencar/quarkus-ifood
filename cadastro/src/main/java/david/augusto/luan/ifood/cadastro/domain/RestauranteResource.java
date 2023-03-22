package david.augusto.luan.ifood.cadastro.domain;

import io.jaegertracing.internal.metrics.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.*;
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

    @POST
    @Transactional
    public void salvar(Restaurante dto) {
        Restaurante.persist(dto);
    }
}
