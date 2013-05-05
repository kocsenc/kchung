#!/usr/bin/ruby
#
#
# Kocsen Chung
# main controller


require './fileio.rb'
require './DB.rb'
require './GUI.rb'



def main()
	#GUI.new
	sysout("Booting System")

	sysout("Loading Database...")
	db = parse()
	sysout("Loaded Database")

	puts("Welcome to the Pixel People Helper Program v2.0")
	puts("Latest Updates: System has been updated to include all of the professions")
	puts(" For help type in 'help' or 'h' ")
	edit = false
	while true do

		sysout( "Type in command please")
		print ">>"

		input = gets
		abort("EOF, terminating") if input == nil
		input = input.chomp
		input.downcase!

		case input
		when /unlock\s+/
			param = input.gsub(/unlock\s+/, "")
			param.upcase!
			db.unlock(param)
			edit = true

		when /lock\s+/
			param = input.gsub(/lock\s+/, "")
			param.upcase!
			db.lock(param)
			edit = true

		when /info\s+/
			param = input.gsub(/info\s+/,"")
			param.upcase!
			db.infoOn(param)

		when /have\s+/
			param = input.gsub(/have\s+/,"")
			param.upcase!
			db.have?(param)

		when /find\s+/
			param = input.gsub(/find\s+/,"")
			param.upcase!
			db.find(param)

		when /^queue\s+/
			param = input.gsub(/queue\s+/,"")
			param.upcase!
			db.addQueue(param)
			edit = true

		when /^dequeue\s+/
			param = input.gsub(/dequeue\s+/,"")
			param.upcase!
			db.removeQueue(param)
			edit = true

		when /^canmake\s+/
			param = input.gsub(/canmake\s+/,"")
			param.upcase
			db.canMake(param)
			edit = true		

		when "print queue"
			db.printQueue

		when "clear queue"
			db.clearQueue

		when "save"	
			if edit
				save(db)
			else
				sysout("No changes were made, cant save")
			end
			edit = false

		when "print unlocked"
			db.printAttainedProfessions

		when "print locked"
			db.printUnattainedProfessions

		when "print possible"
			db.printPossible

		when "help","h" #offer help
			sysout("******************** HELP PAGE ********************")
			sysout("|  ****COMMAND****   |  ****DESCRIPTION****       |  ")
			sysout("|   info [NAME]      | Get information about NAME |")
			sysout("|   have [NAME]      | Will show you if you have  |")
			sysout("|                    | that profession            |")
			sysout("|  canmake [NAME]    | what can profession make?  |")
			sysout("| queue/dequeue name | A convenience queue to use |") 
			sysout("|   print unlocked   | Will show unlocked         |")
			sysout("|                    | 	professions               |")
			sysout("|   print locked     | Show locked professions    |")
			sysout("|  print possible    |  Show what you can make    |")
			sysout("|  find [NAME]       | to find if profession exist|")
			sysout("| lock/unlock [NAME] | tell program to lock/unlock|")
			sysout("| ")


		when "exit", "exit()", "q", "quit()", "quit"
			shutdown(edit,db)
		else
			sysout("Invalid command\nType 'h or 'help' for help")
		end
		
	puts "\n"
	end #end while true loop

end

def sysout(par)
	puts "[SYSTEM] " + par.to_s
end

def shutdown(edit,db)
	sysout ("System going for shutdown")
	if edit
		save(db)
		sysout ("All changes have been saved...")
	else
		sysout ("No changes have been made")
	end
	abort ("Pixel People Helper Terminated")

end
	

main()
