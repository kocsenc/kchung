# Kocsen Chung
# LogItem Class Unit test.
# simple unit tests that willt test for the LogItem object functionality

require "test/unit"
require "./LogItem"
require "date"

class LogItemUnitTest < Test::Unit::TestCase

	def setup
		#puts "LogItem Unit Test Setup"
	end

	# Make sure that a LogItem Object can be intantiated and that
	# the correct variables are accessible.
	def test_initialization
		item1 = LogItem.new("FOODNAME", Date.parse("2012-10-10"), 1)
		assert( item1.foodName == "FOODNAME" , "item1 foodname should = FOODNAME")
		dateTest = Date.parse("2012-10-10")
		assert( (item1.date).eql?(dateTest), "item1 date should be 2012-10-10 object")
		assert( item1.count == 1, "item1 count should be 1")

		item1.foodName = "POTATO"
		assert( item1.foodName == "POTATO")

		item1.count+=1
		assert( item1.count == 2)
	end

	# test to string method
	def test_to_s
		item1 = LogItem.new("PEACH", Date.parse("1212-12-12"), 1)
		assert( item1.to_s == "1212-12-12,PEACH" )
	end

	# test comparator
	def test_comparator #comparator based on date
		item1 = LogItem.new("PAST PEACH", Date.parse("1212-12-12"), 1)
		item2 = LogItem.new("PRESENT PEACH", Date.parse("2000-12-12"), 1)
		item3 = LogItem.new("FUTURE PEACH", Date.parse("2013-12-12"), 1)

		assert( item1<item2, "item1 should be < item 2")
		assert( item2<item3, "item2 should be < item 3")

		unsorted = [item3,item1,item2]
		sorted = [item1,item2,item3]

		unsorted.sort!
		assert( unsorted==sorted, "the sort should arrange in increasing order")
	end


end	#end LogITemUnitTest Class
