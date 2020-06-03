package clases;

import java.util.ArrayList;

import javafx.scene.shape.Rectangle;

public class Animacion {
	private double duracion;
	private Rectangle coordenadas[];
	public Animacion(double duracion, Rectangle coordenadas[]) {
		super();
		this.duracion = duracion;
		this.coordenadas = coordenadas;
	}
	public double getDuracion() {
		return duracion;
	}
	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}
	public Rectangle[] getCoordenadas() {
		return coordenadas;
	}
	public void setCoordenadas(Rectangle[] coordenadas) {
		this.coordenadas = coordenadas;
	}

	
	public Rectangle calcularframeactual(double t) {
		int cantidadframes=this.coordenadas.length;
		int indiceframeactual =(int)(t%(cantidadframes*duracion)/duracion);
		return coordenadas[indiceframeactual];
	}

}