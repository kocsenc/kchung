/*
 * Interface to the module that handles command line arguments.
 * 
 * Parses the arguments by parse_args() and makes the results available
 * via the puzzle_file() and echo_input() functions.
 */

#ifndef ARGUMENTS_H
#define ARGUMENTS_H

#include <stdio.h>

#include "bool.h"

/*
 * Parsing - call before any other functions.
 * Exits the program with a message if there are any problems.
 */

extern void parse_args(int ac, char **av) ;

/*
 * Return the handle for the file with the Sudoku puzzle
 * data.
 */

extern FILE *puzzle_file() ;

/*
 * Return true iff the optional -e (for echo) argument was
 * given.
 */

extern bool echo_input() ;

#endif
