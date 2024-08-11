package ru.mts.homework;

import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@EnableProcessApplication("ProcessApplication")
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
