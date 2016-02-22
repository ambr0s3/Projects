#pragma once
#include "lista.h"
#include "Pitcher.h"

class ListaPitchers :
	public Lista
{
public:
	ListaPitchers(void);
	~ListaPitchers(void);
	 Pitcher lista[MAXPLAYERS];//Arreglo de Pitchers
};

