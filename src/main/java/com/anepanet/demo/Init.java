package com.anepanet.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.anepanet.demo.service.SistSolarService;

@Component
public class Init implements ApplicationRunner{
	
	@Autowired
	SistSolarService service;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Arrancando.....");
		
		service.calculoCoordenadasParaAnios(10);
	}
}
