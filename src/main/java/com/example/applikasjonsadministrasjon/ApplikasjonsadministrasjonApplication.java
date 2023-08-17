package com.example.applikasjonsadministrasjon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import io.github.cdimascio.dotenv.Dotenv;


@SpringBootApplication

public class ApplikasjonsadministrasjonApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        System.out.println( dotenv.get("OPENAI_API_KEY"));
        SpringApplication.run(ApplikasjonsadministrasjonApplication.class, args);
    }

}