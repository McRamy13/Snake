/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author usuario
 */
public class Snake {

    //Inicializar una matriz para el tablero. En el ajedrez sería de 8x8
    private char[][] tablero;
    private int tamFila;
    private int tamCol;
    //Declaramos un ArrayList de puntos (posición)
    private ArrayList<Point> listaPosSnake = new ArrayList();
    private byte sentidoMov;
    private Random random = new Random();
    public static final char VACIO = '.';
    public static final char CABEZA = '@';
    public static final char CUERPO = '=';
    public static final char MURO = '#';
    public static final char FRUTA = '$';

    public static final int MARGEN = 2;
    //El tamaño inicial  del cuerpo no puede ser mayor que el margen
    public static final int TAM_INI_CUERPO = 2;

    public static final byte DERECHA = 0;
    public static final byte IZQUIERDA = 1;
    public static final byte ARRIBA = 2;
    public static final byte ABAJO = 3;
    
    /*
     Información contenida en el tablero:
     ' ': Vacío
     '@': Cabeza
     '=': Cuerpo
     '#': Muro
     '$': Fruta
     */
    public Snake(int tamFila, int tamCol) {
        //Asignar el valor de los datos para guardarlos arriba y que se puedan tener en otro método.
        this.tamFila = tamFila;
        this.tamCol = tamCol;
        tablero = new char[tamFila][tamCol];
        //Inicializamos el tablero con todo vacío
        for (int f = 0; f < tamFila; f++) {
            for (int c = 0; c < tamCol; c++) {
                tablero[f][c] = VACIO;
            }
        }
        //Colocar la cabeza en posición aleatoria.
        //Ponemos menos cuatro para que nos de un rango de valores válidos 
        //para que podamos poner la cabeza dentro de los márgenes.
        int cabezaFila = random.nextInt(tamFila - 2 * MARGEN) + MARGEN;
        int cabezaCol = random.nextInt(tamCol - 2 * MARGEN) + MARGEN;

        //PAra el ajedrez habría que poner estas líneas de manera reiterada sin necesidad de hacer números aleatorios
        tablero[cabezaFila][cabezaCol] = CABEZA;
        //Añadir la cabeza a la posición

        listaPosSnake.add(new Point(cabezaCol, cabezaFila));
        //Colocar el cuerpo
        for (int i = 1; i <= TAM_INI_CUERPO; i++) {
            tablero[cabezaFila][cabezaCol - i] = CUERPO;

            listaPosSnake.add(new Point(cabezaCol - i, cabezaFila));
        }
        generarFruta();
    }
    public void setSentidoMov(byte sentidoMov) {
        this.sentidoMov = sentidoMov;
    }
    
    private void generarFruta(){
        int fila = random.nextInt(tamFila);
        int col = random.nextInt(tamCol);
        
        tablero[fila][col] = FRUTA;
    }

    public String toString() {
        String retorno = "";
        for (int f = 0; f < tamFila; f++) {
            for (int c = 0; c < tamCol; c++) {
                retorno += tablero[f][c];
            }
            retorno += '\n';
        }
        return retorno;
    }

    public boolean mover() {
        for (Point p : listaPosSnake) {
            System.out.println(p);
        }
        Point nuevaCabeza = null;
        //guardar posición actual de la cabeza
        listaPosSnake.get(0);
        Point antiguaCabeza = listaPosSnake.get(0);

        //De la lista cogemos la última posición
        //Borrar la cola antigua
        Point antiguaCola = listaPosSnake.get(listaPosSnake.size() - 1);
        tablero[antiguaCola.y][antiguaCola.x] = VACIO;
        listaPosSnake.remove(listaPosSnake.size() - 1);

        // //mover el punto de la cabeza
        tablero[antiguaCabeza.y][antiguaCabeza.x] = CUERPO;
        switch (sentidoMov) {
            case DERECHA:
                nuevaCabeza = new Point(antiguaCabeza.x + 1, antiguaCabeza.y);
                break;
            case IZQUIERDA:
                nuevaCabeza = new Point(antiguaCabeza.x - 1, antiguaCabeza.y);
                break;
            case ARRIBA:
                nuevaCabeza = new Point(antiguaCabeza.x, antiguaCabeza.y - 1);
                break;
            case ABAJO:
                nuevaCabeza = new Point(antiguaCabeza.x, antiguaCabeza.y + 1);
                break;

        }

        tablero[nuevaCabeza.y][nuevaCabeza.x] = CABEZA;
        listaPosSnake.add(0, nuevaCabeza);

        for (Point p : listaPosSnake) {
            System.out.println(p);
        }

        return true;

    }

    

}
