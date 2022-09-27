package clases;

import java.util.ArrayList;
import java.util.List;

public class Movil {

	private String dominio;
	private List<Observacion> observaciones = new ArrayList<>();
	private boolean enElTaller;
	private boolean necesitaTaller;
	private String kilometraje;
	

	public String getKilometraje() {
		return kilometraje;
	}

	public void setKilometraje(String kilometraje) {
		this.kilometraje = kilometraje;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public List<Observacion> getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(List<Observacion> observaciones) {
		this.observaciones = observaciones;
	}

	public boolean isEnElTaller() {
		return enElTaller;
	}

	public void setEnElTaller(boolean enElTaller) {
		this.enElTaller = enElTaller;
	}

	public boolean isNecesitaTaller() {
		return necesitaTaller;
	}

	public void setNecesitaTaller(boolean necesitaTaller) {
		this.necesitaTaller = necesitaTaller;
	}

	public void agregarObservacion(String observacion) {
		observaciones.add(new Observacion("observacion", this));
	}
	
	public String toString() {
		return this.getDominio();
	}
	
	
	
	
	

}
