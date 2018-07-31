package br.com.carlos.spring.mvc.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

/**
 * Classe responsável pelas configurações de banco de dados, conexão e
 * transações.
 *
 * @EnableTransactionManagement = Faz com que o Spring gerencie transações.
 *
 * @author Carlos H
 */
@EnableTransactionManagement
public class JPAConfig {

    /**
     * Toda classe gerenciada pelo Spring é um Bean, logo, temos que inserir a
     * anotação @Bean para que o Spring entenda que retorno do método é uma
     * classe que pode ser gerenciada por ele.
     *
     * @return factoryBean
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        // Adaptador
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        // Gerenciador de conexões
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUsername("carlos");
        dataSource.setPassword("violaopreto");
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521/XE");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");

        factoryBean.setDataSource(dataSource);

        // Propriedades Hibernate
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");

        factoryBean.setJpaProperties(properties);
        factoryBean.setJpaVendorAdapter(vendorAdapter);

        factoryBean.setPackagesToScan("br.com.carlos.spring.mvc.models");

        return factoryBean;

    }

    /**
     * Sempre deve haver um gerenciador de transações para a comunicação com o
     * banco de dados. Este método é responsavel pelas transações dos objetos
     * gerenciados por um EntityManager e anotado como @Transacional.
     *
     * A partir desse bean, o Spring fornecerá as transações para o
     * EntityManager
     *
     * @param emf = Instância de um EntityManagerFactory
     * @return new JpaTransactionManager, a partir de um objeto de EntityManager
     */
    @Bean
    public JpaTransactionManager jpaTransactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

}
