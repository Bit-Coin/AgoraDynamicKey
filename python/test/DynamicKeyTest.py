import sys
import unittest
import os

sys.path.append(os.path.join(os.path.dirname(__file__), '../src'))
import DynamicKey

statickey = "970ca35de60c44645bbae84215061b33b"
signkey = "5cfd2fd1755d40ecb72977518be15d3b"
channelname = "7d72365eb983485397e3e3f9d460bdda"
unixts = 1446455472
uid = 2882341273
randomint = 58964981
expiredts = 1446455471


class DynamicKeyTest(unittest.TestCase):

    def test_generate(self):
        expected = "70e0e5b0a137630190bfa475506de9fb56012576970ca35de60c44645bbae84215061b33b14464554720383bbf5"
        actual = DynamicKey.generate(
            statickey, signkey, channelname, unixts, randomint)
        self.assertEqual(expected, actual)

if __name__ == "__main__":
    unittest.main()