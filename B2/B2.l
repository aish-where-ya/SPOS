%{
#include<stdio.h>
%}
%%
int|double|float|String|char|short|long {printf("%s\t%s\n", yytext, "Datatype");}
"="|"+"|"-"|"*"|"/" {printf("%s\t%s\n", yytext, "Operator");}
";" {printf("%s\t%s\n", yytext, "Delimeter");}
[0-9]+ {printf("%s\t%s\n", yytext, "Number");}
[a-zA-Z]+[0-9]* {printf("%s\t%s\n", yytext, "Variable name");}
%%
int yywrap()
{
return 1;
}
int main()
{
yyin= fopen("sample.java","r");
yylex();
return 0;
}
