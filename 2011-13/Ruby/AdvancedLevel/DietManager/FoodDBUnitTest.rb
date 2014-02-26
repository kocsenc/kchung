# Kocsen Chung
# FooDBUnitTest.rb: Testing DataBase.rb

require "./DataBase.rb"
require "test/unit"

class FoodDBUnitTest < Test::Unit::TestCase

	# setup
	def setup
		@db = FoodDB.new()
	end

	# Test for empty DB
	def test_for_empty_DB
		puts "TEST 1"
		assert( @db.empty? , "DB is not empty")
	end

	# Test for adding single food
	def test_add_1food
		puts "TEST 2"
		assert( @db.empty?, "DB should be empty" )
		@db.addFood("Potato", "123")
		assert( @db.size == 1, "Size of DB != 1")
		assert( !@db.empty?, "DB should not be empty")
	end

	# Test for adding multiple foods
	# Size alteration
	def test_add_multiple_foods
		puts "TEST 3"
		@db.addFood("Potato", '123')
		@db.addFood("Mac and Cheese", "12345")
		assert( @db.size == 2, "Size of Db != 2")

		@db.addFood("potATo", "124")
		assert( @db.size == 2, "Size of Db != 2-Should not be able to add duplicate")

		@db.addFood("Mac AND Cheese", "123")
		assert( @db.size == 2, "size of DB != 2 - Shouldnt be able to add duplicate")

		@db.addFood("hashtag", "624")
		assert( @db.size == 3, "size of db should be 3 after adding food hashtag")

		@db.addFood("NewFood", "not a number1234")
		assert( @db.size == 3, "Should not be able to add food with no number as calorie")
		assert( @db.is_Food?( "potato" ) , "potato should be a food")
		assert( @db.is_Food?( "mac and cheese" ) , "mac and cheese should be a food")
		assert( @db.is_Food?( "hashtag" ) , "hashtag should be a food")
		assert( !@db.is_Food?( "NewFood" ), "NewFood was not added, not a food")

		
	end

	# Test for adding 1 recipe
	def test_add_1_recipe
		puts "TEST 4"
		assert( @db.empty?, "DB should be empty" )
		@db.addRecipe("Cookies", ["Flour", "BakingSoda"] )
		assert( @db.empty?, "DB should still be empty after adding recipe with non existing foods" )
		@db.addFood("Flour", "84" )
		@db.addFood("BakingSoda", "123" )
		assert( @db.size == 2, "there should be 2 foods,ready for recipe" )
		@db.addRecipe("Cookies", ["Flour", "BakingSoda"] )
		assert( @db.size == 3, "With recipe, size should == 3" )
		assert( @db.is_Recipe?("Cookies") , "Cookies should be a recipe" )
	end

	# Test for adding multiple recipes
	def test_add_multiple_recipes
		puts "TEST 5"
		assert( @db.empty?, "DB should be empty" )

		@db.addFood("burger", "124124")
		@db.addFood("Bacon", "814")
		@db.addFood("Lettuce", "12")
		@db.addFood("Tomato","14")
		@db.addRecipe("BLT" , ["bacon","lettuce","tomato"])
		@db.addRecipe("Bacon Burger", ["Bacon","burger"])

		assert( @db.size == 6, "There should be 6 items in db, 4 foods, 2 recipes")
		assert( @db.is_Recipe?("BLT") , "Should be BLT recipe" )
		assert( @db.is_Recipe?("Bacon Burger") , "bacon burger should be a recipe" )
		
		@db.addRecipe("BLT" , ["bacon","lettuce","tomato"])
		assert( @db.size == 6, "Cannot add duplicate recipies")

		@db.addRecipe("NewRecipe", [])
		assert( @db.size == 6, "cannot add recipe with empty foods")

		@db.addRecipe("Copy Recipe", ["bacon", "bacon"])
		assert( @db.size == 7, "Size should be 7, add copy food on recipe")

	end

end
