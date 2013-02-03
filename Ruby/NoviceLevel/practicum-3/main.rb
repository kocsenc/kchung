#Kocsen Chung

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
