Kocsen Chung
Readme.txt

Status: Functional,Actively Developing

To run:
		# Run the executable main.rb
		$ ./main.rb
		or
		$ ruby main.rb

 Objective:	
 			Implement a small diet manager in Ruby. To do this the system mantains a database of foods. The foods are either basic foods and Recipes. Recipe scan contain foods and/or recipes within. In addition, the system will mantaina  log of foods that the user consumes each day. The commands will allow users to add and delete foods, to log and unlog foods for a given date, to save the log and food database, and to print a variety of reports.

 		The Food Database
			The food database is a simple text file, named FoodDB.txt. The file has one text line per food, with the food and associated information in CSV format. There are two types of lines, representing the two types of food, basic and recipe. b for food and r for recipe.

		The Interactive Command Language

			quit
				Saves the food database and the log (if they've changed) and exits. This also occurs if the program encounters end of file.

			save
				Saves the food database and log (if changed) without exiting.

			new  food  name,calories
				Saves a new basic food and its calories. Reports an error and does nothing if a food of the given name is already in the database.

			new  recipe  name,name1,...,nameN
				Saves a new recipe name consisting of the N named foods that follow. If name is already in the database, or if any of the N constituent food names are not in the database, report an error and do nothing.

			print name
				Prints basic information on the named food (if there is no such food, print an error message and do nothing)
				For a basic food, simply print the food name and the calories:

					Orange 67

				For recipes, the constituents are printed out hierarchically, using as many levels as required. A constituent food is printed with the number of units used in the recipe and the total calories.

					PB&J Sandwich 490
					  Bread (2) 160
					  Peanut Butter 175
					  Jelly 155

			print all
				Prints information on all the foods in the database, using the format(s) shown above.

			find  prefix
				Prints information on all the foods in the database that begin with the given prefix. In matching the prefix, the case of the letters is ignored.

			log name
				Adds one unit of the named food to the log for today. Simply prints a message if the food is not in the database.

			log name,date
				Adds one unit of the named food to the log for the specified date. Simply prints a message if the food is not in the database.

			delete name,date
				Removes one unit of the named food from the log for the given date. Does nothing if the food is not in the log on that date.

			show
				Shows the log of foods for today. If a food occurs several times, it is printed just once with the number of units following the name in parentheses.

			show date
				Shows the log of foods for the given date as with plain show above.

			show all
				Shows the log of foods for all dates in the log, organized by ascending date. The log for each day is preceded by a line with the day's date.