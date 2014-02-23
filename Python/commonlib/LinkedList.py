__author__ = 'Kocsen'
"""
Simple Library used for linked list problems.
Will contain:
- Singly Linked Lists
- Doubly Linked Lists
Use this as a way to practice my data structures
"""


class Node:
    """ Node for a singly linked list"""

    def __init__(self, val, next=None):
        self.val = val
        self.next = next

    def __str__(self):
        return str(self.val)

    def get_all(self):
        entire_list = ""
        curr = self
        while curr.next != None:
            entire_list += str(curr.val) + " "
            curr = curr.next
        return entire_list


class DoublyNode:
    """ Node for a doubly linked list"""

    def __init__(self, val, next=None, prev=None):
        self.val = val
        self.next = next
        self.prev = prev

    def __str__(self):
        return str(self.val)
