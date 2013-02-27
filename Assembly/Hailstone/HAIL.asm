# Name: Kocsen Chung
# File: HAIL.ASM2
# **************** pseudo code ************** #

# n = input
# ans = array (allocate 60)

# arrayCounter = 0
# counter2 = 0
# x = n
# const5 = 1
# const6 = 6
# const7 = 2
# const8 = 60

# //Making Array
# while (arrayCounter < 60Bytes)
# 	insert 0 @ array[arrayCounter]
# 	arrayCounter += 4
# end loop
# 
# while ( n != 1 )
# 	counter2 += 4
# 	Divide (n/const7) // n/2 to see if even or odd
# 	store low value	  //Quotient
# 	store high value //Remainder
#	If its odd, break and go to odd part
# 	else, keep looping with n = division (low value)
# end loop 
#
#odd () 
#	n = 3n+1
#	go to loop with new n
#


.data
			n:		.word 11    	#Counter
			ans:	.alloc 60		# Allocate 60B, for 15 spaces of 4B each

.text
			addi $1, $0, 0 			# Counter for loop1
			addi $2, $0, 0 			# Counter for loop2
			lw $3, n($0) 			# Load n to $3
			addi $5, $0, 1 			# N = 1
			addi $6, $0, 6 			# O = 6
			addi $7, $0, 2			# P = 7
			addi $8, $0, 60

arrloop: 	sw $0, ans($1)			# store 0 at array[counter]
			addi $1, $1, 4 			# counter+=4
			beq $1, $8, loop		# break if size contains 60B, 15 spaces of 4 bytes
			j arrloop

loop:		sw $3, ans($2)			# store value in $3 to ans[counter2]
			addi $2, $2, 4 			# counter2 = counter2 + 4
			div $3, $7				# Divide n/2
			mflo $3 				# Lo -> $3
			mfhi $4					# Hi -> $4
			beq $4, $5, odd			# if odd, go to odd
			bne $3, $5, loop 		# else, keep looping
			j end

odd:		mult $3, $6 			# Does n = 3n+1
			mflo $3
			addi $3, $3, 4
			j    loop 				# Jump to loop subrout

end:		sw $5, ans($2) 			# append 1 to array (always the case)
			jr $31					# return;exit





