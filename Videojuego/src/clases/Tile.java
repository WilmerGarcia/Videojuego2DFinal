package clases;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Objetojuego{
	private int xImagen;
	private int yImagen;
	private int tipotile;
	private boolean capturado;

	public Tile(int tipotile,int x, int y, String nombreImagen, int velocidad) {
	super(x, y, nombreImagen, velocidad);
	this.alto=alto;
	this.ancho=ancho;
	this.capturado = capturado;
	
	
	switch(tipotile) {
	case 1:
		this.xImagen=0;
		this.yImagen=0;
		this.ancho=70;
		this.alto=70;
		break;
	case 2:
		this.xImagen=0;
		this.yImagen=70;
		this.ancho=70;
		this.alto=70;
		break;
	case 3:
		this.xImagen=0;
		this.yImagen=140;
		this.ancho=70;
		this.alto=70;
		break;
	case 4:
		this.xImagen=0;
		this.yImagen=420;
		this.ancho=70;
		this.alto=70;
		break;
		
	case 5:
		this.xImagen=490;
		this.yImagen=560;
		this.ancho=70;
		this.alto=70;
		break;
		
	case 6:
		this.xImagen=70;
		this.yImagen=560;
		this.ancho=70;
		this.alto=70;
		break;
		
	case 7:
		this.xImagen=0;
		this.yImagen=420;
		this.ancho=70;
		this.alto=70;
		break;
		
	case 8:
		this.xImagen=560;
		this.yImagen=280;
		this.ancho=70;
		this.alto=70;
		break;	
		
	case 9:
		this.xImagen=210;
		this.yImagen=490;
		this.ancho=70;
		this.alto=70;
		break;	
		
	case 10:
		this.xImagen=490;
		this.yImagen=210;
		this.ancho=70;
		this.alto=70;
		break;	
		
	case 11:
		this.xImagen=700;
		this.yImagen=280;
		this.ancho=70;
		this.alto=70;
		break;	
		
	case 12:
		this.xImagen=280;
		this.yImagen=210;
		this.ancho=70;
		this.alto=70;
		break;	
	//arbol1	
	case 13:
		this.xImagen=262;
		this.yImagen=130;
		this.ancho=87;
		this.alto=123;
		break;
		//arbol2
	case 14:
		this.xImagen=447;
		this.yImagen=131;
		this.ancho=65;
		this.alto=123;
		break;
		
		//arbol3
	case 15:
		this.xImagen=255;
		this.yImagen=255;
		this.ancho=96;
		this.alto=101;
		break;
		//arbol4
	case 16:
		this.xImagen=362;
		this.yImagen=142;
		this.ancho=75;
		this.alto=111;
		break;	
		
	case 17:
		this.xImagen=10;
		this.yImagen=456;
		this.ancho=46;
		this.alto=53;
		break;	
		
	case 18:
		this.xImagen=69;
		this.yImagen=479;
		this.ancho=54;
		this.alto=33;
		break;	
		
	case 19:
		this.xImagen=73;
		this.yImagen=450;
		this.ancho=49;
		this.alto=30;
		break;
		
	case 21:
		this.xImagen=100;
		this.yImagen=256;
		this.ancho=65;
		this.alto=33;
		break;
		
	case 22:
		this.xImagen=99;
		this.yImagen=287;
		this.ancho=66;
		this.alto=36;
		break;
		
	case 23:
		this.xImagen=0;
		this.yImagen=0;
		this.ancho=96;
		this.alto=161;
		break;
		
	case 24:
		this.xImagen=94;
		this.yImagen=0;
		this.ancho=99;
		this.alto=128;
		break;	
		
	case 20:
		this.xImagen=560;
		this.yImagen=840;
		this.ancho=70;
		this.alto=70;
		break;


	}
	
}

	public int getxImagen() {
		return xImagen;
	}

	public void setxImagen(int xImagen) {
		this.xImagen = xImagen;
	}

	public int getyImagen() {
		return yImagen;
	}

	public void setyImagen(int yImagen) {
		this.yImagen = yImagen;
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		if (Juego.derecha)
			this.x-=this.velocidad;
		graficos.drawImage(Juego.imagenes.get(nombreImagen), xImagen, yImagen,ancho,alto,x,y,ancho,alto);
	}

	@Override
	public void mover() {
		
	}
	
	public Rectangle obtenerRectangulo() {
		return new Rectangle(getX(),getY(), this.ancho, this.alto);
	}
	
	
	public boolean isCapturado() {
		return capturado;
	}

	public void setCapturado(boolean capturado) {
		this.capturado = capturado;
	}

	
	
	
}



