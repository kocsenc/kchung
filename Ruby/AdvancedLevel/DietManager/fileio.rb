#Kocsen Chung
#Parser.rb: Parses the file input and file writing using CSV Ruby module


require "csv"
require "./DataBase.rb"
require "./Log.rb"


# This parses the Food Database only
# The log parsing is handled in parseLog()
# Uses CSV to parse each line into an array
def parse()
	begin
		database = FoodDB.new()
		CSV.foreach("./FoodDB.txt") do |line|
			if line[1] == "b"
				#DEBUG puts "Adding " + line[0] + " with " + line[2] +  " calories to dbase."
				database.addFood(line[0], line[2])
			elsif line[1] == "r"  
				#DEBUG puts "Adding recipe " + line[0] + " to dbase."
				database.addRecipe(line[0], line[2..(line.size-1)])
			else
				abort( "Critical Error parsing FOOD file in parser.rb")
				return
			end #end ifelse block

		end #end foreach block

		return database
	rescue
		abort("Critical Error parsing FOOD file in parser.rb")
	end

end #end method

# Method called to save the foods to the file
# Makes a new array and pushes the foods in first
# Then pushes the recipes in order of recipe dependancy.
# So, if there is a recipe that depends on more recipies, then
# it is pushed last, hence written last.
def save(db)
	begin
		printArray = db.getOrderedArray()
		# DEBUG: puts "Size of array is : " + printArray.size.to_s
		CSV.open("./FoodDB.txt", "wb") do |csv|
			printArray.each { |x|
				if x.is_a?BasicFood
					csv << [x.name, "b", x.cal.to_s] #write Food
				end
			}

			# Block for Recipes (printed last)
			printArray.each { |y|
				if y.is_a?Recipe
					temp = [y.name,"r"]
					y.ingredients.each{ |ing|
						if ing.is_a?BasicFood
							for i in (1..ing.count) do temp.push(ing.name) end
						else #if ingredient is a recipe
							temp.push(ing.name)
						end
					}
					csv << temp
				end
			} 


		end #end CSV.open file
	rescue
		abort("Critical Error saving FOODS file in parser.rb")
	end

end #end method save
		

# This method parses the log from the file: DietLog.txt
# Creates a new instance of the Container Class called Log
# and begins parsing in the logs in the txt file.
# See Log.rb to see how they are 
def parseLog(database)
	begin 
		log = Log.new()
		CSV.foreach("./DietLog.txt") do |line|
			if line.size > 2
				abort( "Critical Error parsing LOG file in parser.rb")
			else
				#DEBUG: puts "Logging food " + line[1] +" @ date: " + line[0]
				if !database.contains?(line[1])
					sysout("CRITICAL ERROR: Attempting to parse log with food that no longer/doesn't exists. Log has not been parsed")
				else
					log.logSpecificDate(line[1], line[0], ) #(foodname,date)
				end
			end #end if/else block

		end #end for/each block

		return log
	rescue
		abort( "Critical Error parsing LOG file in parser.rb")
	end
end #end parseLog Method


# Method that saves the log file to DietLog.txt
# Gets ordered array (sorted by date), 
# and writes file, oldest logs first.
def saveLog(log)
	begin
		ary = log.getOrderedArray()
		CSV.open("./DietLog.txt", "wb") do |csv|
			ary.each{ |x|
				csv << [x.date.to_s, x.foodName]
			}
		end
	rescue
		abort("Critical Error saving LOG file in parser.rb")
	end
end #end saveLog

