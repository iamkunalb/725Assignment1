# 725Assignment1 - kbha192

# Simple File Transfer Protocol

Compiling/Running the project
* Open Eclipse
* Open project from file
* Open 725Ass1
* Right click "Test.java"
* Choose Run As > Java Application
* Enter desired commands for the SFTP as outlined in the documentation provided
* Send a "DONE" command when finished to stop all proccesses

NOTE: The command is the first 4 letters of the text you type, parameters are defined by adding a space.
E.g. "USER 1", the command is USER and the parameter is "1"

Test Case

USER

    "USER 1" is used to log in with user 1's credentials. The server then procceeds to ask for the user's username and password

ACCT

    "ACCT shawn" to specify the user wants to log in with shawn's credentials. The next step is to put in the password
     If the account doesnt exist, the system will reply saying so.

PASS

    "PASS spencer" to enter the corresponding password to the account being accessed 
    Incorrect passwords will generate a reply to say its incorrect.

TYPE

    "TYPE A" is to specify the file type as ASCII
    "TYPE B" is to specify the file type as BINARY
    "TYPE C" is to specify the file type as CONTINUOUS

CDIR

    "CDIR /newDirectory" will change the current directory to /newDirectory

LIST

    "LIST F" is to display the contents of the current directory

KILL

    "KILL file.doc" will delete the file.doc file

NAME

    "NAME file.doc" will rename choose the file to be renamed
    
TOBE

    "TOBE newFile.doc" changes the file specified above

DONE

    Closes the connection between server and client.
    Use at the end of the program

RETR

    "RETR sendFile.doc" will prepare the file dpecified to be sent to the server 
    
SEND

    "SEND" will send the file specified above

STOP

    "STOP" will stop the sending of the file

STOR

    "STOR NEW sendFile.doc" to specify a new file to be sent
    "STOR OLD" to specify an existing file to be overwritten
    "STOR APP" to specify an existing file to be appended to

SIZE

    "SIZE 123" tells the server that the file specifies in STOR is 123bytes
