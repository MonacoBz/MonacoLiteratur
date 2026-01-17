package com.app.monacolibrary;

import com.app.monacolibrary.service.ApiRequest;
import com.app.monacolibrary.ui.Ui;
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
        Ui ui = new Ui();
        ui.menu();

    }
}
