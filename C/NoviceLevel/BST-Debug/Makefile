# Makefile for testing the BST module

all:	run

run:	bst_test.exe
	./bst_test

bst_test.exe:	bst_test.o bst.o
	gcc -g -o bst_test bst_test.o bst.o

bst_test.o: tests.h bst.h bst_test.c
	gcc -g -c bst_test.c
	
bst.o:	bst.h bst.c
	gcc -g -c bst.c

clean: 
	rm -rf *.o bst_test
