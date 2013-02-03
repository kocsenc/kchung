/*
 * Implementation of the module that handles command line arguments.
 * 
 * Parses the arguments by parse_args() and makes the results available
 * via the puzzle_file() and echo_input() functions.
 */

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "arguments.h"
#include "bool.h"

static void usage( char prog[] ) ;
static void file_error( char prog[], char fname[]) ;

static int echoing = FALSE ;	// are we echoing input?
static FILE *sudoku_file ;	// handle for the sudoku data.

/*
 * Parsing - call before any other functions.
 * Exits with a message if there are any problems.
 */

void parse_args(int ac, char **av) {
	int usage_ok ;
	char *file_name ;

	/*
	 * See if we have the -e option and if the
	 * number of arguments is correct. Usage error
	 * and exit on argument error.
	 */
	echoing = (ac == 3) && (strcmp("-e", av[1]) == 0) ;
	usage_ok = (ac == 2) || echoing ;

	if( ! usage_ok ) {
		usage( av[0] ) ;
		exit( 1 ) ;
	}

	/*
	 * Try to open the sudoku file (the last argument).
	 * Error exit if the file is unreadable.
	 */
	if( ! (sudoku_file = fopen(av[ac-1], "r")) ) {
		file_error( av[0], av[ac-1] ) ;
		exit( 1 ) ;
	}
}

/*
 * Return the handle for the file with the Sudoku puzzle
 * data.
 */

FILE *puzzle_file() {
	return sudoku_file ;
}

/*
 * Return true iff the optional -e (for echo) argument was
 * given.
 */

bool echo_input() {
	return echoing ;
}

/*
 * Print a usage error (when the command line arguments
 * are syntactically incorrect).
 */

static void usage( char prog[] ) {
	printf("Usage: %s [-e] file\n", prog) ;
	printf("\t[-e] Echo input if specified\n") ;
	printf("\tfile The file with the initial board\n") ;
}

/*
 * Print an error message if the file cannot be opened for
 * reading.
 */

static void file_error( char prog[], char fname[] ) {
	printf( "%s: Cannot read file %s\n", prog, fname ) ;
}
