README FILE

Pizza Delivery System by 
Team A

~NOV 2012~V2.0.0

CONTENTS
I.	MINIMUM SYSTEM REQUIREMENTS
II.	HOW TO INSTALL THE PROGRAM
III.	TROUBLESHOOTING
IV.	TECHNICAL SUPPORT
	-Quick note: initial password on system startup is: master




I. MINIMUM SYSTEM REQUIREMENTS

ALL require latest java
available for download at: http://www.java.com/en/download/index.jsp

Windows
¥ Windows: 98, 2000, XP, VISTA, 7, 8
¥ Pentium 366 MHz or higher 
¥ Minimum 64 MB of RAM; Recommended 256 MB of RAM
¥ Screen resolution 800x600 or higher
¥ Internet Explorer 5.5, 6.0, Netscape 7.0, Opera, Firefox, Chrome


Macintosh
¥ Mac 9.2, 10.2, 10.2 or higher
¥ G3 or higher Macintosh required
¥ Minimum 64 MB available RAM 
¥ Internet Explorer 5.2, Netscape 7.0, Safari 1.2, 

Linux
¥ Any Distro
¥ Minimum 64 MB of RAM; Recommmended 256 MB of RAM



II. HOW TO INSTALL THE PRODUCT

A. The project should have been a zipped file. Extract all the files
	together at a specific destination (ie. C:\Users\User\Documents\)
	Using Winrar, 7zip, etc. 
B. Double clicking on the product should automatically launch the window
	and allow proper function.etc.o
C. The default manager password is 'master' without quotes.

NOTE: There are advanced system features for advanced users only.
These features allow a faster simulation or a launch of the PDS
in command line mode (CLI). The arguments must include the mode of running
(either CLI or GUI mode and then the other arguments
The arguments include:

-cli		 : to run in CLI mode
-gui 		 : to run in GUI mode
++LOADING THE FOLLOWING PARAMETERS++:
-i <filename> 	 : loads a specific ITEM file filename
-o <filename>	 : loads a specific ORDER file filename  
-c <filename>	 : loads a specific CUSTOMER file filename
-t <##>		 : sets simulation speed; where 1 unit of time = ## milliseconds.

If you wish to run the progarm with advanced system features in the
command line follow the folling instructions (C., and D.):

C.Windows should have been a zipped file. Extract all the files
        together at a specific destination (ie. C:\Users\User\Documents\)
        Using Winrar, 7zip, etc.
 - Running the .jar file:
1.  Launch the command line - Click on Start > Run > Type in "cmd" > Hit enter
2.  A black screen apears waiting for input.
3.  Using the command 'cd [directory]' , navigate through your folders to 
	get to where the files were extracted.
	(Hint: use command 'ls' to display all available files/folders)
	To go back in a directory type 'cd ..'.
4.  Once your in the correct directory/path, type in the follwoing:
	'java -jar PDS_R2.jar'  >  Hit enter.
5.  The program should be executed.
	Note: .dat files will be created. IF deleted, a new session
	will start from scratch instead of holding saved data.

D. Mac/Linux - Running the .jar file:
1.  Launch your Terminal.
2.  Navigate through your directories using 'cd [directoryname]' until
	you get to the directory where all the files were extracted.
3.  Once your in the correct directory/path, type in the follwoing:
	'java -jar GroupA_PDS.jar'  >  Hit enter.
4.  The program should be executed.
	Note: .dat files will be created. IF deleted, a new session
	will start from scratch instead of holding saved data.



III. TROUBLESHOOTING
1. Make sure files are EXTRACTED using a archive manager such as Winrar, 7zip, etc.
	Download here: http://www.7-zip.org/download.html
2. Make sure JAVA is installed
	Download here: http://www.java.com/en/download/index.jsp
3. The default manager password is 'master' without quotes.
	This can be changed from the manager options.
4. To reset the store, delete all .dat files in the PDS jar's directory.
3. Contact Technical Support @ SECTION IV.




IV. TECHNICAL SUPPORT

PROGRAM DOES NOT LAUNCH:
	If the program does not launch, it is probably because a file is 
	corrupt. If there is an experienced technitian, they should go 
	over the .dat files with a text editor and try and fix the issue.
	If this does not work, you must do the following:
	CAUTION: THIS WILL DELETE ALL DATA
	Try going into the source path and delete all the files that 
	end in .dat. (ie customer.dat).
	Try and relaunch the program.
	If everything fails, contact Customer Support (below).



LOST PASSWORD:
	If your password has been forgotten or lost, it is important to call a 
	supervisor to handle this. The supervisor must either (1) delete the 
	password.dat file, the password will reset to 'master'.
	Or (2), the password.dat file should be accessed and changed to 
	a new desired password

If you need technical assistance, you may contact our technical support department in 
the following ways:
1. Email: kxc4519@rit.edu or acc3863@rit.edu or aaa4939@rit.edu or cpk4877@rit.edu
2. Call 585-754-3885 Mon - Fri 8:00 am to 8:00 pm Eastern

Copyright © 2012 GroupA361, Inc. 
