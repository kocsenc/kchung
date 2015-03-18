/*
 * Implementation of a module supporting an ordered list of HTreeNodes - ordering is by increasing
 * count field.
 *
*/
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
 
#include "OrderedList.h"
#include "HuffmanTree.h"

/*
 * Node structure for nodes in the ordered list. Not defined as a typedef as
 * this is purely a local implementation detail and is invisible outside this module.
 *
 * Each node points to the HTreeNode is contains and to its successor node in the
 * ordered list (or NULL if it is the end of the list).
 */
struct ol_node {
    HTreeNode* ol_tn ;
    struct ol_node* ol_next ;
} ;

/*
 * Module state: Pointer to the head of the list (NULL => empty) and the
 * current size of the list. The latter is an optimization - we could compute
 * the size on each call to ol_size, but this would turn an O(1) algorithm
 * into O(n).
*/

static struct ol_node* head = NULL ;
static int size = 0 ;

/*
 * Insert the specified HTreeNode into the ordered list in the proper position
 * according to increasing count.
 *
 * Record the increase in size.
 * Traverse the list until either (a) find a node with a value larger than that
 *    in the tree node or (b) hit the list end. This tells us where the new node.
 *    belongs.
 * Aborts if we run out of memory.
 */

void ol_insert(HTreeNode* t)  {
	if (t != NULL){
		size ++; //Increase size as long as we know we can add HTreeNode
		struct ol_node* newNode = malloc( sizeof( struct ol_node)); 
		newNode->ol_tn = t; //Make newNode with treeNode info and allocate;
		
	
		if (head == NULL){//If list is empty, make t the head
			head = newNode;
			newNode->ol_next = NULL;
			return;

		}

		//BEGIN TRAVERSE THROUGH LIST
		struct ol_node* current;
		current = head; //set current* to head
		
		
		if ( newNode->ol_tn->ht_count <= head->ol_tn->ht_count ) { 
			newNode->ol_next = head;
			head = newNode  ;
			
			return;
		}



		while ( current->ol_next != NULL && current->ol_next->ol_tn->ht_count <=
			newNode->ol_tn->ht_count) {
			//Traversing through list until cases a/b above
			current = current->ol_next;
		}
		//Perform the insertion
		newNode->ol_next = current->ol_next;
		current->ol_next = newNode;


	}else{
		printf ("Did not insert ol_node, input TNode seems NULL\n");
	}
}
        
/*
 * Return the current size of the list of HTreeNodes.
 */

int ol_size() {
    return size ;
}

/*
 * Remove the first HTreeNode in the list and return a pointer
 * to it, adjusting the list size appropriately.
 * If the list is empty, NULL is returned and the size is unchanged.
 */

HTreeNode* ol_remove() {
	// If the list contains at least something
	if ( head != NULL){
		HTreeNode* returnNode = head->ol_tn; // set the node to be returned,
		head = head->ol_next; // make sure the head is now set to the new head
		size--; 
		return returnNode; //return node.
	}else{
		return NULL; //return NULL if list is empty
	}
}

