#!/usr/bin/ruby
#Kocsen Chung
#Main.rb - Calls the main methods and is the home of the CLI

require "./DataBase.rb"
require "./fileio.rb"


# No class, simply a main method
# Will boot up the system by parsing the food and the log files
# Then will give all the options.
# For detailed information on the option, run the program and type
# in the command 'h' or 'help'
# Will handle saving when shutting down appropriately/exit
def main()
	sysout("Booting System")

	sysout("Parsing FOOD file...")
	database = parse()	

	sysout("Parsing LOG file...")
	log = parseLog(database)

	helpCounter = 0		
	editted = false		#boolean that changes if FoodDB has been changed(add/delete)
	#######BEGIN CLI HERE ########
	puts"*** Welcome to the DietManager v1.0 ***"
	while true do
		
		sysout( "Type in your command please")
		print ">> "
		# Handle command input BEGIN
		
		input = gets
		abort("EOF, terminating program...") if input == nil
		input = input.chomp
		input.downcase!
		case input
		when "print all"			#print all the foods
			sysout("Begin Printing Foods...")
			database.printDB("")

		when /print\s+/	 			# Print [name] in database
			query = input.gsub(/print\s+/, "")
			database.printDB(query)

		when /find\s+/				# Find [prefix] in database & print
			preQuery = input.gsub(/find\s+/, "")
			database.find(preQuery)
			
		when "save"					# if any files were altered, save and shutdown
			if editted
				save(database)
				saveLog(log)
				editted = false
				sysout("OK")
			else
				sysout("No changes have been made since last save/startup")
			end

		when /new food\s+/			# add new food
			info = (input.gsub(/new food\s+/, "")).split(",")
			if info.size == 2
				database.addFood(info[0], info[1]) 
				editted = true
			else
				sysout("ERROR\nUsage: 'new food name,calories'\nTry again please")
			end

		when /new recipe\s+/		# add new recipe
			info = (input.gsub(/new recipe\s+/,"")).split(",")
			if info.size >= 2
				database.addRecipe(info[0], info[1..(info.size-1)]) 
				editted = true
			else
				sysout("ERROR\nUsage: 'new recipe name,food1,food2..'\nTry again please")
			end

		when /log\s+/				# log new food (handles food and food,date cases)
			info = (input.gsub(/log\s+/,""))
			info = info.split(",")
			if !database.contains?(info[0].to_s)
				sysout("The food: " + info[0].to_s + " is not in the Database. Use command new food or 'h' for help")
			elsif info.size <= 1 #if it only has the name
				log.logToday(info[0]) #logToday(name)
				editted = true
				sysout("OK")
			elsif info.size == 2
				log.logSpecificDate(info[0],info[1]) #log(name,date)
				editted = true
				sysout("OK")
				
			else
				sysout("ERROR\nUsage: 'log name,date' \t or \t 'log name' \nTry again please")
			end
		
		
		when "show all"  			#shows all the logs in ascending order
			log.showAll()

		when /delete\s+/ 			#deletes log of name,date
			info = (input.gsub(/delete\s+/,""))
			info = info.split(",")
			if !database.contains?(info[0].to_s)
				sysout("The food: " + info[0].to_s + " is not in the Database. Use command new food or 'h' for help")
			elsif info.size == 2
				log.delete(info[0],info[1]) #name,date
				editted = true
				sysout("OK")
			else
				sysout("ERROR\nUsage: 'delete name,date'\nTry again please")
				editted = true
				sysout("OK")
			end

		when "show"  				#show logs for today
			log.showToday()

		when /show\s+/				#show the logs for the given date
			info = (input.gsub(/show\s+/, ""))
			log.printDate(info)


		when "quit", "exit", "exit()", "quit()" #Initiate shutdown sequence and save
			shutdown(editted, database, log)
		
		when "h", "help"			# offer help
			sysout("******************** HELP PAGE ********************")
			sysout("|  ****COMMAND****   |  ****DESCRIPTION****    |  ")
			sysout("| print all          | Prints out all food DB  |  ")
			sysout("| print [name]       | Prints [name] out       |  ")
			sysout("| find [prefix]      | Searches using prefix   |  ")
			sysout("| quit/exit          | Exit Diet Manager       |  ")
			sysout("| save               | Saves all data          |  ")
			sysout("| new food [n],[c]   | Add food name[n], cal[c]|  ")
			sysout("|new recipe [n][f][f]|AddRecipe name[n]foods[f]|  ")
			sysout("| log [n]            | Add food[n] to daily log|  ")
			sysout("| log [n],[d]        | Add food[n] at date[d]  |  ")
			sysout("| delete [n],[d]     | Delete food[n] form log |  ")
			sysout("| show               | Shows today's food log  |  ")
			sysout("| show [d]           |Show food log for date[d]|  ")
			sysout("| show all           | Show all logs, ascending|  ")
			sysout("| h/help             | Bring you to help screen|  ")
			sysout("| ***** DATES: YYYY-MM-DD format please *****  |  ")
			sysout("|         Input is NOT case/sensitive          |  ")
			sysout("| Recipes will have a ':' following their name |  ")
			sysout("|      Numbers in () are quantity of foods     |  ")
			sysout("|    Numbers with no () is the CALORIE amount  |  ")
			sysout("|  Dates are displayed oldest->newest (ascend) |  ")
			puts "\n"

		else						# for usability, have a counter to display help option
			sysout("Invalid Command, type 'h' for help")
			helpCounter+=1
			if helpCounter >= 3
				puts "Type command 'h' for help." 
				helpCounter = 0
			end

		end	# Handle command input END
		puts ""
	######### END CLI HERE #########

	end #ends While loop
end #end main

# Will initiate shutdown sequence
# 1- advise of shutdown
# 2- Save all files 
# 3- Quit program with message(abort)
def shutdown(editted, database, log)
	sysout("System going for Shutdown")
	save(database)
	saveLog(log)
	abort("Diet Manager Terminated")
end

# System backend print stream
# Will print given string as System
def sysout(string)
	puts ("[SYSTEM]: " + string)  
end

main()

