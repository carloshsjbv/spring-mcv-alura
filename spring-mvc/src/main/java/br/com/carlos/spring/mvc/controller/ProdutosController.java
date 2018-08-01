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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("produtos")
public class ProdutosController {

    // INJEÇÃO da classe ProdutoDAO
    @Autowired
    private ProdutoDAO produtoDao;

    @RequestMapping(value = "/form", method = GET)
    public ModelAndView form() {

        ModelAndView modelAndView = new ModelAndView("produtos/form");
        modelAndView.addObject("tipos", TipoPreco.values());

        return modelAndView;
    }

    @RequestMapping(method = POST)
    public ModelAndView postProdutos(Produto produto, RedirectAttributes redirectAttributes) {

        System.out.println(produto.toString());
        produtoDao.salvar(produto);

        /**
         * O Flash Scoped é um escopo onde os objetos que adicionamos nele
         * através do método addFlashAttribute ficam vivos de um request para
         * outro, enquanto o navegador executa um redirect (usando o código de
         * status 302).
         *
         * O atributo é passado pelo Spring durante e a requisição e é utilizado
         * para adicionar uma mensagem para o próximo redirect (/produtos)
         */
        redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!!");

        /**
         * Ao fazer F5 o navegador repete o ultimo request que ele realizou.
         * Para se evitar o "bug do f5" deve-se fazer um redirect. "Always
         * redirect after post"
         */
        return new ModelAndView("redirect:produtos");
    }

    @RequestMapping(method = GET)
    public ModelAndView getProdutos() {
        List<Produto> produtos = produtoDao.listar();

        ModelAndView modelAndView = new ModelAndView("produtos/lista");
        modelAndView.addObject("produtos", produtos);

        return modelAndView;
    }
}
