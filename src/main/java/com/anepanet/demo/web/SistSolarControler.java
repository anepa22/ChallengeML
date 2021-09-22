package com.anepanet.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anepanet.demo.entity.DiaSistemaSolar;
import com.anepanet.demo.entity.ResponseDia;
import com.anepanet.demo.service.SistSolarService;

@RestController
@RequestMapping("/sissol")
public class SistSolarControler {
	@Autowired
	SistSolarService service;
	
	
	@RequestMapping(path = "/getdia", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public DiaSistemaSolar getdia(@RequestParam Integer dia) {
		return service.getDiaDelSistemaSolar(dia);
	}
	
	@RequestMapping(path = "/getAllDias", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DiaSistemaSolar> getAllDias() {
		return service.getTodosDiasDelSistemaSolar();
	}
	
	@RequestMapping(path = "/getdiaMasLluvioso", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public DiaSistemaSolar getdiaMasLluvioso() {
		return service.getDiaMaslluvioso();
	}
	
	@RequestMapping(path = "/getdiasConLluvia", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DiaSistemaSolar> getdiasConLluvia() {
		return service.getdiasConLluvia();
	}
	
	@RequestMapping(path = "/getDiasConOptimaPresionYTemp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DiaSistemaSolar> getDiasConOptimaPresionYTemp() {
		return service.getDiasConOptimaPresionYTemp();
	}
	
	@RequestMapping(path = "/getDiasConSequia", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DiaSistemaSolar> getDiasConSequia() {
		return service.getDiasConSequia();
	}
	
	@RequestMapping(path = "/getClimaDelDia", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseDia getClimaDelDia(@RequestParam Integer dia) {
		return service.getClimaDelDia(dia);
	}
}
