package com.app.monacolibrary;

import com.app.monacolibrary.service.ApiRequest;
import com.app.monacolibrary.ui.Ui;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MonacoLibraryApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MonacoLibraryApplication.class, args);
    }

    @Autowired
    private Ui ui;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        ui.menu();
    }
}
