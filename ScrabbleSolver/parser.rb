#parser.rb
#Kocsen Chung
#This file parses the dictionary file from dictionary.txt
# and also assigns the points of each letter.

def parseDictionary(anagramHash, dictionary)
	puts sysout"Parsing Dictionary and anagramHash..."
	file = File.new("dictionary.txt", "r")
	counter = 0
	while (line = file.gets)
		parsePalindrome(line.upcase.chomp, anagramHash)
		dictionary.push(line.upcase.chomp)
		counter += 1
	end
	file.close
	puts sysout"Dictionary and anagramHash Parsed..."
	return true
end
	
def parsePalindrome(input, anagramHash)
	
	#puts "Word key = " + input.chars.sort.join
	#puts "Word Value = " + input

	if anagramHash[input.chars.sort.join].nil?
		#puts "Was nil. Making new k/v pair"
		anagramHash[input.chars.sort.join] = [input]
		
	else
		#puts "Was not nil. Adding value to key -> " + input.chars.sort.join
		anagramHash[input.chars.sort.join].push(input)
	end

end





def parsePoints(pointHash)
	pointHash['A'] = 1
	pointHash['B'] = 3
	pointHash['C'] = 3
	pointHash['D'] = 2
	pointHash['E'] = 1
	pointHash['F'] = 4
	pointHash['G'] = 2
	pointHash['H'] = 4
	pointHash['I'] = 1
	pointHash['J'] = 8
	pointHash['K'] = 5
	pointHash['L'] = 1
	pointHash['M'] = 3
	pointHash['N'] = 1
	pointHash['O'] = 1
	pointHash['P'] = 3
	pointHash['Q'] = 10
	pointHash['R'] = 1
	pointHash['S'] = 1
	pointHash['T'] = 1
	pointHash['U'] = 1
	pointHash['V'] = 4
	pointHash['W'] = 4
	pointHash['X'] = 8
	pointHash['Y'] = 4
	pointHash['Z'] =10
	pointHash['BLANK'] = 0
	return true
end
