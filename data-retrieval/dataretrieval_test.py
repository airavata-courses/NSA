import unittest
from init_test import get_scans

class Tests(unittest.TestCase):

    def test_dr1(self):
        print("Testing Radar information")
        expected = "<AwsNexradFile object - 2016/05/31/KTLX/KTLX20160531_000848_V06.gz>"
        result = get_scans('2016','05','31','KTLX')
        self.assertEqual(result,expected,msg = "Scan 1 failed")

    def test_dr2(self):
        print("Testing Radar information")
        expected = "<AwsNexradFile object - 2013/05/31/KTLX/KTLX20130531_000358_V06.gz>"
        result = get_scans('2013','05','31','KTLX')
        self.assertEqual(result,expected,msg = "Scan 2 failed")

if __name__ == '__main__':
	print("Test component for Data Retrieval")
	unittest.main()