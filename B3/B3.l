%{
#include<stdio.h>
int lcount=0,wcount=0, ccount=0;
%}
%%
"\n" {lcount++;}
[a-bA-Z0-9] {wcount++;}
. {ccount++;}
%%
int yywrap()
{
return 1;
}
int main()
{
yyin = fopen("sample.txt","r");
yylex();
printf("Line Count %d\n",lcount);
printf("Word Count %d\n",wcount);
printf("Character count %d\n", ccount);
return 0;
}
