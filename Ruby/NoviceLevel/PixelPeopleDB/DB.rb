# Kocsen Chung
# Database 
#

require './Profession'

class DB

	def initialize()
		@db = Hash.new()
		@queue = Array.new()
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
			puts "=================== " + @db[name].getName + " ======================"
			comboArray = @db[name].getComboNames
			#puts comboArray.inspect
			if @db[comboArray[0]]!=nil and @db[comboArray[1]]!=nil
				print "Combination:\t"	
				puts( @db[comboArray[0]].has_to_s + " + " + @db[comboArray[1]].has_to_s)
				puts "\n"
			else
				puts "Combination:\t\t NA or Requires special Profession"
				puts "\n"
			end

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

	def lock(name)
		name.upcase!
		if @db.has_key?(name)
			@db[name].unlocked = false
			puts name+ " was locked!"
		else
			puts name+ " was not found!"
		end

	end

	def unlock(name)
		name.upcase!
		if @db.has_key?(name)
			@db[name].unlocked = true
			puts name+ " was unlocked!"
		else
			puts name+ " was not found!"
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

	# print the professions which you have the resources for
	def printPossible
		@db.each{|k,v| 
			combo = v.getComboNames
			if combo.size == 2 and @db[combo[0]]!=nil and @db[combo[1]] !=nil
				puts v.getName if @db[combo[0]].unlocked and @db[combo[1]].unlocked and !v.unlocked
			end
		}
	end

	def find(param)
		param.upcase!
		@db.each{|k,v| puts v.getName if (v.getName).start_with?(param)}	
	end	

	def addQueue(name)
		name.upcase!
		if @db.has_key?(name)
			@queue.push(@db[name])
			@queue.uniq!
		else
			puts name+ " was not found as a Profession!"
		end
	end

	def printQueue()
		puts @queue.inspect
	end

	def getQueue()
		return @queue
	end

	def removeQueue(name)
		name.upcase!
		if @queue.has?(@db[name])
			@queue.delete(@db[name])
			@queue.uniq!
		else
			puts name + " was not in queue!"
		end
	end


	def getArray()
		ar = Array.new
		@db.each { |k,v| ar.push(v)	}
		return ar
	end

end #Class end
