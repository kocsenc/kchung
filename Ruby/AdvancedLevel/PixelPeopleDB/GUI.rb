# Kocsen Chung
# Main GUI Class that uses TK to represent the program with a front end

require 'tk'
require './fileio.rb'
require './DB.rb'

class GUI
	def initialize(db)
		@db = db
		@root = TkRoot.new {
			title "Pixel People by Kocsen"
			minsize(800,600)
		}
		init_components()
		Tk.mainloop
	end

	def init_components()
		@canMakeList = nil
		@scrollBar = nil

		makeCanMakeList(@canMakeList, @scrollBar)
		

	end


	#Method for the canMakeList component
	def makeCanMakeList(canMakeList, scrollBar)
		canMakeList = TkListbox.new(@root){
			yscroll proc{|*idx|
				scrollBar.set(*idx)
			}
			selectmode 'multiple'
			pack("side" => "left", "fill" => "y", "expand"=> 1 )
		}

		scrollBar = TkScrollbar.new() {
			command proc{|*idx|
				canMakeList.yview(*idx)
  			}
  			pack('side' => 'left', 'fill' => 'y', 'expand' => 1)
		}
		canMakeList.value=(@db.getAttainedProfessions)

	end

end

def main()
	puts("Booting System")
	puts("Loading Database...")
	db = parse()
	puts("Loaded Database")
	puts("Launching GUI")
	GUI.new(db)
end

main()

