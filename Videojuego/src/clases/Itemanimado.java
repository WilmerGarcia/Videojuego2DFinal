package clases;

import java.util.HashMap;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Itemanimado extends Objetojuego{
	private boolean capturado=false;
	public String animacionActual;
	private HashMap<String, Animacion> animaciones;
	private int xImagen;
	private int yImagen;
	private int anchoImagen;
	private int altoImagen;	
	private int cantidadvidas;
	
	public Itemanimado(int x, int y,int ancho, int alto, String nombreImagen,int velocidad, int cantidadvidas,String animacionActual) {
		super(x, y, nombreImagen, velocidad);
		this.setCantidadvidas(cantidadvidas);
		this.animacionActual=animacionActual;
		animaciones=new HashMap<String, Animacion>();
		inicializarAnimaciones();
	}

	public int getCantidadvidas() {
		return cantidadvidas;
	}

	public void setCantidadvidas(int cantidadvidas) {
		this.cantidadvidas = cantidadvidas;
	}

	public void calcularframe(double t) {
		Rectangle coordenadas = this.animaciones.get(animacionActual).calcularframeactual(t);
		this.xImagen = (int)coordenadas.getX();
		this.yImagen = (int)coordenadas.getY();
		this.anchoImagen = (int)coordenadas.getWidth();
		this.altoImagen = (int)coordenadas.getHeight();
	}
	
	public void inicializarAnimaciones() {
			animaciones = new HashMap<String, Animacion>();
			Rectangle coordenadasMover[]= {
					new Rectangle(8,8,40,40),
					new Rectangle(49,9,40,40),
					new Rectangle(93,10,40,40),
					new Rectangle(12,52,40,40),
					new Rectangle(48,51,40,40),
					new Rectangle(90,53,40,40)
			};
			
			Animacion animacionMover = new Animacion(0.1,coordenadasMover);
			animaciones.put("mover",animacionMover);
	}
	
	
	
	public String getAnimacionActual() {
		return animacionActual;
	}

	public void setAnimacionActual(String animacionActual) {
		this.animacionActual = animacionActual;
	}
	
	@Override
	public void mover(){
		if (Juego.derecha)
			setX(getX()-getVelocidad());
	}
	
	@Override
	public void pintar(GraphicsContext graficos) {
		if (this.capturado)
			return;
		else {
			graficos.drawImage(Juego.imagenes.get(this.nombreImagen), this.xImagen, this.yImagen, this.anchoImagen, this.altoImagen,getX(), getY(),this.anchoImagen, this.altoImagen);
		}
	}
	
	public Rectangle obtenerRectangulo() {
		return new Rectangle(getX(),getY(), this.anchoImagen, this.altoImagen);
	}

	public boolean isCapturado() {
		return capturado;
	}

	public void setCapturado(boolean capturado) {
		this.capturado = capturado;
	}
	
	
}
