# Lab6-GradeCalculator
A grading calculator for CISC3130

How to use
Users will be instructed to fill in there grades for the CISC 3130 class
They will first insert all of there grades for Labs, Practice Problems, and last Test
Labs and Practice problems only accepts integer as you can't get decimal points for these assignments
Test accepts all real numbers
When filling in the grades, enter nothing if the work has not been graded yet.
After all data has been filled in the program will show a chart of all your grades adding -1 for ungraded work.
The user will then be prompted to for an input but only the first letter will be need for all inputs
  add grades: Will Prompt the user for all of the current grades again, removing perivous data and reprinting grade chart
  
  edit a grade: Will edit one assignment and give it a new value
    edit will prompt the user for the type of assignment(Lab,Practice Problem, and test ), 
      will re request input if not one of the three stated assignments
    then Prompt for the assignment number, will end action if number entered is not a viable assignment number
    There are 7 Labs, 8Practice Problems and 3 tests
    
  show grades: Will print the users grades in a chart again
  
  calculate current grade: will print the user's combine grade ignoring ungraded assignment
  
  wanted letter grade: will calculate the worked need to reach a certain letter grade
    prompting the user for a letter grade and will reprompt if a viable one is not given.
    all Letter grades are represented as their lowest possible number grade A:90, B:80, C:70, D:60, F:0
     if f is pick it will end action
    Will print message if grade is impossible to reach or has been reached
    When calculating needed points on all ungraded assignments 
      it will start with labs and practice problems first and will round them to their nearest full point
        these are done first as they are the easier points to obtain in the class
      will then calculate grade need on each test
      Lastly it will print out needed grades on ungraded assignment,
        but only the minimum amount of assignment need fir wanted grade  
