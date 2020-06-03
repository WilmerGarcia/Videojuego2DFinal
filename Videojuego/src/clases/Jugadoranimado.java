package clases;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import implementacion.Juego;
import clases.Tile;
import clases.Disparo;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Font;

public class Jugadoranimado extends Objetojuego {
	private HashMap<String, Animacion>animaciones;
	private int ximagen;
	private int yimagen;
	private int anchoimagen;
	private int altoimagen;
	private static String animacionactual;
	public   int vidas=0;
	public int puntuacion = 0;
	private static int lifes=1;

	private static int direccion=1;
	

	public static int getDireccion() {
		return direccion;
	}

	public static void setDireccion(int direccion) {
		Jugadoranimado.direccion = direccion;
	}

	public Jugadoranimado(int x, int y, String nombreImagen, int velocidad, String animacionactual) {
		super(x, y, nombreImagen, velocidad);
		this.x = x;
		this.y = y;
		this.animacionactual=animacionactual;
		animaciones=new HashMap<String, Animacion>();
		inicializaranimaciones();
		this.velocidad = velocidad;
		
	}

	public void inicializaranimaciones() {
		Rectangle coordenadascorrer[]= {
				new Rectangle(6,100,45,50),
				new Rectangle(52,100,45,50),
				new Rectangle(102,100,45,50),
			
	};
		Animacion animacioncorrer=new Animacion(.04,coordenadascorrer);
		animaciones.put("correr", animacioncorrer);
		
		
		Rectangle coordenadasdescanso[]= {
				new Rectangle(6,100,45,50),
				new Rectangle(52,100,45,50),
			
			
	};
		Animacion animaciondescanso=new Animacion(0.8,coordenadasdescanso);
		animaciones.put("descanso", animaciondescanso);
		
		
		
		Rectangle coordenadasarriba[]= {
				new Rectangle(6,6,42,45),
				new Rectangle(54,6,42,45),
				new Rectangle(104,6,42,45),
				
		};
		Animacion animacionarriba=new Animacion(.05,coordenadasarriba);
		animaciones.put("arriba", animacionarriba);
		
		
		Rectangle coordenadasabajo[]= {
				new Rectangle(7,152,36,40),
				new Rectangle(54,152,36,40),
				new Rectangle(102,152,36,40),
				
		};
		Animacion animacionabajo=new Animacion(.05,coordenadasabajo);
		animaciones.put("abajo", animacionabajo);
		
}
	
	public void calcularframe(double t) {
		Rectangle coordenadas=animaciones.get(animacionactual).calcularframeactual(t);
		this.ximagen=(int)coordenadas.getX();
		this.yimagen=(int)coordenadas.getY();
		this.altoimagen=(int)coordenadas.getWidth();
		this.anchoimagen=(int)coordenadas.getHeight();
	}
	
	public Rectangle obtenerrectangulo() {
		
		return new Rectangle(x,y,(direccion*anchoimagen),altoimagen);
	}
	
	
	@Override	
	public void pintar(GraphicsContext graficos) {
		graficos.setFont(new Font("Comic Sans MS",22));
		graficos.strokeText("JUNGLE SURVIVAL", 600, 20);
		graficos.drawImage(Juego.imagenes.get(nombreImagen),ximagen,yimagen,anchoimagen,altoimagen,x+(direccion==-1?anchoimagen:0),y,direccion*70,70);
		graficos.setFont(new Font("Comic Sans MS",16));
		graficos.strokeText("Puntuacion: " + puntuacion, 20, 30);
	}
	
	@Override	
	public void mover() {
		if(x>1400)
			x=-80;
		if(Juego.derecha)
			x+=velocidad;
		if(Juego.izquierda)
			x-=velocidad;
		if(Juego.arriba)
			y-=velocidad;
		if(Juego.abajo)
			y+=velocidad;
	}



	
	public static String getAnimacionactual() {
		return animacionactual;
	}

	public static void setAnimacionactual(String animacionactual) {
		Jugadoranimado.animacionactual = animacionactual;
	}

	public void verificarColisiones(Itemanimado itemAnimado) {
		if (this.obtenerrectangulo().intersects(itemAnimado.obtenerRectangulo().getBoundsInLocal())) {
				if (!itemAnimado.isCapturado())
					this.puntuacion+=5;
					Juego.puntuacion=this.puntuacion;
				itemAnimado.setCapturado(true);		
	
		}	
	}
	
	public void verificarColisiones2(Item item) {
		if (this.obtenerrectangulo().intersects(item.obtenerRectangulo().getBoundsInLocal())) {
				if (!item.isCapturado())
					this.setLifes(this.getLifes() + 1);
				item.setCapturado(true);				
		}
	}
	
	
	
	public void verificarColisiones3(Enemigo1 item) {
		if (this.obtenerrectangulo().intersects(item.obtenerRectangulo().getBoundsInLocal())) {
			if(!item.isCapturado())
			this.setLifes(this.getLifes() - 1);
			item.setCapturado(true);	
			if (this.getLifes()==0)
			Juego.fin=true;
		}
	}
	
	
	
	public void verificarColisiones4(Enemigo2 item) {
		if (this.obtenerrectangulo().intersects(item.obtenerRectangulo().getBoundsInLocal())) {
		if (!item.isCapturado())
		this.setLifes(this.getLifes() - 1);
		item.setCapturado(true);	
		if (this.getLifes()==0)
		Juego.fin=true;
		}
	}

	public void verificarColisiones5(Enemigo3 item) {
		if (this.obtenerrectangulo().intersects(item.obtenerRectangulo().getBoundsInLocal())) {
			if (!item.isCapturado())
			this.setLifes(this.getLifes() - 1);
			item.setCapturado(true);	
			if (this.getLifes()==0)
			Juego.fin=true;
		}
	}

	
	
	public void verificarColisiones6(Item item) {
		if (this.obtenerrectangulo().intersects(item.obtenerRectangulo().getBoundsInLocal())) {
				if (!item.isCapturado())
					JOptionPane.showMessageDialog(null, "FIN DEL JUEGO CRACK");
					Juego.guardarPuntuaciones();
				item.setCapturado(true);
		}		
		
	}
	
	
	
	public void verificarColisiones7(Tile item) {
		if (item.obtenerRectangulo().intersects(this.obtenerrectangulo().getBoundsInLocal())) {
					if (!item.isCapturado())
					this.x=this.x-1;
					this.y=this.y+1;
			item.setCapturado(true);
		}
			
}

	
	public static int getLifes() {
		return lifes;
	}

	public void setLifes(int lifes) {
		this.lifes = lifes;
	}
	

}


	
