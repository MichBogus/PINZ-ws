import controller.company.CompanyController;
import controller.item.UserItemsController;
import controller.login.LoginController;
import controller.register.RegisterController;
import model.entity.Company;
import model.entity.Item;
import model.entity.LoggedUser;
import model.entity.User;
import model.entity.utils.EntityPropertyGeneratorImpl;
import model.entity.utils.TimeStampValidatorImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import service.company.CompanyServiceImpl;
import service.item.ItemServiceImpl;
import service.login.LoginServiceImpl;
import service.register.RegisterServiceImpl;
import service.token.TokenValidationServiceImpl;
import utils.converter.RequestConverterImpl;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackageClasses = {
        RegisterController.class, RegisterServiceImpl.class,
        CompanyController.class, CompanyServiceImpl.class,
        LoginController.class, LoginServiceImpl.class, TimeStampValidatorImpl.class,
        UserItemsController.class, ItemServiceImpl.class, TokenValidationServiceImpl.class,
        RequestConverterImpl.class, EntityPropertyGeneratorImpl.class})

@EnableJpaRepositories(basePackages = "repository", considerNestedRepositories = true)
@EntityScan(basePackageClasses = {User.class, Company.class, LoggedUser.class, Item.class})
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

}
