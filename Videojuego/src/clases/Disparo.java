package clases;

import java.util.ArrayList;
import java.util.HashMap;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Disparo extends Objetojuego{
	private boolean capturado;
	private static String animacionactual;
	private HashMap<String, Animacion> animaciones;
	private int z;
	private int xImagen;
	private int yImagen;
	private int anchoImagen;
	private int altoImagen;	
	
	private static int direccion=1;
		

	public static int getDireccion() {
		return direccion;
	}

	public static void setDireccion(int direccion) {
		Disparo.direccion = direccion;
	}

	public Disparo(int x, int y,String nombreImagen, int velocidad, String animacionactual) {
		super(x,y,nombreImagen,velocidad);
		this.x = x;
		this.y = y;
		this.animacionactual=animacionactual;
		animaciones=new HashMap<String, Animacion>();
		inicializarAnimaciones();
		this.capturado = capturado;
		this.velocidad = velocidad;
	}

	public void actualizarAnimaciones(double t) {
		Rectangle coordenadasActuales = this.animaciones.get(animacionactual).calcularframeactual(t);
		this.xImagen = (int)coordenadasActuales.getX();
		this.yImagen = (int)coordenadasActuales.getY();
		this.anchoImagen = (int)coordenadasActuales.getWidth();
		this.altoImagen = (int)coordenadasActuales.getHeight();
	}
	
	public void inicializarAnimaciones() {
			animaciones = new HashMap<String, Animacion>();
			Rectangle coordenadasdisparo[]= {
					new Rectangle(21,29,36,32),
					new Rectangle(71,29,36,32),
					new Rectangle(272,29,43,32),
					new Rectangle(340,30,43,32),	
					new Rectangle(130,32,47,28),
					new Rectangle(203,29,47,28),		
					
			};
			Animacion animaciondisparo=new Animacion(.10,coordenadasdisparo);
			animaciones.put("moverdisparo", animaciondisparo);
			
	}

	@Override
	public void mover(){
		if(x>1400)
			x=-80;
		if(Juego.disparar) 
			x+=20;
		}
	
	@Override
	public void pintar(GraphicsContext graficos) {
			if(Juego.disparar) {
			graficos.drawImage(Juego.imagenes.get(this.nombreImagen),xImagen,yImagen,anchoImagen,altoImagen,x+(direccion==-1?anchoImagen:0),y,direccion*anchoImagen,altoImagen);
			}		
	}
	
	public Rectangle obtenerRectangulo() {
		return new Rectangle(getX(), getY(), this.anchoImagen, this.altoImagen);
	}

	public boolean isCapturado() {
		return capturado;
	}

	public void setCapturado(boolean capturado) {
		this.capturado = capturado;
	}

	public static String getAnimacionactual() {
		return animacionactual;
	}

	public static void setAnimacionactual(String animacionactual) {
		Disparo.animacionactual = animacionactual;
	}
	
	
	public void verificarcolisiones(Enemigo1 disparo,ArrayList<Enemigo1>enemigos,int posicion) {
		if (disparo.obtenerRectangulo().intersects(this.obtenerRectangulo().getBoundsInLocal())) {
			if (!disparo.isCapturado()) {
			enemigos.remove(posicion);
			disparo.setCapturado(true);
			}
		}
	}
	
	
	public void verificarcolisiones2(Enemigo2 disparo,ArrayList<Enemigo2>enemigos,int posicion) {
		if (disparo.obtenerRectangulo().intersects(this.obtenerRectangulo().getBoundsInLocal())) {
			if (!disparo.isCapturado()) {
			enemigos.remove(posicion);
			disparo.setCapturado(true);
			}
		}
	}

	
	public void verificarcolisiones3(Enemigo3 disparo,ArrayList<Enemigo3>enemigos,int posicion) {
		if (disparo.obtenerRectangulo().intersects(this.obtenerRectangulo().getBoundsInLocal())) {
			if (!disparo.isCapturado()) {
			enemigos.remove(posicion);
			disparo.setCapturado(true);
			}
		}
	}
	
	
	
}