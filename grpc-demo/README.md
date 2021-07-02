https://www.youtube.com/watch?v=Yw4rkaTc0f8

gRPC (gRPC Remote Procedure Calls) is an open source remote procedure call (RPC) system initially developed at Google in 2015. It uses HTTP/2 for transport, Protocol Buffers as the message format. 

Motivation
1. Client server communication: 

soap(had rigid schema that client and server had to agree.. and bloated xml), rest(no schema), graphql(solves some problems with rest.. reduced reqs)

above 3 didnt solve bidirectional communication.. server sending info
sse(server sent events to client), websockets (bunch of bytes.. no format)

Databases invented own protocol
Raw TCP

2. Problem with client libraries(hard to maintain): client and server has to agree... browser is biggest http client library (http: manages everything)
if not in browser, we are responsible for client library

3. why gRPC
  unified client library (one for each lang)
  hidden http2 protocol
  message format protocol buffers (language angostic): builds/compiles msgs in binary format

Modes
1. Unary RPC(req res): client makes req->synchronously waits for response->constructs response->returns
2. Server streaming RPC(1 req, multiple res): client reqs -> expects a continuous stream of response... like websockets
3. Client streaming RPC: client sends continuous streams of data
4. Bidirectional streaming RPC: chat

communicated with unary protocol and stream format

Pros:
1. fast and compact: data sent in binary format which is the slimest format(not json or others), https2 compresses payload even more. (can reduce round trips: there is limit in tcp of data that can travel once: mtu: maximum transmission unit)

2. one client library (one per language): managed by google/open src community (rest has many client libraries.. the diversification/options makes it hard to manage)

3. progress feedback (like progress bar for upload): server can send progress like with streams

4. HTTP2 allows to cancel requests: http tags every req with stream id... using this id.. req can be cancelled (hook provided by http2)

5. http2 + protobuf benefits



Cons:
1. Forcing schema/proto file (not needed in rest) (GRPC can work with json so this is not much relevent)

2. thick client: every time protoc file runs and compiles ?? (check) 
some langs might not be supported

4. proxies: most dont understand grpc (nginx knows).. can use upto layer 4 proxy.. layer 7 not possible

5. still young: buggy

6. error handling: doesnt use raw http code.. have to build own error handling

7. no browser support.. hacks are present... debugging difficult

8. timeouts: clients timeout problems.. circuit calls have to be managed