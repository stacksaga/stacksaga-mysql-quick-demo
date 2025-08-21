package demo.quickdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(
//        exclude = R2dbcAutoConfiguration.class
)
@EnableDiscoveryClient
public class StacksagaQuickDemoApplication implements CommandLineRunner {
    private final ApplicationContext applicationContext;

    public StacksagaQuickDemoApplication(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(ApplicationContext applicationContext) {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/stacksaga/**")
                        .allowedOrigins("https://live.stacksaga.org")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(StacksagaQuickDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        SagaEventManager bean = this.applicationContext.getBean(SagaEventManager.class);
//        System.out.println("bean = " + bean);
    }
}
