# Kocsen Chung
# Profession class 

class Profession
	attr_accessor :name, :combo,:productivity,:workplace,:gameName,:unlocked

	def initialize(name,combo,productivity,workplace,gameName,unlocked)
		@name = name
		@combo = combo
		@productivity = productivity
		@workplace = workplace
		@gameName = gameName
		@unlocked = unlocked
	end

	def getName
		return @name
	end
		
		
	def getExtra
		return("Is he unlocked? " + @unlocked.to_s + "\n\t Productivity: " + @productivity + "\n\t Workplace: "+ @workplace + "\n\t Has Game Name: " + @gameName) 
	end

	def has_to_s
		return @name +" => " + @unlocked.to_s
	end

	def getComboNames
		combo = @combo.split("+")
		combo.each{|x| x.strip!}
		return combo
	end


end #end Class
