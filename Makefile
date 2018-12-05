JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java

CLASSES = \
		  PhoneBookController.java \
		  PhoneBookModel.java \
		  PhoneBookView.java

default: classes

classes: $(CLASSES:.java=.class)

run:
	java  -classpath ".:sqlite-jdbc-3.23.1.jar" PhoneBookController

clean:
	$(RM) *.class