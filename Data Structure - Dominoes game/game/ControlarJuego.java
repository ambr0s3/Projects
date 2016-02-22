package game;

import library.CircularLinkedList;
import library.IList;
import library.SimpleLinkedList;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by AS
 */
public class ControlarJuego {

    public static final FichasDomino[][] gameBoard = new FichasDomino[14][14]; //Creacion de tablero
    public static int[] puntajeAcumulado; //Puntaje Total

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        puntajeAcumulado = new int[4];
        for (int i = 0; i < puntajeAcumulado.length; i++) {
            puntajeAcumulado[i] = 0;
        }

        System.out.println("Proyecto Final Estructura de Datos.\n");
        System.out.println("\tDOMINOE'S\n");

        int option = -1;
        while (option != 0) {
            gameLoop();

            System.out.println("Quiere seguir jugando? 1 - Si 0 - No.");
            option = scanner.nextInt();
        }
    }

    private static void gameLoop() throws Exception  //Ciclo del juego
    {
        for (int i = 0; i < 14; i++) 
        {
            for (int j = 0; j < 14; j++) 
            {
                gameBoard[i][j] = new FichasDomino(-1, -1);
            }
        }

        boolean gameOver = false;
        int numTurns = 0;
        CircularLinkedList<Jugador> jugadores = new CircularLinkedList<>();
        Jugador jugador = new Jugador("JUGADOR", false);
        Jugador com1 = new Jugador("COM1", true); //Computadora 1
        Jugador com2 = new Jugador("COM2", true); //Computadora 2
        Jugador com3 = new Jugador("COM3", true); //Computadora 3

        configurarJugadores(new Jugador[] {jugador, com1, com2, com3});
        int half = 14 / 2 - 1;

        jugador.setPosition(half, half);
        jugador.setDirection(1, 0);

        com1.setPosition(half, half);
        com1.setDirection(-1, 0);

        com2.setPosition(half, half);
        com2.setDirection(0, 1);

        com3.setPosition(half, half);
        com3.setDirection(0, -1);

        startGameManual(jugadores, new Jugador[]{jugador, com1, com2, com3});
        numTurns++;

        while (!gameOver) {

            clearConsole();
            Jugador jugadoresActivos = jugadores.first();

            if (jugadoresActivos.canContinue()) {
                FichasDomino domino = jugadoresActivos.makeMove();

                if (domino != null) {
                    int r = jugadoresActivos.getPosX();
                    int c = jugadoresActivos.getPosY();
                    gameBoard[r][c] = domino;
                    jugadoresActivos.setNextPosition();
                    jugadoresActivos.removerFichaDomino(domino);
                }
            }

            if (isGameOver(jugadores))
                gameOver = true;

            System.out.println(jugadoresActivos.getNombre() + "'s" + " turn");
            printGameBoard();

            jugadores.rotate();
            numTurns++;
        }

        computeScore(new Jugador[] {jugador, com1, com2, com3});
    }

    private static void configurarJugadores(Jugador... jugadores) throws Exception {
//        jugador.agregarFicha(6, 6);
//        jugador.agregarFicha(6, 1);
//        jugador.agregarFicha(5, 1);
//        jugador.agregarFicha(5, 0);
//        jugador.agregarFicha(2, 2);
//        jugador.agregarFicha(4, 2);
//        jugador.agregarFicha(1, 3);
//
//        com1.agregarFicha(6, 3);
//        com1.agregarFicha(3, 1);
//        com1.agregarFicha(5, 1);
//        com1.agregarFicha(5, 0);
//        com1.agregarFicha(2, 2);
//        com1.agregarFicha(4, 2);
//        com1.agregarFicha(5, 3);
//
//        com2.agregarFicha(1, 1);
//        com2.agregarFicha(3, 1);
//        com2.agregarFicha(5, 1);
//        com2.agregarFicha(5, 0);
//        com2.agregarFicha(2, 2);
//        com2.agregarFicha(0, 2);
//        com2.agregarFicha(6, 0);
//
//        com3.agregarFicha(6, 5);
//        com3.agregarFicha(1, 1);
//        com3.agregarFicha(5, 1);
//        com3.agregarFicha(5, 0);
//        com3.agregarFicha(5, 2);
//        com3.agregarFicha(4, 2);
//        com3.agregarFicha(5, 3);
        IList<FichasDomino> fichasDomino = new SimpleLinkedList<>();
        for (int i = 0, v = 0; i < 7; i++) {
            for (int j = v; j < 7; j++) {

                fichasDomino.addLast(new FichasDomino(i, j));
            }
            v++;
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 7; j++) {
                int rand = genRandom(0, fichasDomino.size() - 1);
                jugadores[i].agregarFicha(fichasDomino.get(rand));
                fichasDomino.remove(rand);
            }
        }
    }

    public static int genRandom(int min, int max) //Genera una ficha aleatoria de min a max
    {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    private static void printGameBoard() 
    {
        System.out.println("**TABLERO**");
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    private static boolean isGameOver(CircularLinkedList<Jugador> jugadores) throws Exception //Determina si el juego se acabo.
    {
        int cnt = 0;
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugador = jugadores.get(i);
            if (!jugador.canContinue())
                cnt++;
        }

        return cnt == jugadores.size();
    }


    private static void startGameManual(CircularLinkedList<Jugador> cirList, Jugador... jugadores) throws Exception //Inicializa la partida, busca el jugador actual a ver si tiene un doble
    {
        FichasDomino src = null;
        int pos = 0;
        for (int i = 0; i < jugadores.length; i++) {
            if (!jugadores[i].hasDouble())
                continue;

            src = jugadores[i].makeFirstMove();
            if (src != null) {
                pos = i;
                break;
            }
        }

        Jugador primerJugador = jugadores[pos];
        primerJugador.removerFichaDomino(src);

        for (int i = 0; i < jugadores.length; i++) {
            if (i == pos)
                continue;

            jugadores[i].generateLongestPath(src);
            cirList.addLast(jugadores[i]);

            jugadores[i].setNextPosition();
            jugadores[i].setUltimaFicha(src);
        }

        cirList.addLast(primerJugador);

        int r = primerJugador.getPosX();
        int c = primerJugador.getPosY();
        gameBoard[r][c] = src;
        primerJugador.setNextPosition();
        printGameBoard();
    }

    public final static void clearConsole() //Limpia la consola
    {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            //  Handle any exceptions.
        }
    }

    public static void computeScore(Jugador... jugadores) 
    {
        

        int[] puntajes = new int[jugadores.length];
        for (int i = 0; i < puntajes.length; i++)
            puntajes[i] = 0;

        for (int i = 0; i < jugadores.length; i++) {
            for (FichasDomino tile : jugadores[i].getHand())
                puntajes[i] += tile.getScore();
        }

        int highest = 0;
        for (int i = 0; i < puntajes.length; i++) {
            if(puntajes[i] > puntajes[highest])
                highest = i;
        }

        System.out.println("El ganodor es: " + jugadores[highest].getNombre() + " con " + puntajes[highest] + " puntos.");

        System.out.println("Los demas: ");

        for (int i = 0; i < puntajes.length; i++) {
            if(i == highest) continue;
            System.out.println(jugadores[i].getNombre() + " - " + puntajes[i] + " puntos.");
        }

        for (int i = 0; i < 4; i++) {
            puntajeAcumulado[i] += puntajes[i];
        }

    }
}
