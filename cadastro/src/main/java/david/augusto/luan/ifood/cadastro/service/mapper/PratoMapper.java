package david.augusto.luan.ifood.cadastro.service.mapper;

import david.augusto.luan.ifood.cadastro.domain.Prato;
import david.augusto.luan.ifood.cadastro.service.dto.AdicionarPratoDTO;
import david.augusto.luan.ifood.cadastro.service.dto.AtualizarPratoDTO;
import david.augusto.luan.ifood.cadastro.service.dto.PratoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface PratoMapper {

    PratoDTO toDTO(Prato p);

    Prato toPrato(AdicionarPratoDTO dto);

    void toPrato(AtualizarPratoDTO dto, @MappingTarget Prato prato);

}