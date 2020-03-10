import unittest
from dataretrieval import get_scans

class Tests(unittest.TestCase):
    def dr_test(self):
        print("Testing Radar information")
        expected = ["<AwsNexradFile object - 2016/05/31/KTLX/KTLX20160531_000848_V06.gz>"]
        result = get_scans('2016','05','31','KTLX')
        self.assertEqual(result,expected,msg = "Scan failed")

if __name__ == '__main__':
	print("Test component for Data Retrieval")
	unittest.main()
