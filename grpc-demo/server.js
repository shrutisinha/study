const grpc = require('grpc');
const protoLoader = require('@grpc/proto-loader');
//compile proto into bunch of js files that has getters/setters for scehma (synchonously)
const packageDef = protoLoader.loadSync('todo.proto', {});//empty object:uses default
//load packageDef(toodo.proto) into grpc object
const grpcObject = grpc.loadPackageDefinition(packageDef);
const todoPackage = grpcObject.todoPackage;
//using this package, can get access to services

const server = new grpc.Server();
// listen to post
//by default it uses http2.. which by defaults needs certificate.. can bypass that by createInsecure
server.bind('0.0.0.0:40000', grpc.ServerCredentials.createInsecure()); //establishes plain text communication

server.addService(todoPackage.Todo.service,{
  'createTodo': createTodo,
  'readTodos': readTodos,
  'readTodoStream': readTodoStream,
});
server.start();
console.log("sss")
//grpc method takes 2 params: 
//call:lwhole call that is made: access to whole thing including tcp connectn
//and callback: used to send back response to client... sending info back to server
const todo = [];
function createTodo (call, callback) {
  //compressed binary format(reconstructed here)
  console.log(call);
  const item = {
    id: todo.length+1,
    text: call.request.text
  };
  todo.push(item);
  callback(null, item);
}

function readTodos (call, callback) {
  //1st param length of payload.. null means autocalculate
  callback(null, {items:todo});
}

function readTodoStream (call, callback) {
  //directly write to call
  todo.forEach(item=> call.write(item));
  call.end();
}