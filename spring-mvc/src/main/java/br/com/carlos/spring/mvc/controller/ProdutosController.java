package br.com.carlos.spring.mvc.controller;

import br.com.carlos.spring.mvc.daos.ProdutoDAO;
import br.com.carlos.spring.mvc.models.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProdutosController {
    
    // INJEÇÃO da classe ProdutoDAO
    @Autowired
    private ProdutoDAO produtoDao;

    @RequestMapping("/produtos/form")
    public String form() {
        return "produtos/form";
    }

    @RequestMapping("/produtos")
    public String grava(Produto produto) {
        System.out.println(produto.toString());
        produtoDao.gravar(produto);
        return "produtos/ok";
    }
}
