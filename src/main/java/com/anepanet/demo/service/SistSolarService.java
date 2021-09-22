package com.anepanet.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anepanet.demo.entity.CordRec;
import com.anepanet.demo.entity.DiaSistemaSolar;
import com.anepanet.demo.entity.ResponseDia;
import com.anepanet.demo.repository.SistemaSolRepo;

@Service
public class SistSolarService {

	private static final Integer DIAS_POR_ANIO = 360;

	@Autowired
	SistemaSolRepo repository;

	public void calculoCoordenadasParaAnios(Integer anios) {

		for (int dia = 0; dia <= DIAS_POR_ANIO * anios; dia++) {

			DiaSistemaSolar diaSolar = DiaSistemaSolar.builder().build();

			// Calculo coordenadas rectangulares para los planetas
			diaSolar.setDia(dia);

			// Planeta 1
			int angP1 = 1;
			int distP1 = 500;
			diaSolar.setCorPolP1(angP1 * dia);
			diaSolar.setDisPolP1(distP1);
			CordRec cordRectP1 = calculoCordenadaRect(angP1 * dia, distP1);
			diaSolar.setCorRecXP1(cordRectP1.getX());
			diaSolar.setCorRecYP1(cordRectP1.getY());

			// Planeta 2
			int angP2 = 3;
			int distP2 = 2000;
			diaSolar.setCorPolP2(angP2 * dia);
			diaSolar.setDisPolP2(distP2);
			CordRec cordRectP2 = calculoCordenadaRect(angP2 * dia, distP2);
			diaSolar.setCorRecXP2(cordRectP2.getX());
			diaSolar.setCorRecYP2(cordRectP2.getY());

			// Planeta 3
			int angP3 = -5;
			int distP3 = 1000;
			diaSolar.setCorPolP3(angP3 * dia);
			diaSolar.setDisPolP3(distP3);
			CordRec cordRectP3 = calculoCordenadaRect(angP3 * dia, distP3);
			diaSolar.setCorRecXP3(cordRectP3.getX());
			diaSolar.setCorRecYP3(cordRectP3.getY());
			// Fin calculo coordenadas rectangulares para los planetas

			// Calculo distacias entre Planetas
			diaSolar.setDistP1eP2(calculoDistanciaEntrePlanetas(cordRectP1.getX(), cordRectP1.getY(), cordRectP2.getX(),
					cordRectP2.getY()));
			diaSolar.setDistP2eP3(calculoDistanciaEntrePlanetas(cordRectP2.getX(), cordRectP2.getY(), cordRectP3.getX(),
					cordRectP3.getY()));
			diaSolar.setDistP3eP1(calculoDistanciaEntrePlanetas(cordRectP3.getX(), cordRectP3.getY(), cordRectP1.getX(),
					cordRectP1.getY()));
			// Fin calculo distacias entre Planetas

			// Calculo pendientes entre planetas
			diaSolar.setPendEP1P2(calculoPendienteEntrePlanetas(cordRectP1.getX(), cordRectP1.getY(), cordRectP2.getX(), cordRectP2.getY()));
			diaSolar.setPendEP2P3(calculoPendienteEntrePlanetas(cordRectP2.getX(), cordRectP2.getY(), cordRectP3.getX(), cordRectP3.getY()));
			diaSolar.setPendEP3P1(calculoPendienteEntrePlanetas(cordRectP3.getX(), cordRectP3.getY(), cordRectP1.getX(), cordRectP1.getY()));
			// Fin calculo pendientes entre planetas
			
			// Calculo pendientes entre planetas y Sol
			diaSolar.setPendEP1Sol(calculoPendienteAlSol(cordRectP1.getX(), cordRectP1.getY()));
			diaSolar.setPendEP2Sol(calculoPendienteAlSol(cordRectP2.getX(), cordRectP2.getY()));
			diaSolar.setPendEP3Sol(calculoPendienteAlSol(cordRectP3.getX(), cordRectP3.getY()));
			// Fin calculo pendientes entre planetas y Sol
			
			// Verifico si el sol esta dentro del triangulo
			diaSolar.setSolDentroDelTriangulo(estaElSolEntreEltreiangolodePlanetas(cordRectP1, cordRectP2, cordRectP3));
			// Fin Verifico si el sol esta dentro del triangulo
			
			repository.save(diaSolar);

		}
	}
	
	// Funcion para calcular la distancia entre dos puntos del plano
	private Double calculoDistanciaEntrePlanetas(double x, double y, double x2, double y2) {
		Double dist = Math.sqrt(Math.pow((x2 - x), 2) + (Math.pow((y2 - y), 2)));
		return dist;
	}

	// Funcion para convertir coordenadas polares en recatangulares
	private CordRec calculoCordenadaRect(int ang, int dist) {
		Double x = Math.sin(Math.toRadians(ang)) * dist;
		Double y = Math.cos(Math.toRadians(ang)) * dist;

		CordRec cordRec = CordRec.builder().y(y).x(x).build();

		return cordRec;
	}

	//	Funcion para calcular pendiente entre planetas
	private Double calculoPendienteEntrePlanetas(double x, double y, double x2, double y2) {
		Double pend = ((y2 - y) / (x2 - x));
		
		if (pend.isInfinite())
			return null;
		
		return formatParaRedondeoAlineacionEntrePlanetas(pend);
	}
	
	// Funcion para calcular pendiente respecto del Sol
	private Double calculoPendienteAlSol(double x, double y) {
		Double pend = (y/x);
		
		if (pend.isInfinite())
			return new Double(0);
		
		return formatParaRedondeoAlineacionEntrePlanetasYSol(pend);
	}

	// Funciones para calcular si el sol esta dentro del triangulo
	private boolean estaElSolEntreEltreiangolodePlanetas(CordRec p1, CordRec p2, CordRec p3) {
		CordRec cordRecSol = CordRec.builder().x(new Double(0)).y(new Double(0)).build();

		boolean ori1 = orientacionDelTriangulo(cordRecSol, p1, p2);
		boolean ori2 = orientacionDelTriangulo(cordRecSol, p2, p3);
		boolean ori3 = orientacionDelTriangulo(cordRecSol, p3, p1);

		return (ori1 && ori2 && ori3) || (!ori1 && !ori2 && !ori3);
	}

	private boolean orientacionDelTriangulo(CordRec p1, CordRec p2, CordRec p3) {
		return (p2.getX() - p1.getX()) * (p3.getY() - p1.getY())
				- (p2.getY() - p1.getY()) * (p3.getX() - p1.getX()) > 0;
	}
	// Fin funciones para calcular si el sol esta dentro del triangulo

	// Funcion para redondear (punto flotante causa muchos problemas para el calculo)
	private double formatParaRedondeoAlineacionEntrePlanetas(double value) {
		return (double)Math.round(value * 1) / 1; 
	}
	
	// Funcion para redondear (punto flotante causa muchos problemas para el calculo)
	private double formatParaRedondeoAlineacionEntrePlanetasYSol(double value) {
		return (double)Math.round(value * 10) / 10; 
	}

	public DiaSistemaSolar getDiaDelSistemaSolar(int dia) {
		Optional<DiaSistemaSolar> diaSistSol = repository.findByDay(dia);

		return diaSistSol.get();
	}

	public List<DiaSistemaSolar> getTodosDiasDelSistemaSolar() {
		List<DiaSistemaSolar> diaSistSol = repository.findAll();

		return diaSistSol;
	}
	
	public DiaSistemaSolar getDiaMaslluvioso() {
		Optional<DiaSistemaSolar> diaSistSol = repository.findDiaMaslluvioso();

		return diaSistSol.get();
	}

	public List<DiaSistemaSolar> getdiasConLluvia() {
		List<DiaSistemaSolar> diaSistSol = repository.findDiasConlluvia();

		return diaSistSol;
	}
	
	public List<DiaSistemaSolar> getDiasConOptimaPresionYTemp() {
		List<DiaSistemaSolar> diaSistSol = repository.findDiasConOptimaPresionYTemp();

		return diaSistSol;
	}
	
	public List<DiaSistemaSolar> getDiasConSequia() {
		List<DiaSistemaSolar> diaSistSol = repository.findDiasConSequia();

		return diaSistSol;
	}
	
	public ResponseDia getClimaDelDia(int dia) {
		Optional<DiaSistemaSolar> diaSistSol = repository.findByDay(dia);
		
		ResponseDia responseDia = ResponseDia.builder().build();

		if (diaSistSol.get().isSolDentroDelTriangulo()) {
			responseDia.setDia(diaSistSol.get().getDia());
			
			if (diaSistSol.get().isSolDentroDelTriangulo()) 
				responseDia.setClimaDia("Llueve.");
			else
				responseDia.setClimaDia("No Llueve.");
		}

		return responseDia;
	}
}
