#pragma once
#include "Jugador.h"

class Pitcher : public Jugador  //Estructura representativa de un Pitcher
{
    public:

		Pitcher();
				~Pitcher();
    float bolas;//Las bolas
    float strikes;//Los strikes
    float ponches;//Los ponches
    float JJ;//Juegos jugados
    float GA;//Juegos iniciados
    float EL;//Entradas lanzadas
    float IP;//Innings Pichados
    float HTS;//Cuantos hits
    float CS;//Cuantas carreras
    float CL;//Carreras limpias
    float BB2;//bases por bola
    float PC;//Ponches
    float VS;//Victorias
    float DS;//Cuantas derrotas
    float TI;//Total de Innings

    float WHIP();//Cálculo del walks and hits per innings pitched
    float PCL();//Cálculo del Promedio Carreras Limpias
};
