#pragma once

const int MAXLASTNAME = 20;//Tama�o M�ximo del primer apellido del jugador
const int MAXFIRSTNAME = 10;//Tama�o M�ximo del primer nombre del jugador
const int MAXPLAYERS = 20;//Tama�o M�ximo de jugadores insertables

class Jugador
{
public:
	Jugador(void);
	~Jugador(void);
	    public:
	char FirstName[MAXFIRSTNAME+1];//Primer nombre del jugador
	char LastName[MAXLASTNAME+1];//Primer apellido del jugador
	char tipo;//Tipo de Jugador: 'P' implica Pitcher y 'B' implica Bateador
};

