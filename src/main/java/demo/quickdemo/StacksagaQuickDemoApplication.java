package demo.quickdemo;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Import;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.stacksaga.Aggregator;
import org.stacksaga.autoconfigure.property.StacksagaProperties;
import org.stacksaga.core.DefaultKeyGen;
import org.stacksaga.executor.SagaExecutor;
import reactor.core.publisher.Hooks;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SpringBootApplication(
//        exclude = R2dbcAutoConfiguration.class
)
@Import({
        StacksagaProperties.class,
        DefaultKeyGen.class,
})
public class StacksagaQuickDemoApplication {
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
}
