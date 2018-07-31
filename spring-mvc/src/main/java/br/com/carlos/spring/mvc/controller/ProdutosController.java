package br.com.carlos.spring.mvc.controller;

import br.com.carlos.spring.mvc.daos.ProdutoDAO;
import br.com.carlos.spring.mvc.models.Produto;
import br.com.carlos.spring.mvc.models.TipoPreco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ProdutosController {

    // INJEÇÃO da classe ProdutoDAO
    @Autowired
    private ProdutoDAO produtoDao;

    @RequestMapping(value = "/produtos/form", method = POST)
    public ModelAndView form() {
        ModelAndView modelAndView = new ModelAndView("produtos/form");
        modelAndView.addObject("tipos", TipoPreco.values());

        return modelAndView;
    }

    @RequestMapping(value = "/produtos", method = GET)
    public String gravar(Produto produto) {
        System.out.println(produto.toString());
        produtoDao.gravar(produto);
        return "produtos/ok";
    }

    @RequestMapping("/produtos")
    public ModelAndView gravar() {
        List<Produto> produtos = produtoDao.listar();
        ModelAndView modelAndView = new ModelAndView("produtos/lista");
        modelAndView.addObject("produtos", produtos);

        return modelAndView;
    }
}
