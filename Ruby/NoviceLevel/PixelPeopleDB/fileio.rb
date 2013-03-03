# Kocsen Chung
# handle the database and fileio
#


require 'csv'
require './DB'

def parse()
	fileNameDB = "./ppeopleDB.csv"
	queueFileName = "./queue.dat"
	begin
		db = DB.new()
		count = 1
		CSV.foreach(fileNameDB) do |line|
			# name,combo,productivity,workplace,gameName,unlocked
			line.each{|x| x.upcase! }#if x.is_a?String}
			#DEBUG: puts "Size is: " + line.size.to_s + " Line number: " + count.to_s
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
	rescue
		abort("RESCUE: Critical Error parsing the DataBase")
	end #end try/catch


	begin
		CSV.foreach(queueFileName) do |line|
			line.each{|x| x.upcase! }
			#Debug:
			line.each{|x| db.addQueue(x) }

		end#end CSV foreach
	rescue
		abort("RESCUE: Critical Error parsing the queueFile")
	end #end try/catch 2



		return db

end #end method


def save(db)
	fileNameDB = "./ppeopleDB.csv"
	queueFileName = "./queue.dat"
	begin
		pplArray = db.getArray()
		CSV.open(fileNameDB, "wb") do |csv|
			pplArray.each { |person|
				ar = Array.new
				has = "T" if person.unlocked
				has = "F" if !person.unlocked
				ar.push(person.name, person.combo, person.productivity, person.workplace, person.gameName, has)
			csv << ar
			}


		end #end csv
	rescue
		abort("Rescue:Critical Error Saving DataBAse")

	end#end try catch 1


	begin
		CSV.open(queueFileName , "wb") do |csv|
			queueArray = db.getQueueIO
			saveArray = Array.new
			queueArray.each{|x| saveArray.push(x)}
			csv << saveArray
		end

	rescue
		abort("RESCUE: Critical Error Saving queue")
	end #end try catch 2
end #end save method

