SUDOKU=./sudoku

# Main target - make an executable version of sudoku from the object files

sudoku: main.o puzzle.o arguments.o
	gcc -o sudoku main.o puzzle.o arguments.o

main.o:	puzzle.h bool.h arguments.h main.c
	gcc -c main.c

arguments.o:	bool.h arguments.h arguments.c
	gcc -c arguments.c

puzzle.o:	puzzle.h bool.h puzzle.c
	gcc -c puzzle.c


# Tests for level 1 - can we print an empty puzzle

test_l1: good_empty

# Tests for level 2 - can we print a real puzzle

test_l2: good_configured

# Tests for level3 - test adding, erasing, and solving

test_l3: good_add good_erase good_solve_puzzle

# Tests for level4 - tests for handling syntax errors
# 		     tests for detecting adds to occupied cell
# 		     tests for detecting erase of empty cell
# 		     tests for detecting erase of fixed cell.

test_l4: bad_puzzle_bad_digit1 bad_puzzle_bad_digit2 \
	 bad_puzzle_bad_digit3 script_bad_syntax \
	 bad_puzzle_occupied script_bad_occupied_empty_fixed

# Tests for level 5 - detecting add duplicates to a row or column

test_l5: bad_puzzle_dup_row bad_puzzle_dup_col script_bad_conflict_rowcol

# Tests for level 6 - detecting add duplicates to a 3x3 region.

test_l6: bad_puzzle_dup_region script_bad_conflict_region

good_empty:
	$(SUDOKU) -e p+s/good_empty_puzzle.txt < p+s/script_good_quit.txt
	sleep 2

good_configured:
	$(SUDOKU) -e p+s/good_puzzle.txt < p+s/script_good_quit.txt
	sleep 2

good_add:
	$(SUDOKU) -e p+s/good_puzzle.txt < p+s/script_good_add.txt
	sleep 2

good_erase:
	$(SUDOKU) -e p+s/good_puzzle.txt < p+s/script_good_erase.txt
	sleep 2

good_solve_puzzle:
	$(SUDOKU) -e p+s/good_puzzle.txt < p+s/script_good_solve_puzzle.txt
	sleep 2

bad_puzzle_bad_digit1:
	-$(SUDOKU) -e p+s/bad_puzzle_bad_digit1.txt < p+s/script_good_quit.txt
	sleep 2

bad_puzzle_bad_digit2:
	-$(SUDOKU) -e p+s/bad_puzzle_bad_digit2.txt < p+s/script_good_quit.txt
	sleep 2

bad_puzzle_bad_digit3:
	-$(SUDOKU) -e p+s/bad_puzzle_bad_digit3.txt < p+s/script_good_quit.txt
	sleep 2

bad_puzzle_occupied:
	-$(SUDOKU) -e p+s/bad_puzzle_occupied.txt < p+s/script_good_quit.txt
	sleep 2

bad_puzzle_dup_row:
	-$(SUDOKU) -e p+s/bad_puzzle_dup_row.txt < p+s/script_good_quit.txt
	sleep 2

bad_puzzle_dup_col:
	-$(SUDOKU) -e p+s/bad_puzzle_dup_col.txt < p+s/script_good_quit.txt
	sleep 2

bad_puzzle_dup_region:
	-$(SUDOKU) -e p+s/bad_puzzle_dup_region.txt < p+s/script_good_quit.txt
	sleep 2

script_bad_syntax:
	$(SUDOKU) -e p+s/good_puzzle.txt < p+s/script_bad_syntax_errors.txt
	sleep 2

script_bad_occupied_empty_fixed:
	$(SUDOKU) -e p+s/good_puzzle.txt < \
		p+s/script_bad_occupied_empty_fixed_errors.txt
	sleep 2

script_bad_conflict_rowcol:
	$(SUDOKU) -e p+s/good_puzzle.txt < p+s/script_bad_conflict_rowcol.txt
	sleep 2

script_bad_conflict_region:
	$(SUDOKU) -e p+s/good_puzzle.txt < p+s/script_bad_conflict_region.txt
	sleep 2

clean:
	rm -fr *~* *.o *.exe
