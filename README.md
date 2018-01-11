# Document Similarity API
## A Java API for Measuring Document Similarity
This aim of this program was to create a Java API that can rapidly compare two large text files by
computing their Jaccard Index.

## To run the project
1.	Navigate to the directory with oop.jar in it.
2.	Type in the command prompt:
```
 java �cp ./oop.jar ie.gmit.sw.Runner
``` 

3.	Once the program is running the user will be prompted with a menu.
4.	Input the full path name of both files and their extensions.
5.	Enter the amount of shingles you would like to use. (A shingle determines how accurate you would like it to be, the lower the shingle size the more comparisons will be tested. The API will rapidly compute the jaccard index and display the results in the console.)
 
## Updates
*   Package all the files within packages that describe their task 
*   Re-model the program using JOptionPane dialog boxes.
*	Automatically check if the file name entered has a .txt extension.
*	Create an interface for the parser so other forms of documents could be parsed.

## Link to GitHub
https://github.com/conormc93/DocumentSimilarityAPI.git