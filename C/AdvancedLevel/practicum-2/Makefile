# Makefile for the dequeue practicum.


pract: dequeuetest.o dequeue.o 
	gcc -o pract dequeuetest.o dequeue.o

dequeuetest.o: dequeue.c bool.h dequeue.h tests.h
	gcc -c dequeuetest.c

dequeue.o: dequeue.c bool.h dequeue.h
	gcc -c dequeue.c

clean:
	rm -f *.o
	rm -fr *~*
	rm -f pract pract.exe
