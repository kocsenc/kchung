# Convert to/from phonetic alphabet
# SOLUTION

class Phonetic

  LETTERS = Hash[
             'A', 'ALPHA',
             'B', 'BRAVO',
             'C', 'CHARLIE',
             'D', 'DELTA',
             'E', 'ECHO',
             'F', 'FOXTROT',
             'G', 'GOLF',
             'H', 'HOTEL',
             'I', 'INDIA',
             'J', 'JULIET',
             'K', 'KILO',
             'L', 'LIMA',
             'M', 'MIKE',
             'N', 'NOVEMBER',
             'O', 'OSCAR',
             'P', 'PAPA',
             'Q', 'QUEBEC',
             'R', 'ROMEO',
             'S', 'SIERRA',
             'T', 'TANGO',
             'U', 'UNIFORM',
             'V', 'VICTOR',
             'W', 'WHISKEY',
             'X', 'XRAY',
             'Y', 'YANKEE',
             'Z', 'ZULU'
             ]

  # Translate a line to its phonetic alphabet equivalent
  def to_phonetic(line)
	line.upcase!
	ary = line.split(" ")
	result = Array.new
	ary.each{ |x| 
		x.upcase!
		x.gsub!(/[^A-Z]/, '') #eliminate not words
		x.split("").each { |letter|
			letter.upcase!
			result.push(LETTERS[letter])
		}
	}
	result = result.join(" ")
	return result

  end


  # Translate a sequence of phonetic alphabet code words 
  # to their alphabetic equivalent
  def from_phonetic(line)
	line.upcase!
	ary = line.split(" ")
	result = Array.new
	ary.each{ |x|
		x.upcase!
		# Shouldnt have to gsub, expecting correct phonetic
		LETTERS.each{ |letter,phonetic|
			if x == phonetic
				result.push(letter)
			end
		}
		
	}
	result = result.join("")
	return result
  end

  # Auto-detect if the line is phonetic or not. 
  # Does this by checking if the first word is in the phonetic alphabet
  # Delegates to from_phonetic or to_phonetic
  def auto_detect(line)
	normal = true
	line.upcase!
	ary = line.split(" ")
	ary.each{ |x|
		normal = false if LETTERS.has_value?(x)
	}
	result = ary.join(" ") #back to normal state
	if normal
		to_phonetic(result)	
	else
		from_phonetic(result)
	end

  end
end

