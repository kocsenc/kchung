require './MovieManager.rb'
require 'test/unit'

class MovieManagerTest < Test::Unit::TestCase

 	def setup
 		@testList = MovieList.new
  end

  def test_smokeTest
    assert_not_nil( @testList, "Should be able to create a MovieList collection")
  end

  def test_problem1
  	movie1 = Movie.new("Lost", 5)
  	@testList.add(movie1)
  	assert( @testList.size == 1, "size not == 1")
  	assert( @testList.getMovie("Lost") == movie1, "movie 1 was not stored")
  end
  
  def test_problem2
		movie1 = Movie.new("Lost", 5)
		movie2 = Movie.new("ToyStory", 10)
		@testList.add(movie1)
		@testList.add(movie2)

		toystoryMovie = @testList.getMovie("ToyStory")
		assert( @testList.size == 2 , "list size should = 2")
		assert( toystoryMovie.name == "TOYSTORY" , "name == toystory")
		assert( toystoryMovie.rating == 10, 'RATING FOR TS should be 5')
		assert( toystoryMovie.review == "No Review Entered")

		(@testList.getMovie("ToyStory")).review = "Wonderful Pixar Movie"
		assert( toystoryMovie.review == "Wonderful Pixar Movie")
  end

  def test_problem3
  	movie1 = Movie.new("LosT", 5)
  	movie2 = Movie.new("ToySTory", 10)
  	movie3 = Movie.new("TOYSTORYtwo", 9)
  	@testList.add(movie1)
		@testList.add(movie2)
		@testList.add(movie3)

		assert( @testList.size == 3 , "list size should = 3")
		assert( (@testList.getMovie("ToyStory")).name == 'TOYSTORY', "name match1"  )

		assert( (@testList.getMovie("ToyStorytwo")).name == 'TOYSTORYTWO', "name match2"  )
		assert( (@testList.getMovie("LosT")).name == 'LOST', "name match3"  )

  end

  def test_problem4
  	movie1 = Movie.new("LosT", 5)
  	movie2 = Movie.new("ToySTory", 10)
  			
		assert( movie1.name == "LOST" , "Uppecase Lost")
		assert( movie2.name == "TOYSTORY" , "Uppecase TOYSTORY")

	end

  def test_problem5
  	movie1 = Movie.new("LosT", 1)
  	movie2 = Movie.new("ToySTory", 10)
  	movie3 = Movie.new("NoRatingMovie")
  	movie4 = Movie.new("BadinputRating", "10")
  	movie5 = Movie.new("MoreBadInput RAting", "daf")



  	assert( movie1.rating == 1 , "Movie Rating should be 1")
  	assert( movie2.rating == 10 , "Movie Rating should be 10")
  	assert( movie3.rating == 5 , "Movie Rating should be 5/default")
  	assert( movie4.rating == 10 , "Movie Rating should be 10")
  	assert( movie5.rating == 5 , "Movie Rating should be 5")


  end

  def test_problem6
  	movie1 = Movie.new("Lost", 5, "non default rating!")
		movie2 = Movie.new("ToyStory", 10)
		@testList.add(movie1)
		@testList.add(movie2)

		toystoryMovie = @testList.getMovie("ToyStory")
		assert( toystoryMovie.review == "No Review Entered")
		(@testList.getMovie("ToyStory")).review = "Wonderful Pixar Movie"
		assert( toystoryMovie.review == "Wonderful Pixar Movie")

		assert( @testList.getMovie("Lost").review == "non default rating!", "should be non default rating!")
		@testList.getMovie("Lost").review = "changing default rating"
		assert( @testList.getMovie("Lost").review == "changing default rating", "should be 'chnging default rating'")
  end

  def test_problem7
  	movie1 = Movie.new("LosT", 5)
  	movie2 = Movie.new("ToySTory", 10)
  	movie3 = Movie.new("TOYSTORYtwo", 9)
  	@testList.add(movie1)
		@testList.add(movie2)
		@testList.add(movie3)

		assert( @testList.size == 3, "list size should == 3")
		@testList.delete("Lost")
		assert( @testList.size == 2, 'list size now ==2 ')
		assert( @testList.getMovie("Lost") == nil , "movie lost should be nil")

		@testList.delete("should not be found")
		assert( @testList.size == 2, 'movie nto found, size unchanged.')

  end

  def test_problem8
  	movie1 = Movie.new("Another time", 5)
  	movie2 = Movie.new("Because of yo", 10)
  	movie3 = Movie.new("Cant stop", 9)
  	@testList.add(movie1)
		@testList.add(movie2)
		@testList.add(movie3)

		assert( @testList.size ==3 , "Size of list should be 3")
		
		array1 = @testList.sortByName
			
		assert( (array1.is_a?Array), "sortByname should return an Array object")
		assert( array1[0] == "ANOTHER TIME", "should start with A")
		assert( array1[2] == "CANT STOP", "Should end with C")

		
  end

  def test_problem9
		movie1 = Movie.new("Another time", 1)
  	movie2 = Movie.new("Because of yo", 5)
  	movie3 = Movie.new("Cant stop", 10)
  	@testList.add(movie1)
		@testList.add(movie2)
		@testList.add(movie3)

		array2 = @testList.sortByRating
		assert( (array2.is_a?Array), "sortByRating should return an Array object")
		puts array2[0].rating.to_s

		assert( array2[0].rating == 10 , "rating should be 10 onthe first value of list")
		assert( array2[2].rating == 1, "Rating should be 1 on the last val of list" )

  end
end 
