# Kocsen Chung
# FoodDB
# File designated to hold the database class and the 
# Ordinary food class


require "./Recipe.rb"

# Class FoodDB
# This class handles addition, searching, printing and general 
# processing of all of the foods and recipes.
# The Class holds a hash as a database, and the key of the Hash
# is the name of the recipe or the foods. The value of the hash
# is either a BasicFood Object, or a Recipe Object.
# See BasicFood or Recipe for more details.
# For convention, all strings are upcased 
class FoodDB
	
	#initialize method. Makes a new hash
	def initialize()
		@database = Hash.new
	end

	# Adds a basic Food to the Food database
	# by upcasing the name and creating a new food.
	# Check wheather calories are digits, and 
	# check whether the food already exists.
	def addFood(name, calories)
		name.upcase!
		return sysout( "Calories for food " + name + " are not all digits: ["+calories+"]") if !calories.match(/^[\d]+(\.[\d]+){0,1}$/).is_a?MatchData
		if !@database.has_key? name 
			@database[name] = (BasicFood.new(name, calories, 1)) 
		else
			return sysout(name + " already exists!")
		end
	end

	# Adds a recipe to the food database
	# Upcases for case sensitivity
	# adds all calories together
	# creates an array of recipe foods (could be BasicFoods or Recipes
	# Error if name already exists
	# Error is the recipe has no foods to make it a recipe
	# loops through the foods and checks that each food is available
	# in the database. 
	# if the food is a recipe then make a new recipe object and push it to the new recipe array
	# if the food is a food, then make new food/or increase count if food exists
	# Always keeping track of total calories
	# All recipes on CLI are displayed with : at the end
	def addRecipe(name, foods) 

		name.upcase!
		return sysout(name + " recipe/food already exists! Use a valid name") if @database.has_key? name
		return sysout(name + " recipe has no foods!") if foods.size<1
		totalCal = 0
		foodArray = Array.new
		# upcasing for standardization
		# If there is no food in the recipe line, then dont loop
		foods.each{ |x| 
			x.upcase!    	#Upcase the foods input
			
			if !@database.has_key?x  # Check if DB has food
				return sysout("Critical Error: No such food/recipe [" + x.to_s + "] to add to recipe!")  
			elsif @database[x].is_a?Recipe # Check if Food is recipe
				rec = Recipe.new(@database[x].name, @database[x].cal, @database[x].ingredients)
				totalCal += @database[x].cal
				foodArray.push(rec)
			else
				# Create a food instance from the food name with the same
				# calories and add to final calorie count. If the food was already in the
				# recipe, add the count of that food instance +1
				contains = false
				tempCalorie = @database[x].cal
				totalCal += tempCalorie	
				food = BasicFood.new(x, tempCalorie, 1)
				
				# This block checks whether a reciepe already contained that food to double the calorie and count
				if foodArray.empty?
					foodArray.push(food)
					contains = true
				else
					foodArray.each { |foodInRList|  ## increase the count of food if DB contains
						if foodInRList.name.eql? food.name
							contains = true
							foodInRList.count += 1
						end

					}
				end #if/else block end
						
				foodArray.push(food) if !contains # push food to foodArray as long as its not already in there


			end #end if/elsif/else block
		} #end of loop through foods array
		
		# Finally, create the Recipe Class with name, cal, and foodArray
		@database[name] = (Recipe.new(name, totalCal, foodArray)) 
	end

	
	# Print methods
	# if parameter is "" then print all
	# else print parameter information
	def printDB(parameter)
		# "" means print all
		parameter.upcase!
		if parameter == ""
			@database.each { |key,val| 
				if val.is_a? BasicFood
					puts val.to_s
				else
					puts val.to_s
				end
			}
		# this means that a name was passed in
		elsif parameter.is_a?String
			if @database.has_key?parameter
				puts @database[parameter].to_s
			else
				sysout("Sorry, the item " + parameter + " was not found.")
			end
		else
			sysout("Unknown/not found argument: " + parameter + ". " )
		end
		
	end


	# Method that finds any foods with the given prefix.
	# In matching the prefix, the case of the letters is ignored.
	def find(prefix)
		found = false
		prefix = prefix.to_s
		prefix.upcase!
		@database.each { |k,v| 
			break if prefix.match(/\W{1,}/).is_a?MatchData
			if k.match(/^#{prefix}/).is_a?MatchData
				puts @database[k].to_s
				found = true
			end

		}

		puts "Sorry, item starting in " + prefix + " not found!" if !found
	end

	# This method makes an array that is sorted for fileio.rb to print nicely
	# It will assure that dependancies on creating recipes are met.
	# All foods are added first
	# then all the recipes in order of recipe dependancy.
	def getOrderedArray()
		oArray = Array.new

		@database.each { |k,v| oArray.push(v) if v.is_a?BasicFood }


		rCountHash = Hash.new
		@database.each { |k,v| 
			numRecipe = 0 	# For every recipe, set the count of recipes to 0
			if v.is_a?Recipe #it should be a recipe 
				v.ingredients.each { |inRecipeObject| numRecipe += 1 if inRecipeObject.is_a?Recipe }
			end

			if rCountHash[numRecipe] == nil 
				rCountHash[numRecipe] = [v]
			else
				rCountHash[numRecipe].push(v)
			end
		}

		rCountHash.each { |key,val| 
			val.each{ |recipe| oArray.push(recipe) }
		}
		oArray.uniq!
		return oArray

	end #end for gOA


	#return true if empty, false otherwise
	def empty?
		return @database.empty?
	end

	# return size of DB
	def size
		return @database.size
	end

	# Return if input is a recipe or not
	def is_Recipe?(inp)
		inp.upcase!
		b = false
		b = true if @database[inp].is_a?Recipe
		return b
	end

	# Return if variable inp is a food or not (boolean)
	def is_Food?(inp)
		inp.upcase!
		b = false
		b = true if @database[inp].is_a?BasicFood
		return b
	end

	def contains?(inp)
		inp.upcase!
		return @database.has_key?inp
	end

end #Class end

	
	# The basic Food class.
	# has attributes name and cal
class BasicFood
	attr_accessor :name, :cal, :count
	#attr_reader :name

	def initialize(name, calories, count)
		@name = name
		@cal = calories.to_i
		@count = count.to_i
	end

	def to_s()
		if self.count<=1
			return (name + " " + cal.to_s )
		else
			return (name + " (" + count.to_s + ") " + ((cal*count).to_s))  
		end
	end
end #Class end

def sysout(string)
	puts ("[SYSTEM]: " + string)  
end
