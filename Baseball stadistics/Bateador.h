#pragma once
#include "Jugador.h"

class Bateador : public Jugador
{
public:
	Bateador(void);
	~Bateador(void);

	float CI; //Carreras Impulsadas
	float AB; //Veces al bate
	float singles; //sencillos
	float doubles; //dobles
	float triples; //triples
	float HR; //homeruns
	float walks; //bases por bola
    float HBP; // Faltas que le han hecho
    float SF; // Service Flies
    float TB; //Total de Bases
	float batAvg();//Cálculo del Bat Average
	float SLG();//Cálculo del Sluggin Percentage
	float OBP();//Cálculo del On-base percentage
	float OPS();//Cálculo del On-base Plus Slugging Percentage
};

