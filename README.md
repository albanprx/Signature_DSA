# Context

This project is for educational purposes only. It is not intended for use in real-world cryptographic applications. Do not use this implementation for securing sensitive data.

# Command to Launch the Program

## Compilation

To compile the program we can use this command:

> 'javac -d out src/*.java'

## Signature

Then, to make a signature, we will be able to launch this one:

> 'java -cp out DSA ./tests/alice.txt 1 s'

This will then make a signature against the contents of the file in ./tests/alice.txt and save it in a file named "sign1.txt" if the file already existed then it will create another one and index it in the order. The number of signatures can be increased by modifying the number in front of the "s" parameter. Furthermore, only the last signature will be displayed in the terminal and it is the latter which will be written to the output file.

## Verification

Finally, to perform a check, we will use the following command:

> 'java -cp out DSA ./tests/alice.txt 1 v ./tests/sign.txt'

It is of course necessary to specify an additional file which will contain in order the public key of the person who carried out the signature, as well as the value of r and s, all separated by signatures. The program returns the time again and here it will tell us whether the check(s) passed or failed
