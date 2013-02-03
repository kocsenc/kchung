#!/usr/bin/make
#
# Makefile for GradeList
#
# Author: Kocsen Chung
# Uses compiler clang/gcc to output binary file 'grades'. Cleans before every
# compilation.

grades: clean
	gcc -o grades main.c
	#clang -o grades main.c -> Was used for debugging(COMMAND DOES NOT EXECUTE)
clean:
	rm -rf grades
