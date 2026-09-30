\# Java Chat Application



A simple multi-client chat application developed in Java using \*\*Socket Programming\*\* and \*\*Multithreading\*\*.



The application allows multiple clients to connect to a central server and exchange messages in real time.



\## Features



\* Multi-client chat support

\* Client-server architecture

\* Java Socket Programming

\* Multithreading for handling multiple clients

\* Real-time message broadcasting

\* Chat history stored in a text file

\* Configurable server port using `config.properties`



\## Technologies Used



\* Java

\* Java Sockets

\* Multithreading

\* File Handling

\* Java Collections

\* PowerShell / Command Prompt



\## Project Structure



```text

Java-Chat-Application/

│

├── Server.java

├── Client.java

├── config.properties

├── chat\_history.txt

├── .gitignore

└── README.md

```



\## How It Works



The application follows a client-server model.



1\. The \*\*Server\*\* starts and listens on a configured port.

2\. A \*\*Client\*\* connects to the server.

3\. The server creates a separate `ClientHandler` thread for each connected client.

4\. When a client sends a message, the server receives it.

5\. The server broadcasts the message to the other connected clients.

6\. Messages are also stored in `chat\_history.txt`.



\## Configuration



The server port is configured using `config.properties`.



Example:



```properties

port=5000

```



\## How to Run



\### 1. Compile the program



Open a terminal inside the project directory and run:



```bash

javac Server.java Client.java

```



\### 2. Start the server



```bash

java Server

```



You should see:



```text

Server started on port 5000

```



\### 3. Start the client



Open another terminal in the same project directory and run:



```bash

java Client

```



Multiple client terminals can be opened to test communication between different clients.



\## Example



```text

Server started on port 5000



New client connected: 127.0.0.1

New client connected: 127.0.0.1



Received: Hello everyone!

Received: Hi!

```



\## Learning Outcomes



This project helped demonstrate:



\* TCP socket communication

\* Client-server architecture

\* Java networking

\* Multithreading

\* Concurrent client handling

\* File handling

\* Basic configuration management



\## Future Improvements



Possible improvements include:



\* Graphical User Interface using Java Swing or JavaFX

\* Usernames and authentication

\* Private messaging

\* Online-user list

\* Better chat-history management

\* Database integration

\* Message timestamps

\* Improved error handling



\## Author



\*\*Namita Dave\*\*



Computer Engineering Student



