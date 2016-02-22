%{
/* Version 2: 2/13/2015 11:22:49 AM */
#include <stdio.h>
#include <stdlib.h>

/*
Variable usada para saber si la linea actual ya fue escrita.
*/
int endLine= 0;

%}

digit	[0-9]
nat		{digit}+
integer	[+-]?{nat}
double	{integer}(\.{nat}([Ee]{integer})?)?
comilla	\".*\"
Line 	.*\n
endL    \n

vector 	 [a-z]
operator ([A-Z<>+-]|\/|\*)
comment	!.*\n

%%

{Line}			{ 
				//Entra si la linea aun no se ha mostrado.
				if(endLine == 0)
				{
					//Se muestra la linea.
					printf("\nlinea: %s", yytext);

					//Informa que la linea se ha mostrado.
					endLine++;
				}
				//La linea vuelve atras y se intenta buscar el proximo match.					
				REJECT;
			}
{comment}	{ printf("Comentario: %s\n", yytext); endLine= 0;}

{comilla}	{ printf("Entre comillas: %s\n", yytext); }

{operator} 	{ printf("Operacion %c\n", yytext[0]); }

{vector}	{ printf("Vector %c\n", yytext[0]); }

{double} 	{ double n = atof(yytext); printf("Encontrado el numero %f\n" , n); } 

{endL} 		{
				//Hace un match del final de la linea, y notifica que la siguiente linea aun no ha sido mostrada.
				endLine=0;
				ECHO;
			}

%%

int main( ) {
	yylex();
	return EXIT_SUCCESS;
}
