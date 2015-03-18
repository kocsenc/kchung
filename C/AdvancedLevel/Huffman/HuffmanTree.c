/*
 * Implementation of the Huffman tree module.
 *
*/
 
 #include <stdlib.h>
 #include <assert.h>
 #include "HuffmanTree.h"

 /*
  * Helper function to create a new tree from two subtrees and
  * the desired root node label. The root node count is the sum
  * of the counts of the subtrees.
 */
 HTreeNode *mk_tree( char lab, int count, HTreeNode* left, HTreeNode* right)  {
	//Begin making new Tree Node	
	HTreeNode* newTreeNode = malloc( sizeof( HTreeNode )); //Allocate space
	newTreeNode->ht_label = lab; //Set label = label
	newTreeNode->ht_count = count; //Set count = count
	newTreeNode->ht_left = left; //Left and right node assignation
	newTreeNode->ht_right = right;
	return newTreeNode; //Return the node. (freed @ end of traverseTree @main.c

 }
