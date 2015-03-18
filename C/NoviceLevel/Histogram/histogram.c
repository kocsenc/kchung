/*Kocsen Chung - Histogram Assignment */
/*Estimated: 30 minutes
  Actual: 38 minutes */

#include <stdlib.h>
#include <stdio.h>
#include <ctype.h>

#define FALSE (0)
#define TRUE  (1)

#define NLETTERS (26)	/* size of array, one entry per letter */
#define MAXSTARS (70)	/* maximum number of stars in a histogram line */

int count[NLETTERS] ;	/* counts for each of the 26 letters */
int nchar;

/*
 * The declaration for the function to print a sequence of asterisks.
 */

extern void print_stars( int ns ) ;

/*
This is the main function.
PreCondition: Include all libraries, define booleans and declare variables
PostCondition: Prints the histogram scaled to MAXSTARS. 
Parses file input (for security, I upcase all input chars); As
looping through file, the maximum count of a letter is stored; +1 is added to
every count depending on the letter found. Then the histogram is printed by
printing the Letter in alphabet array and then the respective stars using the
print_stars function.
*/
int main() {
	char alphabet[27] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',0};
	int i;
	int maxStars = 70; //Number of stars to be scaled
	int highestVal = 0; // Highest count of a specific letter
	/* REPLACE WITH THE CODE FOR HISTOGRAM */
	while ( (nchar = getchar()) != EOF ){
		if ( isalpha(nchar) ){
			//if is letter or punctuation
			int currentLetter = toupper(nchar);
			count[currentLetter - 65]++;
			if (count[currentLetter - 65] > highestVal){
				highestVal = count[currentLetter - 65];
			}
		} 
	}

	for (i = 0 ; i < 26; i++){
		printf("%c", alphabet[i]);

		//printf("Number of stars to print = ");
		//printf("%d", count[alphabet[i]-65]);

		print_stars( ((MAXSTARS) * (count[alphabet[i]-65])) / highestVal );
	}
	return 0 ;
}

/*
 * Print out 'ns' stars (asterisks)
 * Note, this does not scale the asterisks; asterisks are scaled in main.
 */

void print_stars( int ns ) {
	int i;

	for (i = 0 ; i < ns ;i++){
		if (i == 0){
			printf(" ");
		}
		printf("*");	
	} 
	printf("\n");

	return ;
}
