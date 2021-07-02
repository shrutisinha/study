const dgram = require('dgram');
// udp4 for ipv4 and upd6 for ipv6
const socket = dgram.createSocket('udp4');

socket.on('message', (msg, rinfo) => {
  //on recieving message
  console.log(`server got: ${msg} from ${rinfo.address}:${rinfo.port}`);
});

// listens on 8081
socket.bind(8081);

//listening but no concept of connection
// echo "foo" | nc -w1 -u 127.0.0.1 8081
// send msg "foo" to post 8081 upd, -w1 means timeout of 1s

// Response
// server got: hi from 127.0.0.1:52458
// 52458 is internal ip:port of client