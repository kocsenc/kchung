/* Interface  to the Huffman tree module. Actually, this is just a datatype and a function to
 * construct trees from the bottom up - given the left and right trees and the character label,
 * creates a new root tree with the subtrees positioned as desired, the given label intact, and
 * the count of the root = sumof counts of the two children.
 *
*/
 
#ifndef HUFFMAN_TREE
#define HUFFMAN_TREE
 
 /*
  * The structure of a node in a (binary) Huffman coding tree.
  */
typedef struct h_tree_node {
     char ht_label ;
     int ht_count ;
     struct h_tree_node *ht_left ;
     struct h_tree_node *ht_right ;
} HTreeNode ;
 
 /*
  * Helper function to create a new tree from two subtrees and
  * the desired root node label. The root node count is the sum
  * of the counts of the subtrees.
 */
HTreeNode *mk_tree( char lab, int count, HTreeNode *left, HTreeNode *right) ;
#endif
