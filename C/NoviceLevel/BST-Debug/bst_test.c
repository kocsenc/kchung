/*
 * Unit test harness for the BST module
 */

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "bst.h"
#include "tests.h"

START_TESTS()

	struct treeNode *bst ;

START_TEST("Nothing in the list initially")
	bst = bst_make() ;
	ASSERT( ! bst_contains(bst, "mike") ) ;
	ASSERT( ! bst_contains(bst, "andy") ) ;
	ASSERT( ! bst_contains(bst, "zeke") ) ;
	ASSERT( ! bst_contains(bst, "bart") ) ;
	ASSERT( ! bst_contains(bst, "tom") ) ;
END_TEST()

START_TEST("Put mike in the list")
	bst = bst_insert(bst, "mike") ;
	ASSERT(   bst_contains(bst, "mike") ) ;
	ASSERT( ! bst_contains(bst, "andy") ) ;
	ASSERT( ! bst_contains(bst, "zeke") ) ;
	ASSERT( ! bst_contains(bst, "bart") ) ;
	ASSERT( ! bst_contains(bst, "tom") ) ;
END_TEST()

START_TEST("Put zeke in the list")
	bst = bst_insert(bst, "zeke") ;
	ASSERT(   bst_contains(bst, "mike") ) ;
	ASSERT( ! bst_contains(bst, "andy") ) ;
	ASSERT(   bst_contains(bst, "zeke") ) ;
	ASSERT( ! bst_contains(bst, "bart") ) ;
	ASSERT( ! bst_contains(bst, "tom") ) ;
END_TEST()

START_TEST("Put andy in the list")
	bst = bst_insert(bst, "andy") ;
	ASSERT(   bst_contains(bst, "mike") ) ;
	ASSERT(   bst_contains(bst, "andy") ) ;
	ASSERT(   bst_contains(bst, "zeke") ) ;
	ASSERT( ! bst_contains(bst, "bart") ) ;
	ASSERT( ! bst_contains(bst, "tom") ) ;
END_TEST()

START_TEST("Put bart in the list")
	bst = bst_insert(bst, "bart") ;
	ASSERT(   bst_contains(bst, "mike") ) ;
	ASSERT(   bst_contains(bst, "andy") ) ;
	ASSERT(   bst_contains(bst, "zeke") ) ;
	ASSERT(   bst_contains(bst, "bart") ) ;
	ASSERT( ! bst_contains(bst, "tom") ) ;
END_TEST()

START_TEST("Put tom in the list")
	bst = bst_insert(bst, "tom") ;
	ASSERT(   bst_contains(bst, "mike") ) ;
	ASSERT(   bst_contains(bst, "andy") ) ;
	ASSERT(   bst_contains(bst, "zeke") ) ;
	ASSERT(   bst_contains(bst, "bart") ) ;
	ASSERT(   bst_contains(bst, "tom") ) ;
END_TEST()

END_TESTS()
