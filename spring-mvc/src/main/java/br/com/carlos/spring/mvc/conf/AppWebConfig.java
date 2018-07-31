package br.com.carlos.spring.mvc.conf;

import br.com.carlos.spring.mvc.controller.HomeController;
import br.com.carlos.spring.mvc.daos.ProdutoDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Classe responsável pelas configurações do Spring.
 *
 * @EnabeleWebMvc: habilita as funcionalidades MVC do Spring.
 * @ComponentScan: Procura por pacotes ou classes gerenciáveis pelo Spring.
 *
 * @author Carlos H
 */
@EnableWebMvc
@ComponentScan(basePackageClasses = {HomeController.class, ProdutoDAO.class})
public class AppWebConfig {

    /**
     * Toda classe gerenciada pelo Spring é um Bean, logo, temos que inserir a
     * anotação @Bean para que o Spring entenda que retorno do método é uma
     * classe que pode ser gerenciada por ele.
     *
     * @return resolver
     */
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();

        // Path das views
        resolver.setPrefix("/WEB-INF/views/");

        // Views são .jsp
        resolver.setSuffix(".jsp");

        return resolver;
    }

}
