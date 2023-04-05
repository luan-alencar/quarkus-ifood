package david.augusto.luan.ifood.cadastro.service.dto;

import lombok.Data;

@Data
public class RestauranteDTO {

    public Long id;

    public String proprietario;

    public String cnpj;

    public String nomeFantasia;

    public LocalizacaoDTO localizacao;

    public String dataCriacao;
}
