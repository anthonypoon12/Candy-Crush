javac info/gridworld/grid/*.java
javac info/gridworld/world/*.java
javac info/gridworld/gui/*.java
javac info/gridworld/actor/*.java
javac -cp ".;./gridworld.jar" CandyCrushRunner.java
java -cp ".;./gridworld.jar" CandyCrushRunner
