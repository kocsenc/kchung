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

    if node.val >= val:
        to_insert.next = node
    else:
        curr = node
        while curr.next.val < val:
            curr = curr.next

        to_insert.next = curr.next
        curr.next = to_insert


def insert_at(head, node, index):
    if index == 0:
        node.next = head
    else:
        try:
            curr = head
            for i in range(1, index):
                curr = curr.next
            node.next = curr.next
            curr.next = node
        except:
            pass
