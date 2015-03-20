# FizzBuzz in Python
# by Marshall Ehlinger

def fizzBuzz(max):
	for i in range (1, max + 1):
		line = ""
		if (i % 3 == 0):
			line += 'fizz'
		if (i % 5 == 0):
			line += 'buzz'
		if (line == ""):
			line = str(i)
		print line

########
# TEST #
########

fizzBuzz(100)