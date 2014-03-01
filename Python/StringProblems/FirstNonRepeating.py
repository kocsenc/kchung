import unittest

__author__ = 'Kocsen'
# Kocsen Chung
# Problem from previous interview

# Implement an algorithm that will determine which is the first element in a string that does not repeat itself ever


def first_non_repeat_of(str):
    # Method will go through string and construct dictionary of counts
    count = {}
    for char in str:
        if char not in count:
            count[char] = 1
        else:
            count[char] += 1

    # Retraverse through string by comparing counts from count{}.
    for char in str:
        if count[char] == 1:
            return char

    return False


class UniqueCharactersTest(unittest.TestCase):
    """
    Unit test for first_non_repeat_of
    """

    def test_edge(self):
        """ Tests edge cases """
        self.assertFalse(self.assertFalse())

    def test_space(self):
        """ TESTS spaces """
        self.assertEqual(" ", first_non_repeat_of(" hello"))
        self.assertFalse(first_non_repeat_of(" hello "))

    def test_no_nonreapeats(self):
        """ TESTS no repeats """
        self.assertFalse(first_non_repeat_of("hello"))
        self.assertFalse(first_non_repeat_of("aabbccdd"))
        self.assertFalse(first_non_repeat_of("abcdefGa"))

    def test_repeats(self):
        """ Test real cases """
        self.assertEqual("a", first_non_repeat_of("abbccddeeff"))
        self.assertEqual("a", first_non_repeat_of("abcdefg"))
        self.assertEqual("f", first_non_repeat_of("aabbccddeeFFFFFFFFf"))


if __name__ == "__main__":
    unittest.main()