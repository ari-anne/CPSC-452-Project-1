JFLAGS = -g
JC = javac

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	CipherInterface.java \
	Caesar.java \
	Playfair.java \
	RowTransposition.java \
	Vigenere.java \
	cipher.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
