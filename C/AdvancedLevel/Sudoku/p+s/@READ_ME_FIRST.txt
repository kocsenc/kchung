This directory contains:
  Puzzles configurations used to set up a puzzle in the sudoku program.
  Scripts of commands to apply to the configurations.

Puzzles and scripts can either be good or bad:
  Good puzzles/scripts have no errors in them and are used to
    test normal operation.
  Bad puzzles/scripts have errors in them and are used to
    test the error handling of the sudoku program.

GOOD PUZZLES

good_empty_puzzle.txt
   A puzzle with no squares occupied - used to test simple printing.

good_puzzle.txt
   The one and only real puzzle configuration. Not only is
   it error free, it specifies a solvable puzzle.

GOOD SCRIPTS

script_good_add.txt
   A script which run with the good puzzle does a short sequence
      of add ('a') commands, followed by print ('p') and quit ('q').
   Used primarily to test add logic without having to solve the
      entire puzzle.

script_good_erase.txt
   Simple test of correct erase ('e') commands on the good puzzle
      configuration. Just adds a value and immediately erases
      it.

script_good_quit.txt
   Simply quits - used to test basic print functionality and the default
      for bad puzzles (which should never read a command).

script_good_solve_puzzle.txt
   A set of commands that solves the puzzle in good_puzzle.txt.
   Basically a sequence of add ('a') commands punctuated now and
      again by print ('p') commands and ending with a quit ('q').

BAD SCRIPTS
   NOTE: Bad scripts are all run using the good puzzle configuration,
         as the errors are in the commands issued.

script_bad_syntax_errors.txt
   Checks that the program can detect illegal row, column, and
      digits for the add ('a') and erase ('e') commands.

script_bad_occupied_empty_fixed_errors.txt
   Checks that the program can detect attempts to:
      - add a value to an occupied cell, 
      - erase a value from an empty cell,
      - erase a value from a fixed (uneraseable) cell.

script_bad_conflict_rowcol.txt
   Checks that the program can detect attempts to add a value
      to a cell where the value already is in that cell's row
      or column.

script_bad_conflict_box.txt
   Checks that the program can detect attempts to add a value
      to a cell where the value already is in the 'box' for
      the cell's row and column.

BAD PUZZLES
   NOTE: No script is needed for bad puzzles, because the program
         should immediately exit after detecting and printing the
         appropriate error message while reading in the puzzle
         configuration.

bad_puzzle_bad_digit1.txt
   Checks the program's ability to detect a bad digit in the
      first column of a configuration row.

bad_puzzle_bad_digit2.txt
   Checks the program's ability to detect a bad digit in the
      second column of a configuration row.

bad_puzzle_bad_digit3.txt
   Checks the program's ability to detect a bad digit in the
      third column of a configuration row.

bad_puzzle_occupied.txt
   Checks the program's ability to detect a configuration
      where a two values are placed in the same cell.

bad_puzzle_dup_row.txt
   Checks the program's ability to detect a configuration where
      the same value is placed twice in a row.

bad_puzzle_dup_col.txt
   Checks the program's ability to detect a configuration where
      the same value is placed twice in a column.

bad_puzzle_dup_box.txt
   Checks the program's ability to detect a configuration where
      the same value is placed twice in a box.

