package clases;

import java.io.Serializable;

import implementacion.Juego;

public class Jugadoresjuego implements Serializable{
	private static String nombre;
	private static int puntuacion;
	
	public Jugadoresjuego(String nombre, int puntuacion) {
		this.nombre = nombre;
		this.puntuacion=puntuacion;
	}

	public Jugadoresjuego() {}
	
	public static String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public static int getPuntuacion() {
		return puntuacion;
	}

	public static void setPuntuacion(int puntuacion) {
		Jugadoresjuego.puntuacion = puntuacion;
	}

	public static String toCSV() {
		return getNombre()+","+getPuntuacion()+"\n";
	}
}