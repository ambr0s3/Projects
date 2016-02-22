#pragma once
#include "lista.h"
#include "Bateador.h"

class ListaBateadores :
	public Lista
{
public:
	ListaBateadores(void);
	~ListaBateadores(void);
	    Bateador lista[MAXPLAYERS];//Arreglo de Bateadores
};

