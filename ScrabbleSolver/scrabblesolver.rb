#Kocsen Chung
#Scrabble Solver  -  Main Run method

	

def main()
	load "parser.rb"
	dictionary = [] #dictionary (array of words)
	anagramHash = Hash.new
	pointHash = Hash.new

	boolean = true
	if parseDictionary(anagramHash, dictionary) and parsePoints(pointHash) == true
		
		if dictionary.empty?
			print sysout"WARNING dictionary is empty ==> "
			puts dictionary.empty?
		end
		if anagramHash.empty?
			printprint sysout"WARNING anagramHash is empty ==> "
			puts anagramHash.empty?
		end
		puts "*******************************************************************************"
		puts "SCRUBBY Initialized ---- @KocsenCHUNG"
		

		while boolean
			puts
			puts "   -1 to exit \n    1 To make a move!\n    2 To print out dictionary"
			print ">> "
			command = gets.chomp!
			
			case command
			when "-1"
				systemShutdown()
			when "1"

				puts "Please insert your letters separated by a SPACE."
				hand = gets.chomp!.upcase
				puts "This is your input> " + hand
				puts "This is your input sorted: " + hand.split(' ').sort.join.upcase
				hand = hand.split(' ').sort.join
				print getPossibleWords.inspect

				


			when "2"
				print dictionary

			end
		end

	else
		systemShutdown()
	end
		
end

def getPossibleWords(input, anagramHash)
	puts sysout("Looking for possible words...")
	anagramHash.each {|sortedWord,actualWords| 
		#puts sysout"Searching through " + sortedWord
		if sortedWord.eql? input
			puts
			print sysout"FOUND MATCH! -> "
			return actualWords
		
		end

		}
	puts sysout"SORRY NO MATCHES"
	return []	
	
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
