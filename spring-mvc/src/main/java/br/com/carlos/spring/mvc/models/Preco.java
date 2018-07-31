package br.com.carlos.spring.mvc.models;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

/**
 * @Embeddable significa que a classe está inclusa em uma outra, no caso, Preco
 * está 'embedded' em Produto.
 * 
 * @author Carlos H
 */
@Embeddable
public class Preco {

    private BigDecimal preco;
    private TipoPreco tipoPreco;

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public TipoPreco getTipoPreco() {
        return tipoPreco;
    }

    public void setTipoPreco(TipoPreco tipoPreco) {
        this.tipoPreco = tipoPreco;
    }

}
