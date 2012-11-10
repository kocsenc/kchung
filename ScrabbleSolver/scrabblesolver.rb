#Kocsen Chung
#Scrabble Solver  -  Main Run method







def main()
	load "parser.rb"
	#puts "Input dictionary file .txt"
	#input = gets.chomp!

	dictionary = [] #dictionary (array of words)
	pointHash = Hash.new


	boolean = true
	if parseDictionary("k", dictionary) and parsePoints(pointHash) == true
		puts "SCRUBBY Initialized ---- @KCHUNG"
		while boolean
			puts "   -1 to exit \n    1 To make a move!\n    2 To print out dictionary"
			print "> "
			command = gets.chomp!
			
			case command
			when "-1"
				systemShutdown()
			when "1"
				
			when "2"
				puts dictionary

			end
		end




	else
		
		puts sysout"GoodBye -- Progam Exit"
		System.exit(0)
	end

		
end

def systemShutdown()
	puts "Shutting down system; GoodBye!"
	boolean = false
	exit(0)
end




def sysout(text)
	return "[SYS]	" + text 
end


	main()
