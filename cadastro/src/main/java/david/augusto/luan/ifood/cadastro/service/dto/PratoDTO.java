package david.augusto.luan.ifood.cadastro.service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PratoDTO {

    public Long id;
    public String nome;
    public String descricao;
    public RestauranteDTO restaurante;
    public BigDecimal preco;
}
