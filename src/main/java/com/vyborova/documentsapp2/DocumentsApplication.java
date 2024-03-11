package com.vyborova.documentsapp2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DocumentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocumentsApplication.class, args);
        CreationController.menu();
    }

}
