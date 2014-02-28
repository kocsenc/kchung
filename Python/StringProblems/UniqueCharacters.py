# Kocsen Chung
# Problem from Cracking the Coding Interview by Gayle McDowell

# Implement an algorithm to determine if your string has all unique Characters
# """ note assuming Extended ASCII""""

import sys
import unittest


def is_unique(str):
    # Cannot be longer than 256 chars without repeating
    if len(str) >= 256:
        return False

    present_ary = []
    for char in str:
        if char in present_ary:
            return False
        else:
            present_ary.append(char)
    return True


class UniqueCharactersTest(unittest.TestCase):
    """
    Unit tests for is_unique method
    """

    def test_empty_string(self):
        """ Tests empty string"""
        self.assertTrue(is_unique(""))

    def test_passing_words(self):
        """ Normal passing words"""
        self.assertTrue(is_unique(" "))
        self.assertTrue(is_unique("abc"))
        self.assertTrue(is_unique("yole"))
        self.assertTrue(is_unique("aeiou"))
        self.assertTrue(is_unique("qwertyuiopasdfghjklzxcvbnm"))
        self.assertTrue(is_unique("`1234567890-=][poiuytrewasdfghjkl;'/.,mnbvcxz"))
        self.assertTrue(is_unique("qwertyuioplkjhgfdsazxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM"))
        self.assertTrue(is_unique("~!@#$%^&*()_+QWERTYUIOPSDFGHJKLZXCVBNM"))
        self.assertTrue(is_unique(""))
        self.assertTrue(is_unique("OH oh"))

    def test_false_words(self):
        """ Words that should not pass """

        self.assertFalse(is_unique("AA"))
        self.assertFalse(is_unique("aa"))
        self.assertFalse(is_unique("!!"))
        self.assertFalse(is_unique("A,A"))
        self.assertFalse(is_unique("!@#$%^&*()(*&^%TGHJU^Y%T$REWFDSFGTY&U%^EY%TSERAFSDZVFGSTARWEFSDFRAETARGDZFSE"))
        self.assertFalse(is_unique("ABBA"))
        self.assertFalse(is_unique("!#$%@#^@#%@#$%"))
        self.assertFalse(is_unique("Complication is Futie"))
        self.assertFalse(is_unique("I love a dove"))
        self.assertFalse(is_unique("  "))
        self.assertFalse(is_unique("AlA"))


if __name__ == "__main__":
    unittest.main()
