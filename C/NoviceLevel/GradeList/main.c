/** Kocsen Chung - Grade List Assignment (350)
 *  main.c file
 *  Estimated: 25 minutes
 *  Actual: 3.25 hours
 */

/* Must
1. Read set of names and gardes from input
2. Prints the list in its initial ordering
3. Prints the list SORTED by names
4. Prints the list SORTED by grade
5. Prints the mean and median grade
*/

#include <stdlib.h>
#include <stdio.h>
#include <ctype.h>
#include <string.h>

#define FALSE (0)
#define TRUE (1)

#define MAXNAME (20) //Name has max 20 alphabetic chars;
#define MAXGRADES (100) //Grade is int from 1 - 100;

/*grade_entry struct. used to store every student name plus their grade*/
struct grade_entry {
    char name [ MAXNAME+1 ] ;
    int grade;
};

/*Declaring variables used throughout the code*/
double mean, median; //mean and median, respectively
int sumgrades = 0 ; //Sum of all the grades (used for mean)
int numgrades = 0 ; //total number of grades parsed (starting at 0)
struct grade_entry grade_list[ MAXGRADES ] ; //grade_entry array of size MAXGRADE
char emptyChar; //A representation of an empty slot in grade_list = nul

static void sort_by_name() ; //method that sorts by name
static void sort_by_grade(); //method that sorts by grade
static void printArray();    //method that prints the array


/*
Main function.
PreCondition: Structs are defined and some variables are defined; the file must
follow the correct format (since using printf());
PostCondition: Parses data; Prints inital data; sorts by name; prints data;
sorts by grade; prints data; prints mean and median.

Note: mean is calculated right after parsing data; median is calculated in
sort_by_grade() function right after the array has been sorted.
*/

int main(){

    emptyChar = grade_list[0].name[0]; //sets variable emptyChar
    int count = 0 ;
    char *input_name;
    int input_grade;
    
    while (scanf("%s %d", input_name, &input_grade) != EOF ){ //Populate grade_list
        struct grade_entry anEntry;
        strcpy(anEntry.name,input_name);
        anEntry.grade = input_grade;
		sumgrades += anEntry.grade;

		/*DEBUG Start
        printf("Adding entry with name: ");
        printf("%s", anEntry.name);
        printf(" and grade: ");
        printf("%d", anEntry.grade);
        printf("\n");
		DEBUG END */

        grade_list[count] = anEntry;
        ++numgrades;
		++count;
    }
	//Calculating Mean
	mean = (double)sumgrades / (double)numgrades;

    //DEBUG Start
    printf("%d", numgrades); 
    printf(" entries have been parsed \n \n");
	//DEBUG End

    printArray();//Printing Array as PARSED

	printf("Sorting by name->");
	sort_by_name();//Sorting by name
    printArray(); //Printing array sorted by NAME

	printf("Sorting by grade->");
    sort_by_grade(); //Sorting by grade
    printArray(); //Printing array sorted by GRADE

	printf("\n");

	printf("Mean of grades: ");
	printf("%lf \n", mean);//Printing mean

	printf("Median of grades: ");
	printf("%lf \n", median);//Printing median

    return (0);

}

/*
sort_by_name() function

PreCondition: the grade_list array exists and only sorts if populated.
PostCondition: the grade_list itself (no copy) is sorted by name.

Uses insertion sort to sort by name of grade_entry.
The dummy variable that holds a grade_entry for swapping is called temp.
*/
static void sort_by_name(){
	int i,j;
	struct grade_entry temp;
	for (i = 0 ; i < numgrades ; i++){
		temp = grade_list[i];
		for (j = i; j>0 && (strcmp( temp.name, grade_list[j-1].name) < 0)   ; j--) {
			grade_list[j] = grade_list[j - 1];

		}
		grade_list[j] = temp;
	}
	
}

/*
sort_by_grade() function

PreCondition: the grade_list array exists and only sorts if populated.
PostCondition: the grade+list itself (no copy) is sorted by grade integer; the
	median is calculated.

Uses insertion sort to sort by grade of grade_entry and then calculates the
median by checking whether numgrades is even or odd.
The dummy variable that holds a grade_entry for swapping is called temp2.

*/
static void sort_by_grade() {
	int q,w;
	struct grade_entry temp2;
	for ( q = 0 ; q < numgrades ; q++){
		temp2 = grade_list[q];
		for (w = q; w> 0 && (temp2.grade < grade_list[w-1].grade) ; w--){
			grade_list[w] = grade_list[w-1];
		}
		grade_list[w] = temp2;
	}

	if ( (numgrades)%2 != 0) { //If numgrades is odd
		median = (double) grade_list[  numgrades / 2 ].grade ;
	}
	else {
		median = (double)(grade_list[ (numgrades / 2) - 1 ].grade + grade_list[
		numgrades / 2].grade) / 2.0 ;
	}
}

/*
printArray() function

PreCondition: the grade_list array exists and numgrades is accurate
PostCondition: the grade_list array is printed out;

Loops through grade_list array and prints out in the following format:
	NAME with grade: GRADE
Before printing anything, the loop double checks whether the current index (i)
in grade_list is empty: compares to emptyChar variable. See var declaration.
*/
static void printArray(){
	printf("Printing array\n");
    int i;
    for (i = 0 ; i < numgrades ; i++ ) {
        if (grade_list[i].name[0] != emptyChar ){
            printf("%s", grade_list[i].name);
            printf(" with grade: ");
            printf("%d", grade_list[i].grade);
            printf("\n");
        }
 
    }
    printf("\n");
}

