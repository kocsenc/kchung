#
# DiGraph - Implements a directed graph using an adjacency list
#
# T. Reichlmayr - 01/26/07
# M. Lutz - 4/20/2011

class DiGraph 
  # Create the mapping hash from known vertices to an array
  # of immediate neighbors.

  def initialize
    # Hash of vertices, mapping each vertex to its immediate neighbors
    @vertices = Hash.new
  end

  # Add a vertex to the graph.  If the vertex has already been added
  # to the graph, it will not be added a second time.

  def addVertex (name)
    @vertices[name] ||= Array.new
  end

  # Add an edge to the graph.  The end vertices of the edge will
  # be added to the graph if necessary.
  # We should never connect two vertices with more than one edge.
 
  def addEdge( from, to)
    addVertex( from )
    addVertex( to )
    @vertices[from] << to
	@vertices[from].uniq!
  end

  # Is name the label of a vertex?
 
  def vertex? ( name )
    @vertices.key?(name)
  end
    
  # Is the graph empty?

  def empty?
    @vertices.empty?
  end
    
  # How many vertices do we have?

  def numVertices
    @vertices.size
  end

  # Is there a direct edge leading from one
  # vertex to another?

  def edge?( from, to )
    @vertices[from].include? to 
  end
  
  # How many outgoing edges are there for vertex name?
  # If name is unknown, then this is 0.
 
  def outDegree( name )
    if (@vertices.has_key? name)
		return @vertices[name].size
	else
		return 0
	end
  end
  
  # How many incoming edges are there for vertex name?
  # If name is unknown, then this is 0.
 
  def inDegree( name )
	
	count = 0
    if @vertices.has_key? name
	  @vertices.each  { |key,v|  count+=1 if self.edge?(key,name)   }
	 end
	return count
  end #def

end
