# Kocsen Chung
# LogItem Class Unit test.
# simple unit tests that willt test for the LogItem object functionality

require "test/unit"
require "./Log.rb"
require "./LogItem.rb"
require "date"

class LogUnitTest < Test::Unit::TestCase

	def setup
		@log = Log.new()
	end

	# Make sure that a LogItem Object can be intantiated and that
	# the correct variables are accessible.
	def test_initialization
		assert(@log.is_a?(Log), "Making sure log is a LOG Object")
	end

	def test_logToday
		assert(@log.empty?, "Log should be empty")
		todayDate = Date.today

		@log.logToday("TODAY FOOD")
		assert(@log.size == 1, "Size of log should be one" )
		assert(@log.has_date?(todayDate), "There should be a log in todays date ")		
	end

	def test_logSpecificDate
		assert(@log.empty?, "log should be empty")
		todayDate = Date.today
		date12 = Date.parse("2012-12-12")

		@log.logSpecificDate("TODAYFOOD", todayDate)
		assert(@log.size == 1 , "size of log should be one")
		assert(@log.has_date?(todayDate), "TodayFood should have todays date")

		@log.logSpecificDate("12FOOD", "2012-12-12")
		assert(@log.size == 2, "size of log == 2")
		assert(@log.has_date?(date12))
	end

	def test_ShowAll
		assert(@log.empty?, "Log should be empty")
		x = @log.showAll()
		assert(x == nil, "Method showAll returns nothing. If it ran fine returns nil")
	end

	def test_ShowToday
		assert(@log.empty?, "Log should be empty")
		x = @log.showToday()
		assert(x == nil, "Method showShowToday returns nothing. If it ran fine returns nil")
	end

	def test_printDate
		assert(@log.empty?, "Log should be empty")
		todayDate = Date.today

		x = @log.printDate(todayDate)
		assert( x == nil , "Method printDate runs fine if no returns.")

		@log.logToday("TODAY FOOD")
		x = @log.printDate(todayDate)
		assert( x == nil , "Method printDate runs fine if no returns.")
		assert( @log.size == 1, "log size should = 1 ")

		x = @log.printDate("ThisisNotaDate")
		assert( x == nil , "When given wrong parameter, no return is ok")
		assert( @log.size == 1, "log size should =1 ")

		x = @log.printDate("0000-90-90") #unattainable date
		puts x.to_s
		assert( x == nil , "When given wrong parameter, no return is ok")
		assert( @log.size == 1, "log size should =1 ")
	end

	def test_delete
		assert(@log.empty?, "log should be empty")
		todayDate = Date.today
		date12 = Date.parse("2012-12-12")

		@log.logSpecificDate("TODAYFOOD", todayDate)
		assert(@log.size == 1 , "size of log should be one")
		assert(@log.has_date?(todayDate), "TodayFood should have todays date")

		@log.logSpecificDate("12FOOD", "2012-12-12")
		assert(@log.size == 2, "size of log == 2")
		assert(@log.has_date?(date12))

		@log.delete("TODAYFOOD", todayDate)
		assert(@log.size == 1, "log size should be back to one after deletion")

		@log.delete("12FOOD", date12)
		assert(@log.size == 0, "log size should be 0 now because all items deleted")
	end

	def test_getOrderedArray
		assert(@log.empty?, "log should be empty")

		@log.logSpecificDate("PAST PEACH", Date.parse("1212-12-12") )
		@log.logSpecificDate("PRESENT PEACH", Date.parse("2000-12-12") )
		@log.logSpecificDate("FUTURE PEACH", Date.parse("2013-12-12") ) 
		sortedArray = @log.getOrderedArray

		assert(@log.has_date?(Date.parse("1212-12-12")), "There should be a entry for 1212-12-12")
		assert(@log.has_date?(Date.parse("2000-12-12")), "There should be a entry for 2000-12-12")
		assert(@log.has_date?(Date.parse("2013-12-12")), "There should be a entry for 2013-12-12")

		boolean = false
		assert( (sortedArray[0].foodName == "PAST PEACH"), "The first element should be past peach")
		assert( (sortedArray[1].foodName == "PRESENT PEACH"), "The first element should be PRESENT PEACH")
		assert( (sortedArray[2].foodName == "FUTURE PEACH"), "The first element should be FUTURE PEACH")
	end

end #end Test Class