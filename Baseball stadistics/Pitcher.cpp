#include "Pitcher.h"


Pitcher::Pitcher(void)
{
}


Pitcher::~Pitcher(void)
{
}

float Pitcher::WHIP()//Cálculo del walks and hits per innings pitched
{
    return (HTS + CS)/TI;
}

float Pitcher::PCL()//Cálculo del Promedio Carreras Limpias
{
    return (CS*TI)/IP;
}
