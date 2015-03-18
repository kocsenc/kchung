/*
 * Test cases for the double ended queue problem.
 */

#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include "bool.h"
#include "dequeue.h"
#include "tests.h"	/* simplectest definitions */

#define MAX_TEST_LENGTH 20    // max size of string contents used in testing

// Start the overall test suite, "main()" starts here

START_TESTS()  
// IMPORTANT - note that each unit test starts with its own empty dequeue !!
// dequeue is declared here and visible to each test case, but it is set to NULL
// at the start of each unit test.
// This removes dependencies between test cases - a good unit test practice.
//
// The astute student will notice that some tests may end with orphaned allocated
// memory references that we did not deallocate (a put without a get for example).
// For the sake of practicum sanity, we will let that go here. In reality we would
// would want to make sure that we were cleaning that up properly during testing, or
// perhaps write a deq_delete() function.

// These variables are visible to all tests
     node* dequeue;                       /* the dequeue head */
     char get_value[MAX_TEST_LENGTH];     /* string content copied here for get functions */
     char put_value1[] = "ONE";           /* string content test values for put functions */
     char put_value2[] = "TWO";
     char put_value3[] = "THREE";

START_TEST("size of zero for an empty dequeue")
     dequeue = NULL; 			/* dequeue initally empty */
     ASSERT_EQUALS( 0, deq_size( dequeue ));
END_TEST()

START_TEST("check for empty dequeue")
     dequeue = NULL; 			/* dequeue initally empty */
     ASSERT(deq_is_empty( dequeue ));
END_TEST()

START_TEST("put one entry at head of empty dequeue")
     dequeue = NULL; 			/* dequeue initally empty */
     deq_put_head( &dequeue, put_value1);
     ASSERT_EQUALS( 1, deq_size( dequeue ));
END_TEST()

START_TEST("check for non-empty dequeue")
     dequeue = NULL; 			/* dequeue initally empty */
     deq_put_head( &dequeue, put_value1);
     ASSERT(!deq_is_empty( dequeue ));
END_TEST()

START_TEST("put & get one entry from head of dequeue")
     dequeue = NULL; 			/* dequeue initally empty */
     deq_put_head( &dequeue, put_value1);
     deq_get_head( &dequeue, get_value);
     ASSERT_EQUALS( 0, strncmp( put_value1, get_value, strlen(put_value1)));
END_TEST()

START_TEST("put two entries & get two entries from head of dequeue")
     dequeue = NULL; 			/* dequeue initally empty */
     deq_put_head( &dequeue, put_value1);
     deq_put_head( &dequeue, put_value2);
     ASSERT_EQUALS( 2, deq_size( dequeue ));  
     deq_get_head( &dequeue, get_value);
     ASSERT_EQUALS( 0, strncmp( put_value2, get_value, strlen(put_value2)));     
     deq_get_head( &dequeue, get_value);
     ASSERT_EQUALS( 0, strncmp( put_value1, get_value, strlen(put_value1)));
END_TEST()

START_TEST("put & get one entry from tail of dequeue")
     dequeue = NULL; 			/* dequeue initally empty */
     deq_put_tail( &dequeue, put_value1);
     ASSERT_EQUALS( 1, deq_size( dequeue ));   
     deq_get_tail( &dequeue, get_value);
     ASSERT_EQUALS( 0, strncmp( put_value1, get_value, strlen(put_value1)));
END_TEST()

START_TEST("put two entries & get one entry from tail of dequeue")
     dequeue = NULL; 			/* dequeue initally empty */
     deq_put_head( &dequeue, put_value1);
     deq_put_head( &dequeue, put_value2);
     deq_get_tail( &dequeue, get_value);
     ASSERT_EQUALS( 0, strncmp( put_value1, get_value, strlen(put_value1)));
     ASSERT_EQUALS( 1, deq_size( dequeue ));   
END_TEST()

// end tests visible to students

START_TEST("*** try to get one entry from head of empty dequeue")
     dequeue = NULL; 			/* dequeue initally empty */
     deq_get_head( &dequeue, get_value);
     ASSERT( dequeue == NULL );
END_TEST()

START_TEST("*** try to get one entry from tail of empty dequeue")
     dequeue = NULL; 			/* dequeue initally empty */
     deq_get_tail( &dequeue, get_value);
     ASSERT( dequeue == NULL );
END_TEST()

START_TEST("*** put & get one entry from head of dequeue, verify dequeue NULL")
     dequeue = NULL; 			/* dequeue initally empty */
     deq_put_head( &dequeue, put_value1);
     deq_get_head( &dequeue, get_value);
     ASSERT_EQUALS( 0, strncmp( put_value1, get_value, strlen(put_value1)));
     ASSERT( dequeue == NULL );
END_TEST()

START_TEST("*** put & get one entry from tail of dequeue, verify dequeue NULL")
     dequeue = NULL; 			/* dequeue initally empty */
     deq_put_tail( &dequeue, put_value1);
     ASSERT_EQUALS( 1, deq_size( dequeue ));   
     deq_get_tail( &dequeue, get_value);
     ASSERT_EQUALS( 0, strncmp( put_value1, get_value, strlen(put_value1)));
     ASSERT( dequeue == NULL );
END_TEST()

START_TEST("*** put two entries & get two entries from head of dequeue, verify dequeue NULL")
     dequeue = NULL; 			/* dequeue initally empty */
     deq_put_head( &dequeue, put_value1);
     deq_put_head( &dequeue, put_value2);
     ASSERT_EQUALS( 2, deq_size( dequeue ));  
     deq_get_head( &dequeue, get_value);
     ASSERT_EQUALS( 0, strncmp( put_value2, get_value, strlen(put_value2)));     
     deq_get_head( &dequeue, get_value);
     ASSERT_EQUALS( 0, strncmp( put_value1, get_value, strlen(put_value1)));
     ASSERT( dequeue == NULL );
END_TEST()

START_TEST("*** put two entries & get two entries from tail of dequeue, verify dequeue NULL")
     dequeue = NULL; 			/* dequeue initally empty */
     deq_put_tail( &dequeue, put_value1);
     deq_put_tail( &dequeue, put_value2);
     ASSERT_EQUALS( 2, deq_size( dequeue ));  
     deq_get_tail( &dequeue, get_value);
     ASSERT_EQUALS( 0, strncmp( put_value2, get_value, strlen(put_value2)));     
     deq_get_tail( &dequeue, get_value);
     ASSERT_EQUALS( 0, strncmp( put_value1, get_value, strlen(put_value1)));
     ASSERT( dequeue == NULL );
END_TEST()

START_TEST("*** put one entry on head & get one entry from tail of dequeue, verify dequeue NULL")
     dequeue = NULL; 			/* dequeue initally empty */
     deq_put_head( &dequeue, put_value1);
     deq_get_tail( &dequeue, get_value);
     ASSERT_EQUALS( 0, strncmp( put_value1, get_value, strlen(put_value1)));
     ASSERT( dequeue == NULL );   
END_TEST()

START_TEST("*** put one entry on tail & get one entry from head of dequeue, verify dequeue NULL")
     dequeue = NULL; 			/* dequeue initally empty */
     deq_put_tail( &dequeue, put_value1);
     deq_get_head( &dequeue, get_value);
     ASSERT_EQUALS( 0, strncmp( put_value1, get_value, strlen(put_value1)));
     ASSERT( dequeue == NULL );   
END_TEST()

// End the overall test suite
END_TESTS()   
