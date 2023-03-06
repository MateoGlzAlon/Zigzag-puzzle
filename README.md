# Zigzag-puzzle
Final project of the subject "Programacion II" coursed in Universidad de Leon


The zigzag puzzle consists of a matrix of numbers between 1 and 9, and the goal is to find a path in the matrix starting from the top left corner, and ending at the bottom right corner, so as to pass through all the numbers in the matrix in circular ascending order (the largest number will be followed by the smallest) only once and without the path crossing. Horizontal, vertical and diagonal moves of length 1 are allowed, i.e. always to contiguous matrix positions. A move must always go to a different number.


The output to be produced by the program is the number of possible and distinct solutions to the puzzle and then each of the given solutions in lexicographic order with respect to the coordinates of the matrix positions that the path is visiting. The positions of the matrix are identified in the usual way by row-column starting in the upper left corner. Recall that the lexicographic order for pairs of values is defined as follows: open parentheses x subscript 1 comma and subscript 1 close parentheses less than open parentheses x subscript 2 comma and subscript 2 close parentheses if x subscript 1 less than x subscript 2 or x subscript 1 equals x subscript 2 and y subscript 1 less than y subscript 2. Given two sequences of pairs of values s equal left parentheses s subscript 1 comma space s subscript 2 comma space. .. comma space s subscript n right parenthesis and v equal left parenthesis v subscript 1 comma space v subscript 2 comma space... comma space v subscript n right parenthesis , we will have that s space less than v space in lexicographic order if there exists i with 1 less than or equal to i less than or equal to n such that s subscript k equals v subscript k for all k less than i spaceand s subscript i less than v subscript i.

The input shall consist of a single matrix given by rows and with a number of rows or columns less than or equal to 10. Each row of the matrix shall be on a separate line and each row number shall be separated by a single blank from the next. There shall be no blank either at the beginning of the row or after the last number.

The output will contain in the first line the number of existing solutions to the puzzle and then each solution will be given separated from each other by a blank line (the last solution will not be followed by a blank line) and arranged in lexicographical order with respect to the coordinates of the numbers in the path.



Each solution will be given by the input matrix but separating each row of the matrix by an additional line containing as many characters as each row. Five characters will be used between the matrix positions to indicate the solution path. These characters will be either the white character or one of the characters '-', '/', '\', '|'. The white character will indicate that two positions are not in a row in the solution path; the dash character will be used to show two numbers in a row in the path that are in the same row of the matrix; the vertical line character '|' will be used to show numbers in a row in the path that are in the same column but in different rows; the backslash character '/' will be used to show consecutive numbers that being in different rows are either in a subsequent column if up or in a previous column if down; finally the backslash character '/' is the opposite of the previous one.

To better see how each of the solution matrices should be formed it is convenient to put the characters in a table. For example the first solution in the example below is placed in a grid as follows:

1   3 - 1
|  /  /	
2   2   2
  /   / |
3 - 1   3
keeping in mind that the empty cells must have a blank space.

If there is no solution to the puzzle given in the input, the output will simply be a 0 on a single line.

If the input to the program is syntactically incorrect the output will be the string "Entrada incorrecta." (Incorrect input)
