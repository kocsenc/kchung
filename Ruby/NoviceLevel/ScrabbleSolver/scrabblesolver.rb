# Kocsen Chung
# Scrabble Solver  -  Main Run method
# Teaching myself Ruby
	

def main()
	load "parser.rb"
	dictionary = [] #dictionary (array of words)
	anagramHash = Hash.new
	pointHash = Hash.new
	puts sysout("INITIALIZING SCRUBBY SYSTEM")
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
			puts "   -1 to exit \n    1 To make a move!\n    2 To find in dictionary"
			print ">> "
			command = gets.chomp!
			
			case command
			when "-1"
				systemShutdown()
			when "1"

				puts "Please insert your letters (<11)"
				hand = gets.chomp!.upcase
				
				puts "This is your input sorted: " + hand.split(' ').sort.join.upcase
				hand = hand.split(' ').sort.join
				

				resultArray =  everyPermutation(hand, anagramHash, pointHash).sort_by {|x| x.length}.reverse
				#puts resultArray.inspect
				printOutBestResults(resultArray, pointHash)


				


			when "2"
				puts "Please insert word to search:"
				input = gets.chomp!.split(//).sort.join.upcase
				searchWord(input, anagramHash)


			end
		end

	else
		systemShutdown()
	end
		
end

def everyPermutation(word, anagramHash, pointHash)
	puts sysout("Word is " + word)
	#puts sysout("Word split(//) is " + word.split(//).inspect)

	mainWord = word.clone()
	
	wordSize = mainWord.split(//).size
	puts sysout("wordSize = " + wordSize.to_s())
	puts sysout("Searching for ALL results...")

	counter = 0
	permCounter = 0
	maxPointsAttained = 0
	results = []
	beenThereDoneThat = []


	allTheWords = word.chars.to_a.permutation.map &:join
	
	
	for daWord in allTheWords do
		#puts sysout("Trying with " + daWord + "...")
		while permCounter < wordSize
			#puts sysout("Iteration #" + permCounter.inspect)
			
			while counter < wordSize 
				canCheck1 = true
				canCheck2 = true
				canCheck3 = true
				wiq = daWord.split(//)


				split1 = wiq[counter, wordSize].sort.join
				#puts sysout("split1 = " + split1)
				if split1.split(//).size < 2 or beenThereDoneThat.include?(split1) 
					beenThereDoneThat.push()
					canCheck1 = false
				end

				beenThereDoneThat.push(split1)

				if canCheck1
					queryResult1 = getPossibleWords(split1, anagramHash)
					if queryResult1 != []
						queryResult1.each { |q| 
							if !results.include?(q) #and getPoints(q, pointHash) > maxPointsAttained
								
								results.push(q)
							else
								#puts sysout("Found duplicate, not pushing: " + q)
							end
							}
						#puts sysout("Since no matches, no push")
						
					end
				end
				
				#SPLIT 2 BEGIN HERE#####################################################
				split2 = wiq[0 ,counter].sort.join
				if split2.split(//).size < 2 or beenThereDoneThat.include?(split2)
					canCheck2 = false
				end
				
				beenThereDoneThat.push(split2)

				if canCheck2
					queryResult2 = getPossibleWords(split2, anagramHash)
					if queryResult2 != [] 
						queryResult2.each { |w|
							if !results.include?(w) #and getPoints(w, pointHash) > maxPointsAttained
								#puts sysout("Pushing " +w)
								results.push(w)
							else
								#puts sysout("Found duplicate, not pushing: " + w)
							end
						}
						
					end
				end
				
				# SPLIT 3 BEGIN HERE##################################################
				if wiq.slice(counter+permCounter, wordSize-(counter+permCounter)) != nil
					split3 = ((wiq.slice(0, counter) + wiq.slice(counter+permCounter, wordSize-(counter+permCounter))))
					split3 = split3.sort.join
					

					if split3.split(//).size < 2 or beenThereDoneThat.include?(split3)
						canCheck3 = false
					end

					if canCheck3
						queryResult3 = getPossibleWords(split3, anagramHash)
						if queryResult3 != [] 
							q ueryResult3.each { |z|
								if !results.include?(z) #and getPoints(z, pointHash) > maxPointsAttained
									#puts sysout("Pushing " +w)
									results.push(z)
								else
									#puts sysout("Found duplicate, not pushing: " + w)
								end
							}
							
						end
					end
				end

				
				counter += 1
				
			end
			permCounter += 1
			counter = 0

			
		end
	end
	return results 

end


def getPossibleWords(input, anagramHash)
	#puts sysout("Looking for words that match...")

	if anagramHash.has_key?(input)
		#puts sysout("FOUND MATCH! -> " + anagramHash.fetch(input).inspect)
		return anagramHash.fetch(input)
	else 
		return []

	end


	
end

def printOutBestResults(list, pointHash)
	puts ""
	puts "Success"
	puts "Top 5 results ^by points:"
	finalWords = []
	for words in list
		finalWords.push(Word.new(words, getPoints(words, pointHash)))
	end
	finalWords.sort_by {|x| x.points}.reverse
	for i in 0...5
		puts "		" + finalWords[i].to_s
	end
end



def getPoints(word, pointHash)
	points = 0
	word.split(//).each {|x|
		points += pointHash.fetch(x)
	}
	return points
end

def searchWord(input, anagramHash)
	query = getPossibleWords(input, anagramHash)
	if query != []
		puts "YES that word exists!"
	else
		puts "NO, sorry word not found."
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

class Word
	attr_accessor :word, :points

	def initialize(word, points)
		@word = word
		@points = points
	end

	def to_s
		"[" + @points.to_s + "]: " +@word
	end
end




main()
