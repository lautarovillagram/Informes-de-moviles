package clases;

import java.util.ArrayList;
import java.util.List;

public class Movil {

	private String dominio;
	private List<Observacion> observaciones = new ArrayList<>();
	private boolean enElTaller;
	private boolean necesitaTaller;
	private String kilometraje;
	private boolean tieneCriquet;
	private boolean tieneMatafuego;
	

	public boolean isTieneCriquet() {
		return tieneCriquet;
	}

	public void setTieneCriquet(boolean tieneCriquet) {
		this.tieneCriquet = tieneCriquet;
	}

	public boolean isTieneMatafuego() {
		return tieneMatafuego;
	}

	public void setTieneMatafuego(boolean tieneMatafuego) {
		this.tieneMatafuego = tieneMatafuego;
	}

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
		observaciones.add(0, new Observacion("observacion", this));
	}
	
	public String toString() {
		return this.getDominio();
	}
	
	public Movil(String dominio) {
		this.dominio = dominio;
	}
	
	public Movil (String dominio, boolean enTaller, boolean necesitaTaller, boolean tieneMatafuego, boolean tieneCriquet, String kilometros) {
		this.dominio = dominio;
		this.enElTaller = enTaller;
		this.necesitaTaller = necesitaTaller;
		this.tieneMatafuego = tieneMatafuego;
		this.tieneCriquet = tieneCriquet;
		this.kilometraje = kilometros;
	}
	
	public List<Observacion> getObsSinSolucionar() {
		List<Observacion> observacionesSinSolucionar = this.getObservaciones().stream().filter(o -> !o.isFueSolucionado()).toList();
		
		return observacionesSinSolucionar;
	}
	
	public List<Observacion> getObsSolucionadas() {
		List<Observacion> observacionesSolucionadas = this.getObservaciones().stream().filter(o -> o.isFueSolucionado()).toList();
		
		return observacionesSolucionadas;
	}
	
	
	
	
	

}
