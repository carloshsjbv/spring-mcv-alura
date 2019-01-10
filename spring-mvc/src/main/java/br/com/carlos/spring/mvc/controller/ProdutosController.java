package br.com.carlos.spring.mvc.controller;

import br.com.carlos.spring.mvc.daos.ProdutoDAO;
import br.com.carlos.spring.mvc.infra.FileSaver;
import br.com.carlos.spring.mvc.models.Produto;
import br.com.carlos.spring.mvc.models.TipoPreco;
import br.com.carlos.spring.mvc.validation.ProdutoValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

    // INJEÇÃO da classe ProdutoDAO
    @Autowired
    private ProdutoDAO produtoDao;
    
    // Injeção de dependência do componente de salvamento de arquivos.
    @Autowired
    private FileSaver fileSaver;

    /**
     * Relaciona o Controller com o Validator.
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new ProdutoValidation());
    }

    @RequestMapping(value = "/form", method = GET)
    public ModelAndView form(Produto produto) {

        ModelAndView modelAndView = new ModelAndView("produtos/form");
        modelAndView.addObject("tipos", TipoPreco.values());

        return modelAndView;
    }

    /**
     * Método de cadastro de produtos.
     *
     * IMPORTANTE: Deve-se manter a ordem dos parâmetros, pois para o Spring, se
     * você deseja validar uma classe, os resultados devem vir na sequência.
     *
     * @param sumario: arquivo submetido
     * @param produto: produto a ser cadastrado.
     * @param result: resultado de validações. Durante a validação, o Spring vai
     * coletar todos os erros e irá adicionar nesse parâmetro.
     * @param redirectAttributes: mensagens de redirecionamento.
     * @return
     */
    @RequestMapping(method = POST)
    public ModelAndView postProdutos(
            MultipartFile sumario,
            @Valid Produto produto, // @Valid sinaliza que o objeto é passível de validação.
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {

        if (result.hasErrors()) {
            return form(produto);
        }
        
        /**
         * O arquivo é gravado no sistema de arquivos por completo, porém, para
         * salvarmos no servidor, basta apenas a adição de uma String com o caminho
         * abreviado para o arquivo.
         */
        String path = fileSaver.writeFile("arquivos-sumario", sumario);
        produto.setSumarioPath(path);

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
