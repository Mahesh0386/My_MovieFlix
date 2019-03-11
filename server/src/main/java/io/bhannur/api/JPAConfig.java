	package io.bhannur.api;

    import org.eclipse.persistence.config.PersistenceUnitProperties;
    import org.eclipse.persistence.logging.SessionLog;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.jdbc.datasource.DriverManagerDataSource;
    import org.springframework.orm.jpa.JpaTransactionManager;
    import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
    import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
    import org.springframework.transaction.PlatformTransactionManager;
    import org.springframework.transaction.annotation.EnableTransactionManagement;

    import javax.persistence.EntityManagerFactory;
    import javax.sql.DataSource;
    import java.util.Properties;


    @Configuration
    @EnableTransactionManagement
    public class JPAConfig {

        @Bean
        public LocalContainerEntityManagerFactoryBean createEmf() {
            LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
            emf.setJpaVendorAdapter(new EclipseLinkJpaVendorAdapter());
            emf.setPackagesToScan("io.bhannur.api.entity");
            emf.setDataSource(dataSource());
            emf.setJpaProperties(jpaProperties());
            return emf;
        }

        @Bean
        public DataSource dataSource() {
            DriverManagerDataSource ds = new DriverManagerDataSource();
            ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
            ds.setUrl("jdbc:mysql://localhost:3306/movieflix-db?allowPublicKeyRetrieval=true&useSSL=false");
            /*ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
            ds.setUrl("jdbc:mysql://localhost:3306/example-db?useSSL=false");*/
            ds.setUsername("root");
            ds.setPassword("Denso@963");
            return ds;
        }

        @Bean
        public PlatformTransactionManager txnMgr(EntityManagerFactory emf) {
            JpaTransactionManager txnMgr = new JpaTransactionManager(emf);
            return txnMgr;
        }

        private Properties jpaProperties() {
            Properties prop = new Properties();
            prop.setProperty(PersistenceUnitProperties.WEAVING, "false");
            prop.setProperty(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.CREATE_OR_EXTEND);
            prop.setProperty(PersistenceUnitProperties.LOGGING_LEVEL, SessionLog.FINE_LABEL);
            return prop;
        }
    }
