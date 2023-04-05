package david.augusto.luan.ifood.cadastro.service.mapper;

import david.augusto.luan.ifood.cadastro.domain.AtualizarRestauranteDTO;
import david.augusto.luan.ifood.cadastro.domain.Restaurante;
import david.augusto.luan.ifood.cadastro.service.dto.AdicionarRestauranteDTO;
import david.augusto.luan.ifood.cadastro.service.dto.RestauranteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {

    @Mapping(target = "nome", source = "nomeFantasia")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    @Mapping(target = "localizacao.id", ignore = true)
    Restaurante toEntity(AdicionarRestauranteDTO dto);

    @Mapping(target = "nome", source = "nomeFantasia")
     void toEntity(AtualizarRestauranteDTO dto, @MappingTarget Restaurante restaurante);

    @Mapping(target = "nomeFantasia", source = "nome")
    //Exemplo de formatação.
    @Mapping(target = "dataCriacao", dateFormat = "dd/MM/yyyy HH:mm:ss")
    RestauranteDTO toDTO(Restaurante r);
}
