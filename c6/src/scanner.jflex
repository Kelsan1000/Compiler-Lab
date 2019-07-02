import java_cup.runtime.Symbol;

%%

%{
  Symbol makeToken(int id, Object val){
    // yychar and yychar+yylength() are the positions of left and right ends
    return new Symbol(id, yychar, yychar + yylength(), val);
  }

  Symbol makeToken(int id){  // as above but with no attribute value
    return new Symbol(id, yychar, yychar + yylength());
  }
%}

%char
%cup
%cupdebug
%unicode

%%

"/"     {return makeToken(sym.DIVIDE);}
[0-9]+  {return makeToken(sym.NUMBER, yytext());}
"-"     {return makeToken(sym.MINUS);}
"*"     {return makeToken(sym.TIMES);}
"("     {return makeToken(sym.LPAREN);}
")"     {return makeToken(sym.RPAREN);}
"%"     {return makeToken(sym.MOD);}
"+"     {return makeToken(sym.PLUS);}
int   {return makeToken(sym.INT);}
if    {return makeToken(sym.IF);}
else	{return makeToken(sym.ELSE);}
while	{return makeToken(sym.WHILE);}
return	{return makeToken(sym.RETURN);}
"{"     {return makeToken(sym.LBRACE);}
"}"     {return makeToken(sym.RBRACE);}
[a-zA-Z][a-zA-Z0-9]* {return makeToken(sym.IDENTIFIER, yytext());}
'[^\n]'	{return makeToken(sym.NUMBER, "" + (int) yytext().charAt(1));}
'\\n'	{return makeToken(sym.NUMBER,"10");}
"//".* {}
"="		{return makeToken(sym.ASSIGNMENT);}
";"		{return makeToken(sym.LNTERM);}
","		{return makeToken(sym.COMMA);}
">="		{return makeToken(sym.GTE);}
"<="		{return makeToken(sym.LTE);}
"=="		{return makeToken(sym.EQ);}
"!="		{return makeToken(sym.NE);}
">"		{return makeToken(sym.GT);}
"<"		{return makeToken(sym.LT);}
" "|\r|\n|\t|\f      {}
.     {return makeToken(sym.ILLEGAL_CHAR, yytext());}
