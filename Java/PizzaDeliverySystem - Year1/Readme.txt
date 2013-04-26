# Kocsen Chung
# Pizza Delivery System
# Date: SEP 2012 - NOV 2012

Overview:
		This Pizza Delivery System (PDS) is a simulator for a pizza restaurant.
		The system uses the Model-View-Controller architechture to handle the
		many functions it has. It is meant to be running on a touch screen
		station giving the user the power to :
										view current orders
										view past orders
										view statistics on orders
										create a new order
										edit orders
										more etc.

		The manager can also obtain clearance (default password = master) using
		a password. This is used to generate reports, shutdown the system and
		change the manager password.

		There are multiple databases that will keep the information on next
		startup. Serializable objects are NOT used, instead basic files are used
		The databases are:
			Customer
			Items
			Password
			Others

To run:
		# Make sure Java is installed
		# For more info find README in source code zip

		$ java -jar PDS_R2.jar

Documentation: 
		In the directory ./SourceFiles/ there are formal documents pertaining to 
		the project and its process with the source code itself.


Special Instructions:
		The password for the manager options is set to default to 'master'

