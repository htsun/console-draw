This program is built with java 8; so it runs only with java 8 JVM
It is a Console application, prompting user for various commands (as described in the spec)
Note that x and y co-ordinates are 1-based; x is across, y is downwards

Here is how to run the program

    cd <current directory when the project is>
    java -jar target/springer-test.jar

Finish (gracefully) by typing Q when prompted

Every command is evaluated relative to the 'latest' C (create canvas) command, and display the cumulative outcome

I use Apache common libraries for commonly used helper functions
[however, they are only being used in test; otherwise I have to build a uber jar ...]

Filling a large canvas (200 x 200) takes some time though ...