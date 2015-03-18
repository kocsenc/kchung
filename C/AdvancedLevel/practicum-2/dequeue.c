/*
 * Implementation file for the doubly ended queue (dequeue)
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "dequeue.h"
#include "bool.h"

//Declaring local variables
int size=0;

bool deq_is_empty(node *deqhead){
    if (deqhead == NULL){
		return true;
	}else{
		return false;
	}
}

int deq_size(node *deqhead){
	node* current = deqhead;
	int count = 0;

	while(current !=  NULL){
		count++;
		current = current->link;
	}
	return count;

}

//NOTE: Accoording to this implementation, the correct  head will always be passed in
void deq_put_head(node** deqhead, char* value){
	if (value != NULL){
		size ++;
		node* newNode = malloc (sizeof( node));
		char* newValue = malloc (sizeof( value+1) );
		strcpy(newValue, value);
		newNode->contents = newValue;
		if (*deqhead == NULL){//if the list is empty, make it the head
			*deqhead = newNode;
			newNode->link = NULL;
		}else{
			newNode->link = *deqhead;
			*deqhead = newNode;
		}
	}


}

void deq_put_tail(node **deqhead, char *value){
	node* current;
	current = *deqhead;
	
	size++;
	node* newNode = malloc(sizeof( node));
	char* newValue = malloc (sizeof( value+1) );
	strcpy(newValue, value);
	newNode->contents = newValue;
	newNode->link = NULL;


	if (current == NULL){//If head is null(empty llist)
		*deqhead = newNode;
		return;
	}else{
		while (current->link != NULL){
			current = current->link;
		}
		current->link = newNode;
	}
}

void deq_get_head(node **deqhead, char *value){
    node* current;
	current = *deqhead;
	if (current == NULL){ //If is empty case
		return;
	}else{
		*deqhead = current->link;
		current->link = NULL;
		strcpy( value, current->contents );
		free(current->contents);
		free(current);
		size--;
	}
}

void deq_get_tail(node **deqhead, char *value){
	node* current;
	current = *deqhead;

	if ( current == NULL ){
		return;
	}else if ( current->link == NULL ){
		strcpy( value, current->contents );
		free( current->contents );
		free( current );
		*deqhead = NULL;
	}else{
		node* currentAhead;
		currentAhead = current->link;
		while( currentAhead->link != NULL ){
			current = current->link;
			currentAhead = currentAhead->link;
		}

		current->link = NULL;

		strcpy( value, currentAhead->contents );
		free(currentAhead->contents);
		free(currentAhead);
		
		size--;
	}

}
