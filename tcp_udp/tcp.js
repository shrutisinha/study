const net = require("net")

const server = net.createServer(socket => {
  //writes hello on client when it connects (once)
    socket.write("Hello.")
    socket.on("data", data => {
        //when clients sends data
        console.log(data.toString())
    })
})

//listening to port 8080
server.listen(8080);

//sample connect
//telnet 127.0.0.1 8080

