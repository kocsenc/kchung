/*
* BST - Implement a binary search tree (for strings).
*       Interface file
*
* Practicum #2		
* SE350 - 20082
*
*/
#ifndef BST_H

#define TRUE  1
#define FALSE 0

struct treeNode ;

/*
* Return a new, empty binary search tree.
*/

struct treeNode *bst_make() ;

/*
* Insert the <value> string into the binary search <tree>.
* Returns a pointer to the updated <tree>.
*
* Note: Adding a value that is already in the tree has no 
*       observable effect.
*/

struct treeNode *bst_insert(struct treeNode *tree, char *value) ;

/*
* Return TRUE if the binary searcy <tree> contains the given
* string <value>, otherwise return FALSE.
*/

int bst_contains(struct treeNode *tree, char *value) ;

#endif

