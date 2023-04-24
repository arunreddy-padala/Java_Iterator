# Java_Iterator
To design and implement look and say problem using the Iterator design pattern 

Langauges and Frameworks: Java, Junit4 

A look-and-say series is a number sequence. The next number is calculated by reading the current number out loud and writing down what we say.

A) The constructor takes two arguments: a starting seed and an end value. The seed is the number at which the sequence must begin. The iterator should not produce a number greater than the end value. If the seed is not positive, or is not less than the end, or has any zeroes in it, the constructor will throw an IllegalArgumentException.

B) The constructor that takes a starting seed as its only argument. The seed is the number at which the sequence must begin. The end value will be a number with 100 9s (the largest 100 digit number). If the seed is not positive, or is not less than the end, or has any zeroes in it, the constructor should throw an IllegalArgumentException.

C) A constructor that takes no arguments. This will start the sequence with a seed of 1. 

D) The method next() will return the current number in the sequence and advance to the next number. If there is no next value in the sequence, this method throws a NoSuchElementException.

E) The method prev() will return the previous number in the sequence, that is, the number coming before the last number returned by next(). If there is no previous value in the sequence, this method throws a NoSuchElementException.

F) The method hasNext() will return true if the next number to be returned is still lesser than end (specified in the constructors), false otherwise.

G) The method hasPrevious() will return true if it is possible to go back one step, false otherwise. 

H) Implemented test cases to test edge cases, and functionality of the code as well. 
