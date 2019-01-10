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

    private BigDecimal valor;
    private TipoPreco tipo;

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TipoPreco getTipo() {
        return tipo;
    }

    public void setTipo(TipoPreco tipo) {
        this.tipo = tipo;
    }

}
