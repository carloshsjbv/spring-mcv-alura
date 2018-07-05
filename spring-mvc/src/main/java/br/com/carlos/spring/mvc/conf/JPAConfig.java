package br.com.carlos.spring.mvc.conf;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

public class JPAConfig {

    // Toda classe gerenciada pelo Spring é um Bean, logo, temos que inserir a
    // anotação @Bean para que o Spring entenda que retorno do método é uma 
    // classe que pode ser gerenciada por ele.
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring-mvc");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl", "update");
        
        factoryBean.setJpaProperties(properties);
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        
        factoryBean.setPackagesToScan("br.com.carlos.spring.mvc.models");
        
        return factoryBean;
        
    }

}
