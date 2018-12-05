JC = javac
JAVA = java
SQLITE_JDBC = ".:sqlite-jdbc-3.23.1.jar"
.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java

CLASSES = \
		  PhoneBookController.java \
		  PhoneBookModel.java \
		  PhoneBookView.java

default: classes

classes: $(CLASSES:.java=.class)

run: classes
	$(JAVA)  -classpath $(SQLITE_JDBC) PhoneBookController

clean:
	$(RM) *.class