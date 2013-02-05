# Kocsen Chung
# Database 
#

require './Profession'

class DB

	def initialize()
		@db = Hash.new()
	end

	# Adds a profession object to the hash
	def add(name,combo,productivity,workplace,gameName,unlocked)
		name.upcase!
		@db[name] = Profession.new(name,combo,productivity,workplace,gameName,unlocked)
	end

	# Returns info on profession and the combination required plus extra info
	def infoOn(name)
		if @db.has_key?(name)
			name.upcase!
			puts @db[name].getName
			comboArray = @db[name].getComboNames
			puts( @db[comboArray[0]].has_to_s + " + " + @db[comboArray[1]].has_to_s) 
			puts @db[name].getExtra
		else
			puts name+" was not found!"
		end
	end

	# Returns the profession and wether you have it or not
	def have?(name)
		name.upcase!
		if @db.has_key?(name)
			puts @db[name].has_to_s
		else
			puts name + " was not found!"
		end
	end

	# print professions who have been unlocked
	def printAttainedProfessions
		@db.each{|k,v| puts v.getName if v.unlocked}
	end

	# print locked professions
	def printUnattainedProfessions
		@db.each{|k,v| puts v.getName if !v.unlocked}
	end
	
	def find(param)
		@db.each{|k,v| puts v.getName if v.start}	
	end	


	def getArray()
		ar = Array.new
		@db.each { |k,v| ar.push(v)	}
		return ar
	end

end #Class end
