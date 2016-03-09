# TechChallenges

Repo for my code to solve various coding challenges.

Problem#1
Boatman Bridge Problem
You may have come across an old but fun riddle concerning a boatman who has to transport a tiger, goat and a bale of grass across a river.  In this puzzle, one can represent the affinity of the three main items to be transported as follows:

Item List:
[Tiger, Goat, Grass]

The "x" represents the item pair that have strong affinity, and hence shouldn't be left alone together.
 
Affinity Matrix (based on the above item list order):
[0,x,0]
[x,0,x]
[0,x,0]

Write a program to transport any 3 items across the river, based on a given affinity matrix.The input parameters for the riddle are mentioned above and expected output are mentioned below.

Output (The trips the boatman makes from one shore to the other)

Goat (from shore A to B)
Empty (from shore B to A)
Tiger (from shore A to B)
Goat (from shore B to A)
Grass (from shore A to B)
Empty (from shore B to A)
Goat (from shore A to B) --- Done

Advanced Challenge:

Generalize the program. The program should accept any "nXn" affinity matrix as well as the number of items that can be carried across the river in one trip ("m"). Based on these, the program should output the journey. 

Solution:-
BoatmanBridgeSolution.java