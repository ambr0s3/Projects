package game;

import library.IList;
import library.SimpleLinkedList;
import library.GrafoPonderado;

import java.util.Scanner;

/**
 * Created by AS
 */
public class Jugador {
    private int posX; //Posicion en X
    private int posY; //Posicion en Y
    private String nombre; //Nombre 
    private GrafoPonderado<Integer> dominoGraph; //Grafo que contiene el domino
    private IList<FichasDomino> fichasActuales; //Fichas que tiene el jugador en la mano
    private IList<FichasDomino> fichasJugadas; //Fichas que se han jugado, se construyen en una lista enlazada
    private IList<FichasDomino> longestPath; //Camino mas largo
    private boolean esMaquina; //Determina si es la maquina que esta jugando
    private Cell direction; //Direccion en la que se va a mmover la ficha
    int cursor = 0; 
    FichasDomino ultimaFicha; //Ultima ficha

    public Jugador(String name, boolean esMaquina) 
    {
        this.esMaquina = esMaquina;
        direction = new Cell(0, 0);
        this.nombre = name;
        ultimaFicha = new FichasDomino(-1, -1);
        dominoGraph = new GrafoPonderado<>();
        fichasActuales = new SimpleLinkedList<>();
        longestPath = new SimpleLinkedList<>();
        fichasJugadas = new SimpleLinkedList<>();
        // fill the default vertices
        for (int i = 0; i < 7; i++) {
            try {
                dominoGraph.addVertex(i);
            } catch (Exception e) {
            }
        }
    }

    public FichasDomino getUltimaFicha() {
        return ultimaFicha;
    }

    public void setUltimaFicha(FichasDomino ultimaFicha) {
        this.ultimaFicha = ultimaFicha;
    }

    public void setNextPosition() {
        posX += direction.filas;
        posY += direction.columnas;
    }

    public boolean esMaquina() {
        return esMaquina;
    }

    public static class Cell 
    {
        int filas;
        int columnas;

        public Cell(int x, int y) {
            this.filas = x;
            this.columnas = y;
        }

        public int getFilas() {
            return filas;
        }

        public void setFilas(int filas) {
            this.filas = filas;
        }

        public int getColumnas() {
            return columnas;
        }

        public void setColumnas(int columnas) {
            this.columnas = columnas;
        }
    }

    public void agregarFicha(int upperNum, int lowerNum) throws Exception 
    {
        dominoGraph.addEdge(upperNum, lowerNum, upperNum + lowerNum);

        fichasActuales.addLast(new FichasDomino(upperNum, lowerNum));
    }

    public void agregarFicha(FichasDomino ficha) throws Exception //Agrega lista al grafo y le da una mano al jugador
    {
        dominoGraph.addEdge(ficha.numeroSuperior, ficha.numeroInferior, ficha.getScore());
        fichasActuales.addLast(ficha);
    }

    public IList<FichasDomino> getHand() 
    {
        return fichasActuales;
    }


    public FichasDomino makeFirstMove() throws Exception {
        if (esMaquina) {
            FichasDomino highest = null;

            for (FichasDomino domino : fichasActuales) {
                if (domino.isDouble()) {
                    if (highest == null)
                        highest = domino;
                    else if (domino.getNumeroSuperior() > highest.getNumeroSuperior())
                        highest = domino;

                }
            }
            if (highest != null) {
                generateLongestPath(highest);
                longestPath.addFirst(highest);
            }
            return highest;
        } else {
            //Pide que ficha voy a mover
            Scanner scan = new Scanner(System.in);
            System.out.println("Fichas del jugador: ");
            int cnt = 0;
            for (FichasDomino item : getHand()) {
                System.out.println(cnt++ + " - " + item.toString());
            }
            int option = -1;
            while (option == -1) {
                System.out.print("Seleccione uno: ");
                option = scan.nextInt();

                if (option < 0 || option > fichasActuales.size()) {
                    System.out.println("Opcion invalida");
                    option = -1;
                }

                if (!esDoble(option)) {
                    System.out.println("Debe Selecionar un doble");
                    option = -1;
                }


            }

            scan.nextLine();
            return fichasActuales.get(option);
        }

    }

    private boolean esDoble(int option) throws Exception {
        FichasDomino ficha = fichasActuales.get(option);
        return ficha.isDouble();
    }

    public void generateLongestPath(FichasDomino primeraFicha) throws Exception {

        int src = primeraFicha.getNumeroSuperior();
        int[] costs = dominoGraph.maxLongestPath(src);
        int dest = 0;

        for (int i = 0; i < costs.length; i++) {
            if (costs[i] > costs[dest])
                dest = i;
        }

        //longestPath.addLast(primeraFicha);
        IList<Integer> path = dominoGraph.maxLongestPath(src, dest);
        int prev = src;
        for (int i = 0; i < path.size(); i++) {
            int current = path.get(i);
            longestPath.addLast(new FichasDomino(prev, current));
            prev = current;
        }
    }

    public FichasDomino makeMove() throws Exception //Hace la jugada
    {
        if (esMaquina) {
            if (cursor < longestPath.size()) {
                return longestPath.get(cursor++);
            }
        } else {
            //Me pregunta cual ficha deseo mover
            Scanner scan = new Scanner(System.in);
            System.out.println("Fichas Actuales: ");
            int cnt = 0;
            for (FichasDomino item : getHand()) 
            {
                System.out.println(cnt++ + " - " + item.toString());
            }
            System.out.println("8 - Para pasar");

            int option = -1;

            while (option == -1) {
                System.out.print("Seleccione uno: ");
                option = scan.nextInt();

                if(option == 8)
                    return null;

                if (option < 0 || option > fichasActuales.size() || !isOptionEligible(option)) {
                    System.out.println("Opcion invalida");
                    option = -1;
                }
            }

            scan.nextLine();
            FichasDomino ficha = fichasActuales.get(option);
            if(ficha.getNumeroSuperior() == ultimaFicha.numeroInferior)
                return ficha;
            else
                return ficha.reverse();
        }

        return null;
    }


    private boolean isOptionEligible(int option) throws Exception //Valida si puede jugar x ficha
    {
        FichasDomino ficha = getHand().get(option);

        return ultimaFicha.numeroInferior == ficha.numeroSuperior ||
                ultimaFicha.numeroSuperior == ficha.numeroInferior ||
                ultimaFicha.numeroInferior == ficha.numeroInferior ||
                ultimaFicha.numeroSuperior == ficha.numeroSuperior;
    }

    public void removerFichaDomino(FichasDomino ficha) throws Exception //Lista que remueve ficha (Controller)
    {
        int i = 0;
        for (FichasDomino item : fichasActuales) //Item = Fichas
        {
            if (item.equals(ficha)) {
                fichasJugadas.addLast(ficha);
                fichasActuales.remove(i);
                break;
            }
            i++;
        }

        setUltimaFicha(ficha);
    }

    public boolean canContinue() //Valida si puede seguir jugando (Si tiene ficha o no)
    {
        if (fichasActuales.isEmpty())
            return false;
        if (esMaquina && cursor == longestPath.size())
            return false;

        if(!esMaquina) {
            boolean canContinue = false;
            for (FichasDomino ficha : fichasActuales) 
            {
                if (ficha.canBeNextTo(ultimaFicha))
                    canContinue = true;
            }

            return canContinue;
        }

        return true;
    }

    public IList<FichasDomino> getFichasJugadas() {
        return fichasJugadas;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPosition(int x, int y) {
        posX = x;
        posY = y;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setDirection(int x, int y) 
    {
        direction = new Cell(x, y);
    }

    public boolean hasDouble() 
    {
        for (FichasDomino ficha : getHand()) 
        {
            if (ficha.isDouble())
                return true;
        }
        return false;
    }
}
