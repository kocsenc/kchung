# Makefile for Huffman code program.
# Simply compiles the source files then links them to create huffman.exe

OBJS= main.o OrderedList.o HuffmanTree.o

all: huffman.exe

huffman.exe: $(OBJS)
	gcc -o huffman $(OBJS)

main.o: main.c HuffmanTree.h OrderedList.h
	gcc -c main.c

HuffmanTree.o: HuffmanTree.c HuffmanTree.h
	gcc -c HuffmanTree.c

OrderedList.o: OrderedList.c OrderedList.h HuffmanTree.h
	gcc -c OrderedList.c

clean:
	rm -fr *.exe *.o huffman

# Testing targets start here 
# Output to files and compare to expected results 

test1:
	./huffman < SelectedInput.txt > Test1.txt
	diff SelectedOutput.txt Test1.txt

test2:
	./huffman < DickensInput.txt > Test2.txt
	diff DickensTestOutput.txt Test2.txt
	

testAll: test1 test2
