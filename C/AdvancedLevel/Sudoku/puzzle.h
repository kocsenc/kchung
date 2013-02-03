/*
 * Interface to the The Sudoku puzzle module.
 *
 * In keeping with standard Sudoku labeling, we use
 * 1-based indexing. Thus row indices, column indices,
 * and digits being placed must all be in the range 1 .. 9.
 */

#ifndef PUZZLE_H
#define PUZZLE_H

#include <stdio.h>
#include "bool.h"

/*
 * An enumeration holding the status values that can be returned
 * from add_digit() and erase_digit().
 */

typedef enum {
	OP_OK ,		// add_digit / erase_digit ok
	OP_BADARGS ,	// bad arguments to add_digit / erase_digit
	OP_OCCUPIED ,	// space occupied for add_digit
	OP_ILLEGAL ,	// illegal digit placement for add_digit
	OP_EMPTY ,	// space empty for erase_digit
	OP_FIXED 	// space fixed (initially set) for erase_digit
} op_result ;

/*****
 * The operations in the puzzle support module.
 ****/

/*
 * Initialize the puzzle so that (a) all values are zero (free)
 * and (b) non of the values are 'fixed' (uneraseable).
 */

extern void init_puzzle()  ;

/*
 * Read in the initial puzzle configuration. The
 * puzzle must have been previously initialized.
 *
 * Each line is 4 characters long:
 *   Row    as a character '0' .. '9'
 *   Column as character '0' .. '9'
 *   Digit  as character '0' .. '9'
 *   Terminating newline.
 * As each value is placed, its location is marked
 * 'fixed' so that the value cannot be erased.
 *
 * On any errors, prints an appropriate message with the
 * offending line number and exits the program.
 */

void configure(FILE *puzzle_file) ;

/*
 * Print the puzzle to standard output using the
 * specified output format.
 */

void       print_puzzle() ;

/*
 * (Attempt to) add the specified digit at the given row and
 * column location on the puzzle.
 *   - The digit, row, and column must all be integers in
 *     the range 1 .. 9.
 *   - The selected puzzle cell must be unoccupied.
 *   - The value must not already be in the row, column,
 *     or 3x3 region.
 *
 * Return value is the status of the attempt (OP_OK is the only
 * status where the puzzle actually changed).
 */

op_result  add_digit(int row, int col, int digit) ;

/*
 * (Attempt to) erase the digit at the given row and
 * column location on the puzzle.
 *   - The row and column must both be integers in the
 *     range 1 .. 9
 *   - The selected puzzle cell must be occupied.
 *   - The selected puzzle cell must not be 'fixed' (that
 *     is, uneraseable.
 *
 * Return value is the status of the attempt (OP_OK is the only
 * status where the puzzle actually changed).
 */

op_result  erase_digit(int row, int col) ;

#endif
