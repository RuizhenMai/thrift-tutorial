# What is this repo for 
This is a repo demonstrating the usage of Apache Thrift to generate common interface across languages and we can utilize these interface to build microservice server in Java and directly invoke the service in Python client. 

In addition, I introduce a proxy pattern in `thrift-tutorial-client/Client.py`. The dynamic proxy pattern hides the remote calling detail, openning and closing the connection automatically. In this way one doesn't need to worry about the implementation details of making a connection to a remote server and etc. 

# Why we needs rpc
Another mainstream API deisgn is RestAPI that is based on HTTP. RPC's (or rpc's framework's) advantage is that it 
1. Reduces server programmers' necessity of writing another controller layer; with rpc frameworks, it also reduces the load that clients' need to extract necessary information from returned JSON
2. Allows clients to freely choose transport protocols other than HTTP, and serialization method other than JSON 

# How to Run 
## Have JDK, Maven and Thrift installed
I assume you have Jdk and Maven installed, to install Apache Thrift, I suggest you use 
```
brew install thrift
```
to avoid building their files from source 

## Generate the thrift interface
On the project directory, run
```bash
# generate python client dependencies
thrift -r --gen py -o thrift-tutorial-client common/thrift_tutorial_common.thrift 
# generate java server dependencies
thrift -r --gen java -out thrift-tutorial-server/src/main/java common/thrift_tutorial_common.thrift
```

## Run Server and Client
Run 
```bash
cd thrift-tutorial-server
mvn compile
mvn exec:java
```
to compile and run the Java Server class. Then run the Python client to send request by
```bash
cd ../thrift-tutorial-client
python Client.py
```


# Without Framework
codes without Apache Thrift are stored in the `no-framework-codes` directory. It contains solely Java code on both server and client side. It demonstrates in more details why we need a `Stub` class to hide the connection detail and the usage of dynamic proxy pattern. 

# Reference 
Codes in `no-framework-codes/` are thanked to https://www.bilibili.com/video/BV17Z4y1s7cG?p=2&share_source=copy_web
