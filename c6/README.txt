Author: Kelsan Dorjee
Credit (names of individuals, textbooks, forums, websites, etc. that help you):
Tony, Michael, Cody, Bryan, Greg 
MCS-388 Spring 2019
Project 06
Changed the compiler to allow for procedure declarations, procedure definitions,
allowing more/fewer arguments and checking calls/declarations/definitions. 

Date: 22 May 2019
Status: Finished

-Made the class:
o DeclareProc.java

-Removed the class:
o LibraryDeclarations.java

-Modified these classes:
o CallExpr.java
o Compiler.java
o Procedure.java
o SequenceProc.java

-Changes made to Parser.cup:
Added action code String currentProcName
Added non terminals:
o params, remParam, args, remArgs, param, argument, procDec

-Modified these non-terminals in the Parser.cup file:
o program, programs, expr

Test cases used:

int putchar(int c);

int print(int n){
  if(n < 0){
    putchar('-');
    n = -n;
  }
  if(n < 0){ // must have been -2^31, which negates to itself
    putchar('2');
    putchar('1');
    putchar('4');
    putchar('7');
    putchar('4');
    putchar('8');
    putchar('3');
    putchar('6');
    putchar('4');
    putchar('8');
  } else {
    if(n >= 10){
      print(n/10);
    }
    putchar(n%10 + '0');
  }
  return 0;
}

int println(int n){
  print(n);
  putchar('\n');
  return 0;
}

int nada(){
  return 2;
}

int dos(int one, int three){
  return one + three;
}

int four(int one, int two, int three, int four){
  return one + two + three + four;
}

int main(int result){
  println(22222);
  println(nada());
  println(dos(6,9));
  println(four(1,2,3,4));
}
