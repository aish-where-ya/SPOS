%{
#include<stdio.h>
void yyerror(char *s);
extern FILE *yyin;
%}

%token	CONJUCTION NOUN PRONOUN VERB ARTICLE ADJECTIVE ADVERB PREPOSITION SPACE
%%
start: simple	{printf("SIMPLE \n");}|
	compound	{printf("COMPOUND \n");}
;
simple: SUBJECT SPACE VERB SPACE OBJECT|SUBJECT SPACE VERB SPACE ADVERB|SUBJECT SPACE ADVERB SPACE VERB|SUBJECT SPACE VERB SPACE ARTICLE SPACE NOUN|SUBJECT SPACE VERB SPACE ADJECTIVE
;
compound: simple SPACE CONJUCTION SPACE simple
;
SUBJECT : NOUN|PRONOUN;
;
OBJECT : NOUN|PRONOUN;
%%
void yyerror(char *s)
{
	printf("%s \n", s);
}
int yywrap()
{
	return(1);
}
int main()
{
	extern FILE *yyin;
	yyin = fopen("sample2.java", "r");
	yyparse();
	return 0;
}


