const grpc = require('grpc');
const protoLoader = require('@grpc/proto-loader');
const packageDef = protoLoader.loadSync('todo.proto', {});
const grpcObject = grpc.loadPackageDefinition(packageDef);
const todoPackage = grpcObject.todoPackage;

//client needs to connect
const client = new todoPackage.Todo('0.0.0.0:40000', grpc.credentials.createInsecure());

const text = process.argv[2];
console.log(text)

client.createTodo({
  "id": -1,
  "text": text
}, (err, response) => {
  console.log("Recieved from server " + JSON.stringify(response));
  console.log("Recieved error from server " + JSON.stringify(err));
});

client.readTodos({}, (err, response) => {
  console.log("Recieved from server " + JSON.stringify(response));
  console.log("Recieved error from server " + JSON.stringify(err));
});


const call = client.readTodoStream({});

call.on("data",(item)=>console.log(JSON.stringify(item)));
call.on("end",()=>console.log("END"));