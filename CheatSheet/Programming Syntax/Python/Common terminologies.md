
### The building blocks of programs

**input**
Get data from the “outside world”. This might be reading data from a file, or even some kind of sensor like a microphone or GPS. In our initial programs, our input will come from the user typing data on the keyboard.

**output**
Display the results of the program on a screen or store them in a file or perhaps write them to a device like a speaker to play music or speak text. 

**sequential execution**
Perform statements one after another in the order they are encountered in the script. 

**conditional execution**
Check for certain conditions and then execute or skip a sequence of statements. 

**repeated execution**
Perform some set of statements repeatedly, usually with some variation. 

**reuse**
Write a set of instructions once and give them a name and then reuse those instructions as needed throughout your program


### Common Errors

**Syntax errors**
These are the first errors you will make and the easiest to fix. A syntax error means that you have violated the “grammar” rules of Python. Python does its best to point right at the line and character where it noticed it was confused. The only tricky bit of syntax errors is that sometimes the mistake that needs fixing is actually earlier in the program than where Python noticed it was confused. So the line and character that Python indicates in a syntax error may just be a starting point for your investigation.

**Logic errors**
A logic error is when your program has good syntax but there is a mistake in the order of the statements or perhaps a mistake in how the statements relate to one another. A good example of a logic error might be, “take a drink from your water bottle, put it in your backpack, walk to the library, and then put the top back on the bottle.” 

**Semantic errors**
A semantic error is when your description of the steps to take is syntactically perfect and in the right order, but there is simply a mistake in the program. The program is perfectly correct but it does not do what you intended for it to do. A simple example would be if you were giving a person directions to a restaurant and said, “…when you reach the intersection with the gas station, turn left and go one mile and the restaurant is a red building on your left.” Your friend is very late and calls you to tell you that they are on a farm and walking around behind a barn, with no sign of a restaurant. Then you say “did you turn left or right at the gas station?” and they say, “I followed your directions perfectly, I have them written down, it says turn left and go one mile at the gas station.” Then you say, “I am very sorry, because while my instructions were syntactically correct, they sadly contained a small but undetected semantic error.”.


### Debugging

**reading**
Examine your code, read it back to yourself, and check that it says what you meant to say. 

**running**
Experiment by making changes and running different versions. Often if you display the right thing at the right place in the program, the problem becomes obvious, but sometimes you have to spend some time to build scaffolding. 

**ruminating**
Take some time to think! What kind of error is it: syntax, runtime, semantic? What information can you get from the error messages, or from the output of the program? What kind of error could cause the problem you’re seeing? What did you change last, before the problem appeared? 

**retreating**
At some point, the best thing to do is back off, undoing recent changes, until you get back to a program that works and that you understand. Then you can start rebuilding. 