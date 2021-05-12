package com.curso.cursomc;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.curso.cursomc.domain.*;
import com.curso.cursomc.domain.enums.EstadoPagamento;
import com.curso.cursomc.domain.enums.TipoCliente;
import com.curso.cursomc.repositories.*;
import com.curso.cursomc.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    @Autowired
    private S3Service s3Service;



    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        s3Service.uploadFile("C:\\Users\\mobile\\Pictures\\Wallpapers\\1080942.jpg");

    }

}
