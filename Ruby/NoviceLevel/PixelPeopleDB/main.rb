#!/usr/bin/ruby
#
#
# Kocsen Chung
# main controller


require './fileio.rb'
require './DB.rb'



def main()
	sysout("Booting System")

	sysout("Loading Database...")
	db = parse()
	sysout("Loaded Database")

	puts("Welcome to the Pixel People Helper Program v0.1")
	puts(" For help type in 'help' or 'h' ")
	while true do

		sysout( "Type in command please")
		print ">>"

		input = gets
		abort("EOF, terminating") if input == nil
		input = input.chomp
		input.downcase!

		case input
		when /info\s+/
			param = input.gsub(/info\s+/,"")
			param.upcase!
			db.infoOn(param)

		when /have\s+/
			param = input.gsub(/have\s+/,"")
			param.upcase!
			db.have?(param)

		when "show unlocked"
			db.printAttainedProfessions

		when "show locked"
			db.printUnattainedProfessions

		when "help","h" #offer help
			sysout("******************** HELP PAGE ********************")
			sysout("|  ****COMMAND****   |  ****DESCRIPTION****       |  ")
			sysout("|   info [NAME]      | Get information about NAME |")
			sysout("|   have [NAME]      | Will show you if you have  |")
			sysout("|                    | that profession            |")
			sysout("|   show unlocked    | Will show unlocked         |")
			sysout("|                    | 	professions               |")
			sysout("|   show locked      | Show locked professions    |")

		else
			sysout("Invalid command\nType 'h or 'help' for help")
		end
		

	end #end while true loop

end

def sysout(par)
	puts "[SYSTEM] " + par.to_s
end
	

main()
