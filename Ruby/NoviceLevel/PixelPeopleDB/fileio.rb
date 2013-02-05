# Kocsen Chung
# handle the database and fileio
#


require 'csv'
require './DB'

def parse()
	begin
		db = DB.new()
		count = 1
		CSV.foreach("./ppeopleDB.dat") do |line|
			# Profession, combo, productivity, structure, gameName, has?
			line.each{|x| x.upcase! }#if x.is_a?String}
			puts "Size is: " + line.size.to_s + " Line number: " + count.to_s
			count += 1
			if line.size == 5
				db.add(line[0],line[1],line[2],line[3],line[4],false)
			elsif line.size == 6
				has = true if line[5] == "T"
				has = false if line[5] == "F"
				db.add(line[0],line[1],line[2],line[3],line[4],has)
			else
				abort ("Cirtical Error Parsing the DataBase")
			end

		end # CSV foreach end
		return db
	rescue
		abort("RESCUE: Critical Error parsing the DataBase")
	end #end try/catch
end #end method


def save(db)
	begin
		pplArray = db.getArray()
		CSV.open("./ppeopleDB.dat", wb) do |csv|
			pplArray.each { |person|
				ar = Array.new
				has = "T" if person.unlocked
				has = "F" if !person.unlocked
				ar.push(person.name, person.combo, person.productivity, person.workplace, person.gameName, has)
			csv << ar
			}


		end #end csv

	end #end try catch

end #end save method

