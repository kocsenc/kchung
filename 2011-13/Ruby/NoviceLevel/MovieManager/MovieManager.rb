# Movie Manager 
# 
# Manages a collection of movie objects. 
# 
# Name: Kocsen chung
#
# Change Log - Identify the modifications made to the orginal code
# (1) - @numberOfMovies -= 1 when deleting
# (2) - added return statements for personal preference
# (3) - Can also use Hash.size to return size of movielist
# (4) - Changed sortByName to return a array of names of movies
# (5) - made sure that when deleting the object was present.
# (6) - Added own implementation of sorting by Rating. reverse sort
# (n) -
class MovieList
  def initialize
    @movies = Hash.new
  end
  
  def size
	return @movies.size
   end
  
  def add(movieToAdd )
    @movies.store( movieToAdd.name, movieToAdd) #key,value
  end
  
  def delete(movieToDelete)    
    if @movies.delete(movieToDelete.upcase) == nil
      #Object was not found and not deleted
    else
    end
  end
  
  def getMovie(movieToFind)
    @movies.fetch(movieToFind.upcase, nil) 
  end
  
  def sortByName
    ary = Array.new
    list = Array.new
    @movies.each {|k,v| list.push(v) }
    list.each {|x| ary.push(x.name) }
    ary.sort!
    return ary   

  end
  
  def sortByRating
    ary = Array.new
    list = Array.new
    @movies.each {|k,v| list.push(v) }
    ary = list.sort_by { |x| x.rating if x!=nil}
    ary.reverse!
    return ary
  end    
    
end

# Movie Class - Models a movie entry - maintains movie title as an uppercase string,
#                     rating (1-10) and text review.
#
# Change Log - Identify the modifications made to the orginal code
# (1) - added name as a reader
# (2) - added default rating to be 5
# (3) - added handling for rating default to be 5 changed or to and
# (n) -  

class Movie
    attr_reader :rating, :name
    attr_reader :review
    attr_writer :review
      
    def initialize( aName, aRating=5, aReview="No Review Entered" )
      @name=aName.upcase! 
      aRating = aRating.to_i
      aRating = 5 if aRating == 0
      @rating = self.rating=(aRating)
	    @review=aReview
      #puts ("Making a movie with name : " + aName.upcase! + "and rating" + aRating)

    end

    def rating=(value)
      if value <=10 and value >=1 
		    return value
      else
        return  5
      end
    end
end
