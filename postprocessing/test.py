import unittest
from postprocessing import plot_radar
import warnings
with warnings.catch_warnings():
    warnings.filterwarnings("ignore",category=DeprecationWarning)
    import pyart,matplotlib

class Tests(unittest.TestCase):
    
    def test_dr1(self):
        print("------------------------------Testing PostProcessing 1------------------------------")
        expected = "success"
        result,plot = plot_radar('KTLX20160531_000848_V06.gz')
        self.assertEqual(result,expected,msg = "Plot 1 failed")

    def test_dr2(self):
        print("------------------------------Testing PostProcessing 2------------------------------")
        expected = "success"
        result,plot = plot_radar('KIND20181211_000109_V06')
        self.assertEqual(result,expected,msg = "Plot 2 failed")

if __name__ == '__main__':
	print("------------------------------Test component for Post Processing------------------------------")
	unittest.main()
