package clases;

import java.time.LocalDateTime;

public class Observacion {
	private String obs;
	private LocalDateTime fecha;
	private Movil movil;
	private boolean fueSolucionado;

	public boolean isFueSolucionado() {
		return fueSolucionado;
	}

	public void setFueSolucionado(boolean fueSolucionado) {
		this.fueSolucionado = fueSolucionado;
	}

	public Movil getMovil() {
		return movil;
	}

	public void setMovil(Movil movil) {
		this.movil = movil;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime localDateTime) {
		this.fecha = localDateTime;
	}

	public Observacion(String observacion, Movil movil) {
		this.setObs(observacion);
		this.setMovil(movil);
		this.setFecha(LocalDateTime.now());

	}
}
