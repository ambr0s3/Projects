#pragma once
#include "ListaPitchers.h"
#include "ListaBateadores.h"
#include <iostream>
#include <stdlib.h>
#include <iomanip> 
using namespace std;

class Stats
{
public:
	Stats(void);
	~Stats(void);
	ListaPitchers listaPitchers; //La lista de Pitchers
	ListaBateadores listaBateadores; //La lista de Bateadores
	int players; //Cantidad total de jugadores de ambos tipos

	void InsertPlayer(Pitcher jugador);//Metodo de Insercion de un jugador tipo Pitcher
	void InsertPlayer(Bateador jugador);//Metodo de Insercion de un jugador tipo Pitcher

	void checkPlayers();//Metodo para validar que no se puedan insertar más del límite de jugadores
	void getData();//Metodo para leer los datos de los jugadores
	void showData();//Metodo para desplegar los datos estadisticos de los jugadores
};

