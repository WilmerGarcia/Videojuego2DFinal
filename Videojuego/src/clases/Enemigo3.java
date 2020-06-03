package clases;

import java.util.HashMap;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Enemigo3 extends Objetojuego{
	private boolean capturado;
	public static String animacionactual;
	private HashMap<String, Animacion> animaciones;
	private int z;
	private int xImagen;
	private int yImagen;
	private int anchoImagen;
	private int altoImagen;	
		
	public Enemigo3(int x, int y,String nombreImagen, int velocidad, String animacionactual) {
		super(x,y,nombreImagen,velocidad);
		this.capturado = capturado;
		this.animacionactual=animacionactual;
		inicializarAnimaciones();
	}

	public void actualizarAnimacion(double t) {
		Rectangle coordenadasActuales = this.animaciones.get(animacionactual).calcularframeactual(t);
		this.xImagen = (int)coordenadasActuales.getX();
		this.yImagen = (int)coordenadasActuales.getY();
		this.anchoImagen = (int)coordenadasActuales.getWidth();
		this.altoImagen = (int)coordenadasActuales.getHeight();
	}
	
	public void inicializarAnimaciones() {
			animaciones = new HashMap<String, Animacion>();
			Rectangle coordenadasMover[]= {
					new Rectangle(1,132,61,62),
					new Rectangle(64,129,61,62),
					new Rectangle(127,131,61,62),
					
			};
			Animacion animacionmover=new Animacion(.05,coordenadasMover);
			animaciones.put("mover", animacionmover);
					
	}

	@Override
	public void mover(){
		if (Juego.derecha)
			setX(getX()-getVelocidad());
	}
	
	@Override
	public void pintar(GraphicsContext graficos) {
		if (!capturado) {
			z=getX();
			setX(z-=2);
			graficos.drawImage(
			Juego.imagenes.get(this.nombreImagen), this.xImagen, this.yImagen,this.anchoImagen, this.altoImagen,z*(-1),getY(),this.anchoImagen, this.altoImagen);
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
}
