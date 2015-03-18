Kocsen Chung
Readme.txt for HAIL.ASM (Fibonacci sequence)
Type of processor: MIPS - Microprocessor without Interlocked Pipeline Stages
Uses: 				Misasim.py (MIPS simulator) - Free download

Status: Functional

To run: 
		Download Misasim
		Load the HAIL.ASM file
		Run

Objective:
		Learn assembly (MIPS) by writing the Hailstone 	Sequence 
		Algorithm.
		

		This problem is easy to describe but it is one of mathematics' unsolved problems.

Starting with any positive integer n, form a sequence in the following way:

If n is even, divide it by 2 to give n' = n/2.
If n is odd, multiply it by 3 and add 1 to give n' = 3n + 1.
Then take n' as the new starting number and repeat the process. For example:

n = 5 gives the sequence
5, 16, 8, 4, 2, 1, 4, 2, 1,...
n = 11 gives the sequence
11, 34, 17, 52, 26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1, 4, 2, 1,...
These are sometimes called "Hailstone sequences" because they go up and down just like a hailstone in a cloud before crashing to Earth - the endless cycle 4, 2, 1, 4, 2, 1. It seems from experiment that such a sequence will always eventually end in this repeating cycle 4, 2, 1, 4, 2, 1,... and so on, but some values for N generate many values before the repeating cycle begins. For example, try starting with n = 27. See if you can find starting values that generate even longer sequences.

An unsolved problem is, can it be proved that every starting value will generate a sequence that eventually settles to 4, 2, 1, 4, 2, 1,...? Could there be a sequence that never settles down to a repeating cycle at all?