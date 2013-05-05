# Kocsen Chung
# Main GUI Class that uses TK to represent the program with a front end

require 'tk'

class GUI
	def initialize()
		root = TkRoot.new {title "Pixel People by Kocsen"}
		TkLabel.new(root) do 
			text "Kocsen Chung"
			pack {padx 15; pady 15; side 'left'}
		end
		Tk.mainloop
	end

end


