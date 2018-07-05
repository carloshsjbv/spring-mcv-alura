package br.com.carlos.spring.mvc.daos;

import br.com.carlos.spring.mvc.models.Produto;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

// Cuida da persistência da entidade Produto no banco de dados
// @Repository mapeia ProdutoDAO para que o @Autowired do Spring enxergue a classe
@Repository
//@Component // Pai de @Repository. Mas neste caso, por questão semantica, utilizou-se @Repository
public class ProdutoDAO {

    // Gerenciador gerenciado pelo Spring
    @PersistenceContext
    private EntityManager manager;

    public void gravar(Produto produto) {
        manager.persist(produto);
    }

}
