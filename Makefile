all:
	javac *.java

run:
	java  -classpath ".:sqlite-jdbc-3.23.1.jar" PhoneBookController

clean:
	rm *.class
