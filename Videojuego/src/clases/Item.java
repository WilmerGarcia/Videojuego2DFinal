package clases;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Item extends Objetojuego{
	private boolean capturado;
	public Item(int x, int y, int ancho, int alto, String nombreImagen, int velocidad) {
		super(x,y,nombreImagen,velocidad);
	}

	public void mover(){
		if (Juego.derecha)
			setX(getX()-getVelocidad());
	}
	
	public void pintar(GraphicsContext graficos) {
		if (!capturado)
			graficos.drawImage(Juego.imagenes.get(nombreImagen), getX(), getY());
	}
	
	public Rectangle obtenerRectangulo() {
		return new Rectangle(getX(),getY(),18,18);
	}

	public boolean isCapturado() {
		return capturado;
	}

	public void setCapturado(boolean capturado) {
		this.capturado = capturado;
	}
}
