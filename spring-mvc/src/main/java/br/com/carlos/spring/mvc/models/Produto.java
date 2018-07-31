package br.com.carlos.spring.mvc.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String titulo;
    private String descricao;
    private int paginas;

    /**
     * Denota relação com a classe Preco, porém com a utilização da anotação 
     * abaixo, o relacionamento é automático e é criada uma tabela destinada 
     * somente ao armazenamento de preços.
     * 
     */
    @ElementCollection
    private List<Preco> preco;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public List<Preco> getPreco() {
        return preco;
    }

    public void setPreco(List<Preco> preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", paginas=" + paginas + ", preco=" + preco + '}';
    }

}
