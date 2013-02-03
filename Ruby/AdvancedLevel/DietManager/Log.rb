# Kocsen Chung
# Log.rb: File made for keeping track of logged items

require "date"
require "./DataBase.rb"
require "./LogItem.rb"

# The class log is similar to that of the DataBase.rb
# This class is a container for all the logs, which 
# are Log objects. Uses a Hash to hold all the log items
# The KEY is a Date object, and the value is an array of 
# Log items which contain the date again and the food name
class Log
	# Log is a hash. 
	# Key = DATE
	# Value = Array of Log items
	def initialize()	
		@log = Hash.new
	end
	
	# Called when cli command log name is called.
	# will add the log to today's date given the
	# food name. 
	def logToday(foodName)
		date = Date.today
		foodName.upcase!
		logSpecificDate(foodName, date)
	end

	# Method use to populate the log in parsing or when adding a
	# Specific food on a specific date
	# Note: Food has already been checked for existence
	def logSpecificDate(foodName, date)
		# Checks for invalid date format
		if !date.is_a?Date
			return sysout("The date you entered [" + date.to_s + "] is not valid date format. \nUse 'YYYY-MM-DD' or use 'help' command") if (date =~ /(^\d{4}-\d{2}-\d{2}$)/)== nil
			begin 
				date = Date.strptime(date, "%Y-%m-%d") 
			rescue
				return sysout("The date entered is not a valid date")
			end
		end

		sysout("Note: the food your logging is in the future") if date > Date.today
		foodName.upcase!
		logitem = LogItem.new(foodName, date, 1)

		if @log[date] == nil
			dateArray = (Array.new).push(logitem)
			@log[date] = dateArray

		elsif @log[date].is_a?Array
			contains = false
			@log[date].each { |x|
			 	if x.foodName == logitem.foodName
			 		x.count += 1
			 		contains = true
			 	end
			}
			@log[date].push(logitem) if !contains			


		else
			puts "Error in logSpecificDate: Array never initialized"
		end
		


	end #end logSpecificDate method

	# Will print all log entries in the log
	# Calls printDate method to print each date.
	def showAll()
		logs = @log.sort
		puts "******************LOG****************OLD\n"
		logs.each { |date,item| printDate(date)	} 
		puts "******************LOG END************NEW\n"

	end 

	def showToday()
		puts "******LOGS for TODAY:" + Date.today.to_s + "**********\n"
		date = Date.today
		printDate(date)
		puts "******************LOG END***************\n"
	end

	# Prints the lgos for a single date
	def printDate(date)
		if !date.is_a?Date
			return sysout("The date you entered [" + date.to_s + "] is not valid date format. \nUse 'YYYY-MM-DD' or use 'help' command") if (date=~(/(^\d{4}-\d{2}-\d{2}$)/)) == nil
			begin 
				date = Date.strptime(date, "%Y-%m-%d") 
			rescue
				return sysout("The date entered is not a valid date")
			end
		end

		## AT this pint date should be a DATE object
		if @log[date] == nil || !(@log[date].is_a?Array)
			sysout("Notice, there is no log items for the date: " + date.to_s)
		else
			puts "========================================" #40
			puts "\t\t" + date.to_s + "\t\t"
			@log[date].each{ |x| puts x.to_s }
			puts "========================================"
		end
	end


	# Deletes a certain LogItem given the name and the date
	# The name is checked if is in the food database
	# It will delete the item (or reduce count) from array
	def delete(name,date)
		name.upcase!
		 if !date.is_a?Date
		 	return sysout("The date you entered [" + date.to_s + "] is not valid date format. \nUse 'YYYY-MM-DD' or use 'help' command") if (date=~(/(^\d{4}-\d{2}-\d{2}$)/)) == nil
			begin 
				date = Date.strptime(date, "%Y-%m-%d") 
			rescue
				return sysout("The date entered is not a valid date")
			end
		end

		## Date should be Date Object

		if @log[date] == nil
			return sysout("Error, there is no log for the date: " + date.to_s)
		else
			
			# tentatively nice solution @log[date].delete_if{ |x| x.foodName == name && x.count < 2}
			deleted = false
			@log[date].each{ |logEntry| 
				if logEntry.foodName == name
					if logEntry.count > 1
						logEntry.count -= 1
						deleted = true
						break
					else 
						@log[date].delete(logEntry)
						deleted = true
						break
					end
				end
			}
			sysout("Error: No such food [" + name + "] on date " + date.to_s )  if !deleted

		end
		#@log[date] = nil if @log[date].size == 0
		@log.delete_if{ |k,v| v.size == 0}
	end

	# This method adds all log items into an array
	# and then sorts them via date
	# This method is being called when saving the log file
	def getOrderedArray()
		ary = Array.new
		@log.each{ |k,v|
			if @log[k] != nil
				@log[k].each{ |x|
					ary.push(x)
				}
			end
		}
		ary.sort!
		return ary

	end



	# Checks if empty. boolean
	def empty?
		@log.empty?
	end

	#returns size of Log 
	def size
		@log.size
	end

	# Takes in a Date Object and shows if that date is available
	def has_date?(date)
		@log.has_key?(date)
	end

	# Finds a LogItem Object given the parameters and returns it.
	def getLogItem(date,name)
		if @log[date] == nil
			return sysout("No logs for date")
		else
			@log[date].each{ |x| return x if x.foodName == name}
		end
	end
	

end #end Log Class

# System backend print stream
# Will print given string as System
def sysout(string)
	puts ("[SYSTEM]: " + string)  
end
