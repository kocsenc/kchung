#parser.rb
#Kocsen Chung
#This file parses the dictionary file from dictionary.txt
# and also assigns the points of each letter.

def parseDictionary(input, dictionary)
	puts sysout"Parsing Dictionary..."
	file = File.new("dictionary.txt", "r")
	counter = 1
	while (line = file.gets)
		dictionary.push(*line.upcase!)
		counter = counter + 1
	end
	file.close
	puts sysout"Dictionary Parsed..."
	return true
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
