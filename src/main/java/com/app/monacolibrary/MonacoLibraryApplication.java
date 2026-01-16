package com.app.monacolibrary;

import com.app.monacolibrary.service.HttpApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MonacoLibraryApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MonacoLibraryApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        HttpApi api = new HttpApi();
        api.realizaSolicitud("https://gutendex.com/books/?search=dickens%20great");

    }
}
