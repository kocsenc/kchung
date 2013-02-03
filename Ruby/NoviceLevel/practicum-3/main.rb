#Kocsen Chung
# does to phonetic conversion when prefixed with A2P
# does to alphabetic convesion when prefixed with P2A 
#see phonetic.rb for method calls

require './phonetic'

p = Phonetic.new
input = STDIN.read
input.each_line{|line|

	case line
	when /A2P\s+/
		query = line.gsub(/A2P\s+/, "")
		puts p.to_phonetic(query)

	when /P2A\s+/
		query = line.gsub(/P2A\s+/, "")
		puts p.from_phonetic(query)

	else
		puts p.auto_detect(line)

	end
}
