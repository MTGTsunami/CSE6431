This is the project of course 6431 created by Yifei Diao.
The whole program contains ten java class files£º(no libraries linked)
3 for machine handling (burger, fries and coke machine);
2 for table arrangement;
2 for order arrangement;
1 for cook threads;
1 for diner threads;
1 for main restaurant;
Both cooks and diners number from 0 to n-1 (# cooks-1 || # diners-1).
In order to keep the final result steady, I let each diner thread sleep(3) after starting.
Though it avoids the situation that the subsequent diner threads get the cook and table first,
the final leaving time of the last diner seems to get a little bit larger. 

Compiling: Makefile
Command line: 
make
java restaurant xxx.txt (input file naume)
