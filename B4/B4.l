%{
//Declarations [a-zA-Z0-9]* {return classname;} class {return class;} static {return static1;}"{"|"}" {return brac;}
//"("|")" {return roundbrac;}
//["] {return token1;}
//public|private|protected {return accessspecifier;}
//main|static|class {return main1;}
#include<stdio.h>
#include"y.tab.h"
%}

%%

int|double|string|float|char {return datatype;}
[0-9] {return num;}
[a-zA-Z0-9]+ {return varname;}
"+"|"-"|"*"|"/" {return operator;}
"=" {return eq;}
";" {return sc;}

%%
