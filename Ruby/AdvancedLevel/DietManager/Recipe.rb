# Kocsen Chung
# Recipe.rb
# Contains the Recipe Class and methods



## Create the class Recipe
## Has name, calorie and foods atrribute
class Recipe
	attr_accessor :name, :cal, :ingredients	

	# name is the name of the Recipe
	# Calories is the summed ammount of the calories per ingredient
	# Ingredients is an array of STRINGS representing the key of the DB
	def initialize(name, calories, ingredients)
		@name = name
		@cal = calories
		@ingredients = ingredients
	end

	# The to_s method for a Recipe
	# Will print the recipe name and then the containing
	# foods/recipes, which are indented by 1\t space
	def to_s()
		result = name + " " + cal.to_s + ":\n"
		ingredients.each { |x| result+= ("\t" + x.to_s + "\n") }
		return result
	end


end


		
