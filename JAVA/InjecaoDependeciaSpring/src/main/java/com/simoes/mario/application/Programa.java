package com.simoes.mario.application;

import com.simoes.mario.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Programa implements CommandLineRunner {

	private PayService payService;

	public static void main(String[] args) {
		SpringApplication.run(Programa.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Teste de função spring");
		payService = new PayService(new TaxService(), new DeliveryService());
		System.out.printf("Resultado = R$%.2f", payService.finalPrice(300.0,"SC"));
	}
}
