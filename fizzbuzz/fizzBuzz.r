# FizzBuzz in R
# By Marshall Ehlinger

fizzBuzz <- function(max) {
  for (i in 1:max) {
    line <- "";
    if (i %% 3 == 0) {
      line <- paste(line, "fizz", sep ="");
    }
    if (i %% 5 == 0) {
      line <- paste(line, "buzz", sep ="");
    }
    if (line == "") {
      line <- i;
    }
    print(toString(line));
  }
}

# TEST
fizzBuzz(100);

