/*
 * Kocsen Chung Delgado - SE 350 Sudoku Activity
 * DECEMBER 2012
 * Estimated: 6-7 Hours
 * Actual(ongoing) 6:15 minutes
 * Implementation of the The Sudoku puzzle module.
 *
 * In keeping with standard Sudoku nomenclature, we use
 * 1-based indexing. Thus row indices, column indices,
 * and digits being placed must all be in the range 1 .. 9.
 */

#include <stdlib.h>
#include <stdio.h>

#include "puzzle.h"
#include "bool.h"

/*
 * The tables are 10x10 so that we can index from 1 .. 9 (the 0th row
 * and column are ignored). This wastes a tad of space, but is much
 * less likely to cause "off by 1" indexing errors.
 *
 * puzzle[i][j] is the number (0 .. 9) at row i, column j.
 *   0 represents a free (blank) puzzle location.
 *   Anything else represents the value at that location, and
 *     must be consistent with the rules of Sudoku.
 *
 * fixed[i][j] is true <=> row i, column j is fixed.
 *   That is, the value was part of the initial puzzle layout and
 *   cannot be erased.
 */

static short puzzle[10][10] ;
static bool  fixed[10][10] ;

/*
 * Functions to determine whether a row, a column, or the "region"
 * containing a row and column, contains the specified digit.
 */
static bool row_contains(int row, int digit) ; //SImplemented
static bool col_contains(int col, int digit) ; //SImplemented
static bool region_contains(int row, int col, int digit) ;//SImplemented

/*
 * Function to determine whether an integer - not the character for
 * a digit - is in the range 0 .. 9.
 */
static bool in_range(int value) ; //SImplemented

/*
 * Print support functions.
 *   print a line of dashes.
 *   print a row of values (with blanks for 0's).
 */
static void print_dashes() ; //SImplemented
static void print_row(int row) ; // Simplemented

/*
 * Initialize the puzzle so that (a) all values are zero (free)
 * and (b) non of the values are 'fixed' (uneraseable).
 */

extern void init_puzzle()  {
    int row;
    int col;
    for (col = 0 ; col < 10 ; col++){ //Note: Starting from 0
        for (row = 0 ; row < 10 ; row++){
            puzzle[row][col]=0;
			fixed[row][col] = FALSE;

        }
    }	
}

/*
 * Read in the initial puzzle configuration.
 * Each line is 4 characters long:
 *   Row    as a character '0' .. '9'
 *   Column as character '0' .. '9'
 *   Digit  as character '0' .. '
 *   Terminating newline.
 * Exits with an error message if there are syntactic
 * or semantic errors with any configuration line.
 */

void configure(FILE *puzzle_file) {
	char buffer[5]; //Is a string of size 5 due to extra NULL in char[]
	int row, col, digit;
	int lineNumber = 0;
	
	//fgets parses a line onto a string (char[])
	while ( (fgets(buffer, 5, puzzle_file) != NULL)) {
		//Values saved are only the first 3 characters.
		//So "\n" and EOF and any other chars are discarded(never allocated).
		row = buffer[0] - 48; //ASCII code for 0 = 48
		col = buffer[1] - 48;
		digit = buffer[2] - 48;
		lineNumber++;

		//DEBUG START
		//printf("Adding new with row: %d\tcol: %d\t digit: %d\n", row, col,
		//digit);
		//DEBUG END

		//if args are out of range
		if (!in_range(row) || !in_range(col) || !in_range(digit)) {
			printf ("Illegal format in configuration file at line %d.\n",
			lineNumber);
			exit(1);
			break;

		//checking if adding place is fixed, or if it doesnt comply with sudoku
		//regulation.
		}else if ( fixed[row][col] || region_contains(row , col, digit) || row_contains(row,  digit) || col_contains(col, digit) ){
			printf("Illegal placement in configuration file at line %d.\n",
			lineNumber);
			exit(1);
			break;		
		}else{ 
			//Adding puzzle digit, setting fixed value to TRUE.
			//Happens only after checking in range,if legal and if fixed previously.
			puzzle[row][col] = digit;
			fixed[row][col] = TRUE;
		}

	}
}

/*
 * Print the puzzle to standard output using the
 * specified output format.
 * 	- Print an initial row of dashes.
 * 	- Print each row.
 * 	- Print a row of dashes after each 3rd row.
 */

void print_puzzle() {
    int i;
    print_dashes();
    for (i = 1 ; i < 10 ; i++){
	    print_row(i);
	    if (i == 3 || i == 6 || i == 9){
	        print_dashes();
	    }
    }
  
}

/*
 * (Attempt to) add the specified digit at the given row and
 * column location on the puzzle.
 * The digit, row, and column must all be integers in
 * the range 1 .. 9.
 *
 * Return value is the status of the attempt (OP_OK is the only
 * status where the puzzle is actually changed).
 */

op_result add_digit(int row, int col, int digit) { //**DDCHECK
	//if parameters not in range (1..9)
	if ( !in_range(digit) || !in_range(row) || !in_range(col)){
		return OP_BADARGS;

	//if puzzle[row][col] is !=  zero -> is full/not empty
	}else if (puzzle[row][col] != 0 ){
		return OP_OCCUPIED;

	//if puzzle[r][c] is = 0 -> is empty
	}else if (puzzle[row][col] == 0 ) {
		//Check the puzzle if move is illegal
        if (row_contains(row,digit) || col_contains(col, digit) || region_contains(row,col,digit)){
			return OP_ILLEGAL;
		}else{
		puzzle[row][col] = digit;
        return OP_OK ;
		}
	//Worst case/unexpected scenario: return badargs.
    }else{
		return OP_BADARGS;
	}   
    
}

/*
 * (Attempt to) delete the digit at the given row and
 * column location on the puzzle the row and column
 * must both be integers in the range 1 .. 9
 *
 * Return value is the status of the attempt (OP_OK is the only
 * status where the puzzle actually changed).
 */

op_result erase_digit(int row, int col) {
	//if parameters are not in range (1..9)
	if (!in_range(row) || !in_range(col)){
		return OP_BADARGS;
	
	//if puzzle[r][c] is 0 -> empty
	}else if (puzzle[row][col] == 0){
		return OP_EMPTY;

	//if fixed[r][c] is true -> spot is fixed
	}else if (fixed[row][col] == TRUE){
		return OP_FIXED;	

	//finally, double check that row deleting is in range -> delete
	}else if ( in_range(puzzle[row][col])){
		puzzle[row][col] = 0;
		return OP_OK;

	//Worst case/unexpcected scneario: return badargs;
	}else{
		return OP_BADARGS;
	}
}


/*
 * Returns TRUE iff the given 'row' has the given 'digit' in it.
 */
static bool row_contains(int row, int digit) {
    int i;
    bool contains;
    contains = FALSE;
    for  (i = 1 ; i < 10 ; i++){
        if (puzzle[row][i] == digit ){
            contains = TRUE;
        }
    }
    return contains;
}

/*
 * Returns TRUE iff the given 'col' has the given 'digit' in it.
 */
static bool col_contains(int col, int digit) {
	int i;
    bool contains;
    contains = FALSE;
    for (i = 1 ; i < 10 ; i++){
        if (puzzle[i][col] == digit ) {
            contains = TRUE;
        }
    }
    return contains;
}

/*
 * Returns TRUE iff the region containing the cell at 'row' & 'col'
 * has the given digit in it.
 * NOTE: Finding the first row and column for the 'row'
 *       and 'col' is simple if you (a) remember that integer
 *       division discards the remainder and (b) translating
 *       the indices to 0 rather than 1 base makes things easier.
 * NOTE: A region is a 3 x 3 square.
 */

 /*
 Each conditional narrows down the search to a specific quadrant.
 _____________________
 |  I  |  II  |  III  |
 ---------------------
 | IV  |  V   |  VI   |
 ---------------------
 | VII | VIII |  IX   |
 ______________________

*/
static bool region_contains(int row, int col, int digit) {
	int rowIndex, colIndex, i, j ;

	if ( col <= 3 ) { //Narrowed to I ,IV, VII
		colIndex = 1;
	}else if ( col <= 6 ) { //Narrowed to II, V, VIII
		colIndex = 4;
	}else{ //Narrowed to III, VI, IX
		colIndex = 7;
	}
	if ( row <= 3 ) { //Narrowed to I, II, III
		rowIndex = 1;
	}else if ( row <= 6 ) { //Narrowed to IV, V VI
		rowIndex = 4;
	}else{ // Narrowed to VII, VIII, IX
		rowIndex = 7;
	}
	//Taking the intersection of narrowed values will determine the region.
	//Iterate dual array 3x3 times
	for ( i = rowIndex ; i < rowIndex+3 ; i++ ) {
		for ( j = colIndex ; j <  colIndex+3 ; j++){
			//DEBUG: printf("Checking region @ (row,col) = (%d , %d)\n", i, j);
			if ( puzzle[i][j] == digit ){
				return TRUE;
			}

		}
	}
	return FALSE;
}

/*
 * Return TRUE iff the value is in the Sudoku range (1 .. 9)
 */
static bool in_range(int value) {
	if (value >=1 && value <10 ){
		return TRUE;
	}else{
		return FALSE;
	}
}

/*
 * Print a row of 25 dashes.
 * This will line up correctly with the rows with data.
 */
static void print_dashes() {
    printf("-------------------------\n");
}

/*
 * Print a row from the puzzle.
 *   Print an initial bar ('|').
 *   For each value in the row, print a space and either:
 *     - a space if the value is 0.
 *     - the CHARACTER code for the digit if non-zero.
 *   After the 3rd, 6th and 9th columns, print " |"
 */
static void print_row(int row) {
    int i;
    printf("|");
    for (i = 1 ; i < 10 ; i++ ){
		printf(" ");
        if (puzzle[row][i] == 0 ){
            printf(" ");
        }else {
            printf("%d", puzzle[row][i]);  
        }
	
        if ( i % 3 == 0 ){
            printf(" |");
        }
    }
    printf("\n");
}
