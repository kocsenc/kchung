__author__ = 'Kocsen'
"""
This is the file with methods that will do basic things with linked lists
"""
from commonlib.LinkedList import *


def insert_ordered(node, val):
    """
     Inserts a node in an ordered
    :param node: HEAD node
    :param val: the value to insert
    """
    to_insert = Node(val)

    curr = node
    while curr.next.val < val:
        curr = curr.next

    to_insert.next = curr.next
    curr.next = to_insert

