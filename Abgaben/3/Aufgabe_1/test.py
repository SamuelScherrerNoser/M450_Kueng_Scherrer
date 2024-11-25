import unittest
from calculator import Calculator

class CalculatorTest(unittest.TestCase):
    def setUp(self):
        self.cut = Calculator()

    def test_add(self):
        self.assertEqual(cut.add(1, 2), 3)

    def test_subtract(self):
        self.assertEqual(cut.subtract(2, 1), 1)

    def test_multiply(self):
        self.assertEqual(cut.multiply(2, 3), 6)

    def test_divide(self):
        self.assertEqual(cut.divide(6, 3), 2)
