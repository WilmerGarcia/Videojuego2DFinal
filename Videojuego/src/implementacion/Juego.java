package implementacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

import clases.Disparo;
import clases.Enemigo1;
import clases.Enemigo2;
import clases.Enemigo3;
import clases.Fondo;
import clases.Item;
import clases.Itemanimado;
import clases.Jugador;
import clases.Jugadoranimado;
import clases.Jugadoresjuego;
import clases.Tile;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Juego extends Application{
	private GraphicsContext graficos;
	private Group root;
	private Scene escena;
	private Canvas lienzo;
	private Jugadoranimado jugadoranimado;
	private Fondo fondo;
	public static boolean arriba;
	public static boolean abajo;
	public static boolean derecha;
	public static boolean izquierda;
	public static boolean disparar;
	public static HashMap<String,Image>imagenes;
	private int randomx,randomy,randomy2;
	private int DX,DY;
	public static int puntuacion = 0;
	public static boolean fin=false;
	
	private ArrayList<Itemanimado> coins;
	private ArrayList<Item> vidas; 
	private ArrayList<Disparo> disparos;


	private ArrayList<Tile> tiles;
	private ArrayList<Tile> tiles2;
	private ArrayList<Enemigo1>enemigos1;
	private ArrayList<Enemigo2>enemigos2;
	private ArrayList<Enemigo3>enemigos3;
	private static ArrayList<Jugadoresjuego> jugadores;
	
	private Item V1,V2,V3;
	private Item fin1,fin2,fin3;
	private Item b1,b2,b3,b4,b5;
	private Tile a1,a2,a3,a4;
	
	public static int tilemap[][] = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,12,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,12,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,12,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,12,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,12,0},
			{20,20,20,20,20,20,20,20,20,20,20,20,20,20,1,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,1,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,1,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,1,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,1,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,1,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,1,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,1,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,1,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,1,20,20,20},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,4,4,4,4,4,4,4,4,4,4,4,0,0,0,0,4,4,4,0,4,4,4,0,0,0,1,1,1,1,1,1,0,0,4,4,4,4,4,4,4,0,4,4,4,0,0,0,0,0,0,0,0,4,4,0,4,4,0,0,0,1,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,4,4,4,4,4,0,4,4,0,0,0,0,0,4,4,4,0,4,4,4,0,0,0,1,1,1,1,1,1,0,0,4,4,4,4,4,4,4,0,4,4,0,0,0,0,0,0,0,0,0,4,4,0,4,4,0,0,0,0,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,1,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,3,6,6,3,6,6,3,6,0,0,0,3,3,0,3,3,6,3,3,6,3,3,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,4,3,0,0,0,0,0,0,0,1,1,2,2,2,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,6,6,3,6,6,3,6,6,3,6,0,0,0,0,3,6,3,3,6,3,3,6,3,3,6,3},
			{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
			
	};
	
	
	public static int tilemap2[][] = {
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,17,0,0,0,0,0,21,0,0,0,0,0,22,0,0,0,0,0,18,0,0,0,0,0,19,0,0,0,0,0,17,0,0,0,0,0,22,0,0,0,0,0,18,0,0,0,0,0,21,0,0,0,0,0,17,0,0,0,0,0,21,0,0,0,0,0,22,0,0,0,0,0,18,0,0,0,0,0,19,0,0,0,0,0,17,0,0,0,0,0,22,0,0,0,0,0,22,0,0,0,0,19,0,0,0,0,0,22,0,0,0,0,0,19,0,0,0,0,0,18,0,0,0,0,0,17,0,0,0,0,0,21,0,0,0,0,0,19,0,0,0,0,0,22,0,0,0,0,0,17,0,0,0,0,18,0,0,0,0,0,17,0,0,0,0,0,21,0,0,0,0,0,22,0,0,0,0,0,18,0,0,0,0,0,19,0,0,0,0,0,17,0,0,0,0,0,22},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,13,0,0,0,0,0,0,0,0,0,0,16,0,0,0,0,0,0,0,0,0,0,0,13,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,13,0,0,0,0,0,0,0,0,0,0,13,0,0,0,0,0,0,0,0,0,0,16,0,0,0,0,0,0,0,0,0,0,0,13,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,13,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,14,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,15,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,16,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,15,0,0,0,0,0,0,0,0,0,0,0,0,0,13,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,15,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,13,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,16,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,15,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,14,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,14,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,16,0,0,0,0,0,0,0,0,0,0,0,0,0,0,16,0,0,0,0,0,0,0,0,0,0,0,13,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,13,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,14,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,22,0,0,0,0,0,21,0,0,0,0,0,19,0,0,0,0,0,18,0,0,0,0,0,17,0,0,0,0,0,22,0,0,0,0,0,21,0,0,0,0,0,19,0,0,0,0,0,18,0,0,0,0,0,17,0,0,0,0,0,22,0,0,0,0,0,21,0,0,0,0,0,19,0,0,0,0,0,18,0,0,0,0,0,17,0,0,0,0,0,22,0,0,0,0,0,21,0,0,0,0,19,0,0,0,0,0,18,0,0,0,0,0,17,0,0,0,0,0,22,0,0,0,0,0,21,0,0,0,0,0,19,0,0,0,0,0,18,0,0,0,0,0,17,0,0,0,0,0,22,0,0,0,0,21,0,0,0,0,0,22,0,0,0,0,0,21,0,0,0,0,0,19,0,0,0,0,0,18,0,0,0,0,0,17,0,0,0,0,0,22,0,0,0,0,0,21},
			{0,0,0,0,0,0,0,0,0,0},
			
	};
	
	

	public static void main(String[] args) {
		launch(args);	
	}
	
	
	
	@Override
	public void start(Stage ventana) throws Exception {
		inicializarcomponentes();
		root.getChildren().add(lienzo);
		graficos=lienzo.getGraphicsContext2D();
		gestioneventos();
		ventana.setScene(escena);
		ventana.setTitle("Jungle Survival");
		ventana.show();
		jugadores=new ArrayList<Jugadoresjuego>();
		ciclojuego();
		
	}
	
	
	
	
		public void ciclojuego() {
			long tiempoInicial=System.nanoTime();
			AnimationTimer animationtimer= new AnimationTimer() {

				@Override
				public void handle(long tiempoActual) {
					double t=(tiempoActual-tiempoInicial)/1000000000.0;
					if(!Juego.fin) {
					pintar();
					actualizarestado(t);
					
				}
					else {
					guardarPuntuaciones();
					}
				}
				
			};
			
			animationtimer.start();
		}
		
		
		
		
		public void actualizarestado(double t) {

			for(int z=0;z<coins.size();z++) {
				coins.get(z).mover();
				coins.get(z).calcularframe(t);
			}
			
			for (int i=0;i<coins.size();i++)
				jugadoranimado.verificarColisiones(coins.get(i));
			
			
			for (int z=0;z<3;z++) 
				vidas.get(z).mover();
			
			for (int i=0;i<vidas.size();i++)
				jugadoranimado.verificarColisiones2(vidas.get(i));
			
			
			for(int z=0;z<enemigos1.size();z++) {
				enemigos1.get(z).mover();
				enemigos1.get(z).actualizarAnimacion(t);
			}
			
			
			for (int i=0;i<enemigos1.size();i++) 
				jugadoranimado.verificarColisiones3(enemigos1.get(i));
				
			
			for(int z=0;z<enemigos2.size();z++) {
				enemigos2.get(z).mover();
				enemigos2.get(z).actualizarAnimacion(t);
		}
			
			for (int i=0;i<enemigos2.size();i++)
				jugadoranimado.verificarColisiones4(enemigos2.get(i));
			
			for(int z=0;z<enemigos3.size();z++) {
				enemigos3.get(z).mover();
				enemigos3.get(z).actualizarAnimacion(t);
			}
			
			for (int i=0;i<enemigos3.size();i++) {
				jugadoranimado.verificarColisiones5(enemigos3.get(i));
			}
			
			for(int i=0;i<tilemap.length;i++) {	
				for(int j=0;j<tilemap[i].length;j++) {
					if(tilemap[i][j]!=0)
					jugadoranimado.verificarColisiones7(tiles.get(i));
					jugadoranimado.verificarColisiones7(tiles.get(j));
			} 
		}
			
	
			fin1.mover();
			fin2.mover();
			fin3.mover();
			
			b1.mover();
			b2.mover();
			b3.mover();
			b4.mover();
			b5.mover();
			
			jugadoranimado.verificarColisiones6(fin1);
			jugadoranimado.verificarColisiones6(fin2);
			jugadoranimado.verificarColisiones6(fin3);
			
			jugadoranimado.calcularframe(t);
			jugadoranimado.mover();
			fondo.mover();
			
			
			for(int z=0;z<disparos.size();z++) {
				disparos.get(z).mover();
				disparos.get(z).actualizarAnimaciones(t);
			}
			
			
			for(int z=0;z<disparos.size();z++) {
			disparos.get(z).verificarcolisiones(enemigos1.get(z),enemigos1,z);
			disparos.get(z).verificarcolisiones2(enemigos2.get(z),enemigos2,z);
			disparos.get(z).verificarcolisiones3(enemigos3.get(z),enemigos3,z);
			}		
		}
	
		
		private static void leerPuntuaciones() {
			String linea="";
			String informacion="";
			try {
				BufferedReader flujo = new BufferedReader(new FileReader("puntuacion.csv"));
				while ((linea=flujo.readLine())!=null) {
					String campo[]=linea.split(",");
					jugadores.add(new Jugadoresjuego(campo[0],Integer.parseInt(campo[1])));
					informacion+=campo[0]+":"+campo[1]+"\n";
				}
				flujo.close();
			}
			catch (FileNotFoundException e) {
				System.out.println("sin puntuaciones");
			}catch (IOException e) {
				System.out.println(".");
			}

			JOptionPane.showMessageDialog(null, "puntuacion:"+"\n"+informacion);
			
		}
		
		public static void guardarPuntuaciones() {
			jugadores.add(new Jugadoresjuego(JOptionPane.showInputDialog("Ingrese su nombre:"),puntuacion));
			try {
				BufferedWriter archivo=new BufferedWriter(new FileWriter("puntuacion.csv",true));
				archivo.write(Jugadoresjuego.toCSV());
				archivo.flush();
				archivo.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
			leerPuntuaciones();
			System.exit(0);
		}
		
		
		public void inicializarcomponentes() {
			imagenes=new HashMap<String,Image>();
			cargarimagenes();
			jugadoranimado=new Jugadoranimado(20,530,"personaje",5,"descanso");
			
			disparos=new ArrayList<Disparo>();
			for(int i=0;i<100;i++)
			
		
			fondo=new Fondo(0,0,"fondo","fondo2", 6);
			
			V1=new Item(1200,5,0,0,"vida",0);
			V2=new Item(1240,5,0,0,"vida",0);
			V3=new Item(1280,5,0,0,"vida",0);
			
			b1=new Item(640,100,0,0,"boton1",0);
			b2=new Item(700,100,0,0,"boton2",0);
			b3=new Item(760,100,0,0,"boton3",0);
			b4=new Item(700,45,0,0,"boton4",0);
			b5=new Item(700,155,0,0,"boton5",0);
			
			
			coins = new ArrayList<Itemanimado>();
			for (int z=0;z<40;z++) {
				randomy2=(int)(Math.random()*3+1);
				randomx=(int)(Math.random()*15000+1000);
				if (randomy2==1)
					randomy=300;
				if(randomy2==2)
					randomy=450;
				if(randomy2==3)
					randomy=650;
				coins.add(new Itemanimado(randomx,randomy,0,0,"monedas",1,0,"mover"));
				
			}
			
			
			
			vidas=new ArrayList<Item>();
			for (int z=0;z<3;z++) {
				randomy2=(int)(Math.random()*3+1);
				randomx=(int)(Math.random()*3000+3000);
				if (randomy2==1)
					randomy=300;
				if (randomy2==2)
					randomy=450;
				if (randomy2==3)
					randomy=650;
				vidas.add(new Item(randomx,randomy,0,0,"vida",4));
				
			}
			
				fin1 = new Item(10000,210,0,0,"fin",4);
				fin2 = new Item(10000,420,0,0,"fin",4);
				fin3 = new Item(10000,630,0,0,"fin",4);
				
							
				
				enemigos1=new ArrayList<Enemigo1>();
				for (int z=0;z<45;z++) {
					randomy2=(int)(Math.random()*3+1);
					randomx=(int)(Math.random()*26000+3000);
					if (randomy2==1)
						randomy=280;
					if (randomy2==2)
						randomy=500;
					enemigos1.add(new Enemigo1(randomx,randomy,"enemigo1",1,"mover"));
			}
				
				
				
				enemigos2=new ArrayList<Enemigo2>();
				for (int z=0;z<45;z++) {
					randomy2=(int)(Math.random()*3+1);
					randomy=(int)(Math.random()*26000+3000);
					if (randomy2==1)
						randomx=300;
					if (randomy2==2)
						randomx=1000;
					enemigos2.add(new Enemigo2(randomx,randomy,"enemigo2",1,"mover"));
			}
			
				
				enemigos3=new ArrayList<Enemigo3>();
				for (int z=0;z<45;z++) {
					randomy2=(int)(Math.random()*3+1);
					randomx=(int)(Math.random()*26000+3000);
					if (randomy2==1)
						randomy=400;
					if (randomy2==2)
						randomy=650;
					enemigos3.add(new Enemigo3(randomx,randomy,"enemigo3",1,"mover"));
			}
				
											

			inicializartiles();
			inicializartiles2();
			root= new Group();
			escena=new Scene(root,1400,700);
			lienzo=new Canvas(1400,700);
			
		}
		
		public void inicializartiles() {
			tiles=new ArrayList<Tile>();
			for(int i=0;i<tilemap.length;i++) {
				for(int j=0;j<tilemap[i].length;j++) {
					if(tilemap[i][j]!=0)
						this.tiles.add(new Tile(tilemap[i][j],j*70,i*69,"tilemap",4));
				}}}
				
			public void inicializartiles2() {
				tiles2=new ArrayList<Tile>();
				for(int i=0;i<tilemap2.length;i++) {
				for(int j=0;j<tilemap2[i].length;j++) {
					if(tilemap2[i][j]!=0)
					this.tiles.add(new Tile(tilemap2[i][j],j*70,i*69,"tilemap2",4));
					
				}}}
		
		
		public void cargarimagenes() {
			imagenes.put("vaquero",new Image("vaquero.png" ));
			imagenes.put("goku-furioso",new Image("goku-furioso.png"));
			imagenes.put("fondo",new Image("fondo.jpg"));
			imagenes.put("fondo2",new Image("fondo2.jpg"));
			imagenes.put("tilemap",new Image("tilemap.png"));
			imagenes.put("tilemap2",new Image("tilemap2.png"));
			imagenes.put("personaje", new Image("personaje.png"));
			imagenes.put("monedas",new Image("monedas.png"));
			imagenes.put("vida", new Image("vida.png"));
			imagenes.put("enemigo1", new Image("enemigo1.png"));
			imagenes.put("enemigo2", new Image("enemigo2.png"));
			imagenes.put("enemigo3", new Image("enemigo3.png"));
			imagenes.put("disparos",new Image("disparos.png"));
			imagenes.put("boton1",new Image("boton1.png"));
			imagenes.put("boton2",new Image("boton2.png"));
			imagenes.put("boton3",new Image("boton3.png"));
			imagenes.put("boton4",new Image("boton4.png"));
			imagenes.put("boton5",new Image("boton5.png"));
			imagenes.put("fin",new Image("fin.png"));
		};

		public void pintar() {
		
		fin1.pintar(graficos);
		fin2.pintar(graficos);
		fin3.pintar(graficos);	
				
		fondo.pintar(graficos);
		
		for (int i=0;i<coins.size();i++)
			coins.get(i).pintar(graficos);
			
		
		jugadoranimado.pintar(graficos);
		
		for (int i=0;i<enemigos1.size();i++)
		enemigos1.get(i).pintar(graficos);
		
		for (int i=0;i<enemigos3.size();i++)
			enemigos3.get(i).pintar(graficos);
		
		
		for (int i=0;i<tiles.size();i++)
			tiles.get(i).pintar(graficos);
		
		for (int i=0;i<tiles2.size();i++)
			tiles2.get(i).pintar(graficos);
		
		
		for (int i=0;i<disparos.size();i++) {
			if(i==disparos.size()) {
			disparos.get(0).pintar(graficos);
		}else
			if(0!=disparos.size()) {
				int ultimodisparo=(disparos.size())-1;
				disparos.get(ultimodisparo).pintar(graficos);
			}
		}
		
		if (jugadoranimado.getLifes()==2) {
			V1.pintar(graficos);
		} 
		if (jugadoranimado.getLifes()==3) {
			V1.pintar(graficos);
			V2.pintar(graficos);
		}
		
		if (jugadoranimado.getLifes()==4) {
			V1.pintar(graficos);
			V2.pintar(graficos);
			V3.pintar(graficos);
		}
			
		for (int i=0;i<vidas.size();i++)
		vidas.get(i).pintar(graficos);
		
		
		for (int i=0;i<enemigos2.size();i++)
			enemigos2.get(i).pintar(graficos);
		
		
		b1.pintar(graficos);
		b2.pintar(graficos);
		b3.pintar(graficos);
		b4.pintar(graficos);
		b5.pintar(graficos);
	}
		
		public void gestioneventos() {
		escena.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent evento) {
				switch(evento.getCode().toString()) {
				case "RIGHT":
					derecha=true;
					jugadoranimado.setDireccion(1);
					jugadoranimado.setAnimacionactual("correr");
					break;
				case "LEFT":
					izquierda=true;
					jugadoranimado.setDireccion(-1);
					jugadoranimado.setAnimacionactual("correr");
					break;
				case "UP":
					jugadoranimado.setDireccion(1);
					jugadoranimado.setAnimacionactual("abajo");
					arriba=true;
					break;
				case "DOWN":
					jugadoranimado.setDireccion(1);
					jugadoranimado.setAnimacionactual("arriba");
					abajo=true;
					break;
				case "SPACE":
					disparar=true; 
					DX=jugadoranimado.getX();
					DY=jugadoranimado.getY();
					disparos.add(new Disparo(DX+50,DY+20,"disparos",2,"moverdisparo"));
					break;	
				}
			}
			
		});	
		escena.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent evento) {
				switch(evento.getCode().toString()) {
				case "RIGHT":
					jugadoranimado.setAnimacionactual("descanso");
					derecha=false;
					break;
				case "LEFT":
					jugadoranimado.setAnimacionactual("descanso");
					izquierda=false;
					break;
				case "UP":
					jugadoranimado.setAnimacionactual("descanso");
					arriba=false;
					break;
				case "DOWN":
					jugadoranimado.setAnimacionactual("descanso");
					abajo=false;
					break;
				case "SPACE":
					disparar=false;
					break;		
			}
			
		}
		
		});
		
		}
	
				
}
