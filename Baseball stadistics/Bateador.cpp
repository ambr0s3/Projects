#include "Bateador.h"


Bateador::Bateador(void)
{
}


Bateador::~Bateador(void)
{
}

float Bateador::batAvg()//Cálculo del Bat Average
{
    return singles/AB;
}

float Bateador::SLG()//Cálculo del Sluggin Percentage
{
    return TB/AB;
}

float Bateador::OBP()//Cálculo del On-base percentage
{
    return (singles + walks + HBP) / (AB + walks + SF + HBP);
}

float Bateador::OPS()//Cálculo del On-base Plus Slugging Percentage
{
    return OBP() + SLG();
}
