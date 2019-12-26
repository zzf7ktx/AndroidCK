require('dotenv').config();

var express = require("express");
var app     = express();
var server  = require("http").createServer(app);
var io      = require("socket.io").listen(server);
var fs      = require("fs");

var db      = require("./db-utils");

server.listen(process.env.PORT || 3000);

app.get('/', (req, res)=>{
  res.send("con heo con");
})


// io.sockets.on('connection', function(socket){
//   console.log("function connect user");
// });

// listen connection-event
// id != 1
io.sockets.on('connection', (socket) => {
  let _id;
  console.log("somebody connect");

  socket.on('join-room', (id_user)=>{
    _id = id_user; 
     socket.join(_id);
     console.log("Chao mung id: "+ _id +" da ket noi.");
  })


  // listen send-message-event
  // input: _id, _mess, _send
  socket.on('send-message-event', async (_mess, _send)=>{
    const d = new Date();
    await db.addMess(_id, _mess, _send);
    //console.log(await db.findLastMess(_id));
    let objmess = {detail: _mess, send: _send, time: d.toISOString()};
    io.sockets.in(_id).emit('client-get-one-mess', objmess);
  })
  
  // listen get-message-event
  // input: _id (!= 1)
  if(_id != 1){
    socket.on('get-message-event', async () =>{
      socket.emit('client-get-mess', await db.findMess(_id));
    })  
  }

  // listen get-list-user-event
  // input: void
  socket.on('get-list-user-event', async ()=> {
    socket.emit('client-get-list', await db.findUsers());
  })
})


// connect
// send mess
// get mess
// get list
