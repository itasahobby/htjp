# HTJP Server
Server that offers an [HTJP](https://tools.ietf.org/html/rfc8565) service. You can edit the java proyect to control how the server processes the requests.
> Note that according to the protocol shouldn't respond to bad requests. However this implementation responds for debugging purposes.

## Usage
1. Run the jar file with `java -jar htjp.jar -server 127.0.0.1 9999` to open the server on localhost and port 9999. 
2. Use netcat to emulate a client request. In the source code there is an example of a working request: `nc 127.0.0.1 9999 < htjp/src/sampleRequest.raw`. 
