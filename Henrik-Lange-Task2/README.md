Task 2

Besitzt 2 Laufzeitvariablen: 

* -td topDown
* -ovm oneVsM

Funktion:

* oneVsM: Bei True werden alle moeglichen Eintraege Kontrolliert, bei False nur das erste. 
* topDown: Bei True wird die Datenbank von oben nach unten durchgegangen, bei False werden zufaellig Stellen in der Datenbank gesucht

Test: 

oneVsM: True
topDown: False
Die Eintraege werden zufaellig ausgewaehlt und es werden mehrere Antworten akzeptiert

oneVsM: False
topDown: True
Die Eintraege werden von oben nach unten ausgewaehlt und es wird nur die erste Antwort akzeptiert

----------------------------------------------------------------
Das Programm besitzt schon mehrer Feature:
* Training data
* Import
* Export
* Flashcards
* Cloze test
* Multiply choice
* Compare 1vs1
* Compare 1vsm
* Top down choice of question
* (random) not in the diagramm
* theme filter
* (edit funktion) not in the diagram
 
Für die wurde aber keine Laufzeitvariable eingesetzt, aber sie sind soweit gekapselt das es möglich ist dies zu ergänzen. 

