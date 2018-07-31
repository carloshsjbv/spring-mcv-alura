package br.com.carlos.spring.mvc.daos;

import br.com.carlos.spring.mvc.models.Produto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.Query;

/**
 * Recurso persistível em banco de dados: ProdutoDAO
 *
 * @Repository mapeia ProdutoDAO para que o @Autowired do Spring enxergue a
 * classe.
 *
 * OBS.: Também é possiíve utilizar @Component, que é Pai de @Repository.
 * Entretanto por uma questãi semantica, utilizou-se @Repository.
 *
 *
 * @author Carlos H
 */
@Repository
@Transactional
public class ProdutoDAO {

    /**
     * Gerenciador de entidades fornecido pelo Spring.
     */
    @PersistenceContext
    private EntityManager manager;

    /**
     * Método de inserção.
     *
     * @param produto
     */
    public void gravar(Produto produto) {
        manager.persist(produto);
    }

    public List<Produto> listar() {

        return manager.createQuery("SELECT * FROM PRODUTO", Produto.class).getResultList();

    }

}
