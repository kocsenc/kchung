/*
 * Simple list manipulation exercise.
 * 1. Create a list of integers.
 * 2. Print the list.
 * 3. Sort the list.
 * 4. Print the list
 * 5. Free the list nodes.
 */

#include <stdlib.h>
#include <stdio.h>

struct node {
	int value ;
	struct node *next ;
} ;

extern struct node *mk_node(int v) ;
extern void print_list(struct node *head) ;
extern struct node *sort_list(struct node *head) ;
extern void free_list(struct node *head) ;

/*
 * Arrays of integers terminated with a zero.
 */

int empty[]	= { 0 } ;
int one[]	= { 1, 0 } ;
int two[]	= { 2, 5, 0 } ;
int general[]	= { 3, 8, 2, 5, 1, 9, 0 } ;

/*
 * Array of arrays.
 *
 * Pointers to each of the subarrays,
 * terminated by a NULL program.
 *
 * Also create a parallel array naming the test.
 */

int *a_of_a[] = { empty, one, two, general, NULL } ;

char *test_name[] = { "empty", "one value", "two value", "general", NULL } ;

/*
 * Main driver program.
 *   - For each test array:
 *     * Creates list from array.
 *     * Prints the list.
 *     * Sorts the list.
 *     * Prints it.
 *     * Frees the list.
 */

int main() {
	struct node *head ;	/* head of the current list */
	struct node *curp ;	/* current node inserted in list */

	int *cur_test ;		/* current array of test values */

	/*
	 * Outer loop iterates through the array of arrays.
	 */

	int i ;
	for( i = 0 ; (cur_test = a_of_a[i]) != NULL ; i++ ) {

		/*
		 * Inner loop to create list.
		 */

		head = NULL ;
		int j ;
		for ( j = 0 ; cur_test[j] != 0 ; ++j ) {
			curp = mk_node( cur_test[j] ) ;
			curp->next = head ;
			head = curp ;
		}

		/*
		 * Print / Sort / Print / Free the List
		 */

		printf("Original %s list\n", test_name[i]) ;
		print_list(head) ;

		printf("Sort %s list\n", test_name[i]) ;
		head = sort_list(head) ;

		printf("Print sorted %s list\n", test_name[i]) ;
		print_list(head) ;

		printf("Free %s list\n", test_name[i]) ;
		free_list(head) ;

		printf("\n") ;
	}

	return 0 ;
}

/*
 * Return a new node with 'v' as the label and a NULL next link.
 */

struct node *mk_node(int v) {
//DEBUG:	printf("Making node with value: %d\n", v);
	int val = v;
	struct node* temp = (struct node *) malloc( sizeof(struct node) );//make node

	temp->value = val;
	temp->next = NULL;
	return temp;
}

/*
 * Print the list headed by 'head', one value per line.
 */

void print_list(struct node *head) {
	printf("Printing the list... \n");
	if(head == NULL){
		printf("List is EMPTY\n");
	}
	while (head != NULL){
		if (head->value == 0){//0 is represented as end/empty so it ignores 0's.
			head = head->next;
			continue;
		}else{
			printf("%d\n", head->value); //prints value, head = *head.next
			head = head->next;
		}	
	}
	printf("\n");

}

/*
 * Sort the list headed by 'head', returning a pointer to the node
 * that ends up at the head of the list.
 */

struct node *sort_list(struct node *head) {

	/*Makes a new empty node by allocating memory. This linked list(node) will
	 * be the linked list returned as it will be populated by the sorting
	 * algorithm
	 */
	struct node* newEmpty = NULL;

	//struct node* newEmpty = (struct node *) malloc( sizeof(struct node) );
	//newEmpty->value=NULL;
	//newEmpty->next = NULL;


	while (head != NULL){
		//Store head
		struct node* temp = head; //making a temporary node

		//Store pointer for splice
		struct node ** trailing = &newEmpty; /*Pointer to a pointer of the empty
		sorted listi (declared above)*/

		head = head->next;//keeps moving on through the list 

		while(1){
			//as long as newEmpty is null or the value of the temp (head) is
			//less than the trailing value
			if ( *trailing == NULL || temp->value < (*trailing)->value){
				//Agree
				temp->next = *trailing;
				*trailing = temp;
				break;

			}else{
				//Continue down the list
				trailing = & (*trailing)->next;
			}

		}
	}

	return newEmpty;

}

/*
 * Free all the nodes in the list headed by 'head'.
 */

void free_list(struct node *head) {
	if ( head != NULL ){
		free_list(head->next);
		free(head);
	}
}
