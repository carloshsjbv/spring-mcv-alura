package br.com.carlos.spring.mvc.conf;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// extends = Herda Servlets do Spring.
public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppWebConfig.class, JPAConfig.class};
    }

    /**
     * Método que diz ao Spring quais são as classes de configuração do projeto.
     *
     * @return config.classes
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppWebConfig.class, JPAConfig.class};
    }

    /**
     * Refere-se ao mapeamento da aplicação, ou seja, páginas serão mapeadas a
     * partir do conteúdo fornecido no array de String
     *
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
