require './DiGraph'
require 'test/unit'

class DiGraphTest < Test::Unit::TestCase

  # Create a new graph for the next test

  def setup
    @myGraph = DiGraph.new
  end

  def test_for_empty_graph
    assert( @myGraph.empty?, "New graph not empty")
	assert( (@myGraph.numVertices==0), "New graph size != 0")
  end

  def test_for_sizeOne_graph
	@myGraph.addVertex("US")	
	assert( !@myGraph.empty?, "New graph is not size 1")
	assert( @myGraph.numVertices==1, "New graph size != 1")
	assert( @myGraph.vertex?("US"), "New graph does not contain US")
	
  end

  def test_for_2Vertex
	@myGraph.addVertex("US")
	@myGraph.addVertex("Canada")
	assert( !@myGraph.empty?, "New graph is not size 2")
	assert( (@myGraph.vertex?("US") && @myGraph.vertex?("Canada")) ,"Graph does not have US or Canada1")
	assert( @myGraph.numVertices == 2, "Should be 2 vertices" )
	@myGraph.addEdge("US","Canada")
	@myGraph.addEdge("Canada", "US")
	assert( @myGraph.vertex?("US") && @myGraph.vertex?("Canada"),"Graph does not
	have US or Canada2")
	assert( @myGraph.edge?("US","Canada"), "There is no edge from US to	Canada1")
	assert( @myGraph.edge?("Canada","US"), "There is no edge from Canada to US2")
	assert( @myGraph.outDegree("US")==1, "Out degree of US != 1")
	assert( @myGraph.outDegree("Unknown")==0, "Out degree of unknown != 0")
	assert( @myGraph.outDegree("Canada")==1, "Out degree of US != 1")
	assert( @myGraph.inDegree("US") == 1 , "In degree of US != 1" )
	assert( @myGraph.inDegree("Canada") == 1, "In Degree of Can != 1" )
	assert( @myGraph.inDegree("Nothing") == 0, "In degree of blank ! 0 ")
		
  end

  def test_for_2pair_vertex
	@myGraph.addEdge("US","Canada")
	@myGraph.addEdge("Canada","US")
	@myGraph.addEdge("Peru", "Ecuador")
	@myGraph.addEdge("Ecuador","Peru")
	@myGraph.addVertex("Greenland")

	assert( @myGraph.outDegree("Peru") == 1 , "Out degree of Peru != 1")
	assert( @myGraph.outDegree("US") == 1, "Out degree of US != 1")
	assert( @myGraph.outDegree("Canada") == 1, "Out degree of Can != 1")
	assert( @myGraph.outDegree("Greenland") == 0, "Out degree of GLand !=0" )
	#Testing for one directional edges
	#Because I can
	@myGraph.addEdge("Greenland", "Peru")
	assert( @myGraph.outDegree("Greenland") == 1, "Green land should have 1 out degree")
	assert( @myGraph.inDegree("Peru") == 2 , "Peru should have 2 in Degree")
	assert( @myGraph.numVertices == 5, "Should be 5 vertices in graph!")
  end


end
