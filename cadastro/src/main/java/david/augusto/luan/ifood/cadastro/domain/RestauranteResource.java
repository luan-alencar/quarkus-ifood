package david.augusto.luan.ifood.cadastro.domain;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Tag(name = "restaurante")
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


    @PUT
    @Path("/{id}")
    @Transactional
    public void atualizar(@PathParam("id") Long id, Restaurante dto) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);
        if (restauranteOp.isEmpty()) throw new NotFoundException();
        Restaurante restaurante = restauranteOp.get();

        restaurante.nome = dto.nome;
        restaurante.persist();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);

        restauranteOp.ifPresentOrElse(Restaurante::delete, () -> {
            throw new NotFoundException();
        });
    }

    //Pratos

    @GET
    @Path("/{idRestaurante}/pratos")
    @Tag(name = "prato")
    public List<Restaurante> buscarPratos(@PathParam("idRestaurante") Long idRestaurante) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
        if (restauranteOp.isEmpty()) {
            throw new NotFoundException("Restaurante não existe");
        }
        return Prato.list("restaurante", restauranteOp.get());
    }

    @POST
    @Path("{idRestaurante}/pratos")
    @Transactional
    @Tag(name = "prato")
    public Response adicionarPrato(@PathParam("idRestaurante") Long idRestaurante, Prato dto) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
        if (restauranteOp.isEmpty()) {
            throw new NotFoundException("Restaurante não existe");
        }
        //Utilizando dto, recebi detached entity passed to persist:
        Prato prato = new Prato();
        prato.nome = dto.nome;
        prato.descricao = dto.descricao;

        prato.preco = dto.preco;
        prato.restaurante = restauranteOp.get();
        prato.persist();
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{idRestaurante}/pratos/{id}")
    @Transactional
    @Tag(name = "prato")
    public void atualizar(@PathParam("idRestaurante") Long idRestaurante, @PathParam("id") Long id, Prato dto) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
        if (restauranteOp.isEmpty()) {
            throw new NotFoundException("Restaurante não existe");
        }

        //No nosso caso, id do prato vai ser único, independente do restaurante...
        Optional<Prato> pratoOp = Prato.findByIdOptional(id);

        if (pratoOp.isEmpty()) {
            throw new NotFoundException("Prato não existe");
        }
        Prato prato = pratoOp.get();
        prato.preco = dto.preco;
        prato.persist();
    }

    @DELETE
    @Path("{idRestaurante}/pratos/{id}")
    @Transactional
    @Tag(name = "prato")
    public void delete(@PathParam("idRestaurante") Long idRestaurante, @PathParam("id") Long id) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
        if (restauranteOp.isEmpty()) {
            throw new NotFoundException("Restaurante não existe");
        }

        Optional<Prato> pratoOp = Prato.findByIdOptional(id);

        pratoOp.ifPresentOrElse(Prato::delete, () -> {
            throw new NotFoundException();
        });
    }

}
