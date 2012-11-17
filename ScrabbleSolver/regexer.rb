#Regexer


def regexThis(amount)
	result = ["/"]
	

	for i in 1..amount 
		result.push (".")
	end
	result.push("/")
	return result

end


def main()
	puts "input #"
	input = Integer(gets.chomp!)
	puts "This is the regexReturn: " + regexThis(input).join
	puts "This is 'hello'.scan(regex) =+= "
	word = "hello"
	word.scan(regexThis(input).join){ |b| puts b }
		
	puts "----END PROGRAM-----"


end
main()



