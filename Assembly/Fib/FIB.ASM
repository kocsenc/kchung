# Name: Kocsen Chung
# File: FIB.ASM
# **************** pseudo code ************** #
# inputN = .word N //Fib number
# answer = .word 0 //result to return
#
# fib1 = 0 //Fib(n)
# fib2 = 1 //Fib(n+1)
# fibtemp = 0
# n = 0 //counter
# 
# loop while n < inputN
#	fib1 = answer
# 	fibtemp = (fib1 + fib2)
#	answer = fibtemp
#	fib2 = fib1
# 	n++
# end loop
#
# return answer


.data
		n: .word 7
		ans: .word 0

.text 						# Declaring variables
		addi	$1, $0, 0		# Fib(n) =   0
		addi	$2, $0, 1		# Fib(n+1) = 1
		addi	$3, $0, 0		# Temp addition for Fib(n) + Fib(n+1)
		addi	$4, $0, 0		# Count = 0
		lw		$5, n($0)		# Fib Target Number N
		beq		$5, $0, finish	# If N == 0, return 0

loop:	lw 		$1, ans($0)		# load answer -> to fib(n) 
		add 	$3, $2, $1		# Add Fibs, store in $3 (temp)
		sw		$3, ans($0)		# Put the answer in $3 on ans
		addi 	$2 ,$1, 0		# Store whats in fib(n) to fib (n+1)
		addi 	$4, $4, 1		# Count++
		bne		$4, $5, loop
		j		finish			# Jump to finish (end)

finish:	jr $31
