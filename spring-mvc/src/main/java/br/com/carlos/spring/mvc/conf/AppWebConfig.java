package br.com.carlos.spring.mvc.conf;

import br.com.carlos.spring.mvc.controller.HomeController;
import br.com.carlos.spring.mvc.daos.ProdutoDAO;
import br.com.carlos.spring.mvc.infra.FileSaver;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
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
@ComponentScan(basePackageClasses = {HomeController.class, ProdutoDAO.class, FileSaver.class})
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

    /**
     * Configurador de mensagens.
     *
     * @return
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("/WEB-INF/message");
        messageSource.setDefaultEncoding("UTF-8");

        // Define tempo de cache da mensagem. No caso, um reload é feito
        // a cada segundo, e desta forma não é necessário fazer o build novamente.
        messageSource.setCacheSeconds(1);

        return messageSource;
    }

    @Bean
    public FormattingConversionService mvcConversionService() {
        DefaultFormattingConversionService conversionService
                = new DefaultFormattingConversionService();
        DateFormatterRegistrar registrar = new DateFormatterRegistrar();
        registrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
        registrar.registerFormatters(conversionService);

        return conversionService;
    }

    /**
     * Multipart resolver criador para que seja possível submeter arquivos.
     *
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

}
