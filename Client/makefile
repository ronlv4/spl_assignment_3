CFLAGS:=-c -Wall -Weffc++ -g -std=c++11 -Iinclude
LDFLAGS:=-lboost_system -lpthread

all: EchoClient
	g++ -o bin/BGSclient bin/connectionHandler.o bin/echoClient.o bin/readFromKeyboard.o bin/encoderDecoder.o $(LDFLAGS)

EchoClient: bin/connectionHandler.o bin/echoClient.o bin/readFromKeyboard.o bin/encoderDecoder.o

bin/connectionHandler.o: src/connectionHandler.cpp
	g++ $(CFLAGS) -o bin/connectionHandler.o src/connectionHandler.cpp

bin/echoClient.o: src/echoClient.cpp
	g++ $(CFLAGS) -o bin/echoClient.o src/echoClient.cpp

bin/readFromKeyboard.o: src/readFromKeyboard.cpp
	g++ $(CFLAGS) -o bin/readFromKeyboard.o src/readFromKeyboard.cpp

bin/encoderDecoder.o: src/encoderDecoder.cpp
	g++ $(CFLAGS) -o bin/encoderDecoder.o src/encoderDecoder.cpp

.PHONY: clean
clean:
	rm -f bin/*
