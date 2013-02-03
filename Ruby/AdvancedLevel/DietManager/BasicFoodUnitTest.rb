# Kocsen Chung
# BasicFoodUnitTest
# Unit Tests for Basic Food Class

require "./DataBase.rb"
require "test/unit"

class BasicFoodUnitTest < Test::Unit::TestCase

	# No action on setup
	def setup
		puts "Basic Food Unit Test Setup"
	end

	# Test initalize, and accessing
	def test_initialization
		food1 = BasicFood.new("QUICHE" , "123", 1)
		assert( food1.name.to_s == "QUICHE" , "Food 1 name should = QUICHE")
		assert( food1.cal == 123 , "Calories should = 123")
		assert( food1.count == 1, "count should be 1")
	end

	# Testing to string method. NAME_CALORIES is the convention
	def test_to_s
		food1 = BasicFood.new("QUICHE" , "123", 1)
		puts food1.to_s
		assert((food1.to_s).eql?("QUICHE 123"), "to_s test1")
		food1.count += 1
		assert( food1.count == 2, "Count should = 2")
		assert(food1.to_s.eql?("QUICHE (2) 246"), "to_s test2")
	end

end#End Class
