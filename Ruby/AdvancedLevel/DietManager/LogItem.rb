# Kocsen Chung
# LogItem.rb
# this is the Class which encapsulate a single logged item.
require "date"
require "./DataBase.rb"


class LogItem
	include Comparable
	attr_accessor :foodName, :date, :count

	# A LogItem Object will have 2 
	# parameters. the name (string) and the date (Date object)
	def initialize(foodName, date, count) #NOTE: Expects to get DATE object
		foodName.upcase!
		@foodName = foodName
		@date = date
		@count = count.to_i
		
	end

	# To string method. 
	# Format: YYYY-MM-DD,FOODNAME
	def to_s
		return (@date.to_s + "," + @foodName.to_s) if count <= 1
		return (@date.to_s + "," + @foodName.to_s + " (" + @count.to_s + ")")
	end

	# Used for comparable class
	def <=>(anotherLogItem)
		if self.date < anotherLogItem.date
			-1
		elsif self.date > anotherLogItem.date
			1
		else
			0
		end
	end
end #end LogItem Class
