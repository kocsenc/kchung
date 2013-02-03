# Kocsen Chung 
# Recipe Unit Tests

require "test/unit"
require "./Recipe.rb"
require "./DataBase.rb"

class RecipeUnitTest < Test::Unit::TestCase

	def test_CreateRecipe
		food1 = BasicFood.new("FLOUR" , "32", 1)
		food2 = BasicFood.new("CHOCOLATE CHIP", "99", 1)
		calories = food1.cal + food2.cal
		foodArray = Array.new
		foodArray.push(food1,food2)
		rec = Recipe.new("COOKIES", calories, foodArray)

		assert( rec.is_a?(Recipe), "R should be a recipe")
		assert( rec.name == "COOKIES", "Name of recipe should be COOKIES")
		assert( rec.cal == calories, "Calories should be the calories variable")
		assert( rec.ingredients == foodArray, "ingredients should have food 1 and 2")
	end

	def test_to_s
		f1 = BasicFood.new("FLOUR" , "32", 1)
		f2 = BasicFood.new("CHOCOLATE CHIP", "99" , 1)
		calories2 = f1.cal + f2.cal
		foodArray2 = Array.new
		foodArray2.push(f1,f2)
		rec2 = Recipe.new("COOKIES", calories2, foodArray2)
		assert( rec2.to_s == "COOKIES 131:\n\tFLOUR 32\n\tCHOCOLATE CHIP 99\n", "to string method should print recipe")
	end

end
