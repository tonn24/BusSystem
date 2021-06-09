package repository;

import com.example.demo.BusApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import repository.BaseRepositoryIntegrationTest.PropertiesInitializer;

@Testcontainers
@ContextConfiguration(initializers = PropertiesInitializer.class)
@SpringBootTest(classes = BusApplication.class)
public abstract class BaseRepositoryIntegrationTest {

  protected static final PostgreSQLContainer postgres;

  static {
    postgres = new PostgreSQLContainer("postgres:9.6.15")
        .withDatabaseName("publictransportationsystem")
        .withUsername("tonn24")
        .withPassword("password");
    postgres.start();
  }

  static class PropertiesInitializer implements
      ApplicationContextInitializer<ConfigurableApplicationContext> {
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      TestPropertyValues
          .of("spring.datasource.driver-class-name=" + postgres.getDriverClassName(),
              "spring.datasource.url=" + postgres.getJdbcUrl(),
              "spring.datasource.username=" + postgres.getUsername(),
              "spring.datasource.password=" + postgres.getPassword())
          .applyTo(configurableApplicationContext.getEnvironment());
    }
  }
}