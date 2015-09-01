# Fizzbuzz in Ruby

for i in 0..100 
	substituted = false
	if i % 3 == 0
		print("fizz")
		substituted = true
	end
	if i % 5 == 0
		print("buzz")
		substituted = true
	end
	if substituted == false 
		print(i)
	end
	print("\n")
end
	
