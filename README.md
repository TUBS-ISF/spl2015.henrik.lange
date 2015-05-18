# spl2015.henrik.lange
Task1: Creating a feature-diagram

Task2: Program with some features, with 2 changeable runtime variables:

  Features:
  
    -Training data
    -Import
    -Export
    -Flashcards
    -Cloze test
    -Multiply choice
    -Compare 1vs1
    -Compare 1vsm
    -Top down choice of question
    -(random) not in the diagramm
    
  Runtime variables :
  
    -topDown, if False the question will be choosen randomly
    -oneVsM, if True every possible answer will be checked, if False only the first
      
    Test protocol:  "Henrik-Lange-Task2 / Kombinationstest.txt"
      
  Work in progress:
  
    -implementation of profiles
    -better visualisation from data of different themes (maybe)
    -the possibility to chose themes, but first the profile. The reason for this is to know which
    implementation is more clever.
  
  Problem:
  
    -with the rise of the complexity, i need to implement better tools to organize all the data.
  
  Idea:
  
    -to implement something, so that the menus are more flexible, so if something is not selected, 
    that the counting is still correct.
    
  
