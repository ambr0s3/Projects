/* This tells flex to read only one input file */

%option noyywrap

%{
	int words = 0;
	int chars = 0;
	int lines = 0;
%}

EXP	([Ee][-+]?[0-9]+)
VECTORS [a-z]

%%

L?\"(\\.|[^\\"])*\" { printf("%s | ", yytext); printf("STRING\n"); } 
[AT] { printf("%c | ", yytext[0]); printf("OPERATOR3\n"); }
[SC] { printf("%c | ", yytext[0]); printf("OPERATOR2\n"); }
[>]  { printf("%c | ", yytext[0]); printf("OPERATOR1\n"); }
[-+]?[0-9]+"."[0-9]*{EXP}? | 
"."?[0-9]+{EXP}? { printf("%c | ", yytext[0]); printf("NUMBER\n"); }
{VECTORS} { printf("%c | ", yytext[0]); printf("VECTORID\n"); }
[ \t]     { }
[\n] { }
. { printf("%s | ", yytext); printf("UNEXPECTED\n"); }


%%

main(int argc, char **argv) 
{
	if(argc > 1) 
	{
		if(!(yyin = fopen(argv[1], "r"))) 
		{
			perror(argv[1]);
			return (1); 
		}
	}
	yylex();
}