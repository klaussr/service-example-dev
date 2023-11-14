package ru.budgett;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableSwagger2
@EnableAsync
public class Application {
    public static void main(String[] args) {
        run(Application.class, args);
    }
}
