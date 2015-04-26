# About #

Basic implementation of an AI that can play the pebble game for MSOE classes CS4881 and CS3851 final projects.

# The Pebble Game #

The origional game was named "GATTA TIPNE KHEL" (meaning pebble picking game). In this pebble picking game a pile of some pebbles is kept in the ground. One of the two players picks one, two or three pebbles up at a time in their turn, leaving the pile for the other player to pick for their turn. In this alternate picking process, the player who picks the last pebble(s) will be the loser.

The main logic of the game is to leave the pile of pebbles with 13, 9, 5 or 1 pebble(s) for the opponent to pick. In the program the starting number of pebbles are set to 17, 21, 25, 29 … etc. so that computer could win always if it does not make a mistake. But in the real play computer seems to be gradually learning by correcting mistakes of the previously played games. At last it finds all its mistakes and corrects them to become an unbeatable champion.

It seems computer simulates the psychological learning process of animal, learning by correcting and not repeating the mistakes. A multidimensional array of elements (1..4,1..3) is chosen as the instruction book for the computer to pick the pebbles. The instruction book contains four pages with three lines of instructions to pick pebbles. The first line instructs to pick a single pebble, the second line instructs to pick 2 and the third line instructs to pick 3 pebbles. At the beginning, computer chooses a random page and a random line of instruction to pick the pebble.