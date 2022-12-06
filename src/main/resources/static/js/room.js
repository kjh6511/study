$(document).ready(function(){

    console.log(roomName + ", " + roomNo + ", " + username);

    var sockJs = new SockJS("/stomp/chat");
    //1. SockJS를 내부에 들고있는 stomp를 내어줌
    var stomp = Stomp.over(sockJs);

    //2. connection이 맺어지면 실행
    stomp.connect({}, function (){
       console.log("STOMP Connection")

       //4. subscribe(path, callback)으로 메세지를 받을 수 있음
       stomp.subscribe("/sub/chat/room/" + roomNo, function (chat) {
           var content = JSON.parse(chat.body);
           var message = content.message;
           var writer = content.writer;
           var str = '';
           console.log("sub ====== message")

           if(writer === username){
               str = "<div class='col-6'>";
               str += "<div class='alert alert-secondary'>";
               str += "<b>" + writer + " : " + message + "</b>";
               str += "</div></div>";
               $("#msgArea").append(str);
           }
           else{
               str = "<div class='col-6'>";
               str += "<div class='alert alert-warning'>";
               str += "<b>" + writer + " : " + message + "</b>";
               str += "</div></div>";
               $("#msgArea").append(str);
           }
       });

    });

    $("#button-send").on("click", function(e){
        var msg = document.getElementById("msg");

        console.log(username + ":" + msg.value);
        stomp.send('/pub/chat/message', {}, JSON.stringify({roomNo: roomNo, message: msg.value, writer: username, memNo:memNo}));
        msg.value = '';
    });

        $("#button-disconnect").on("click", function(e){
            stomp.send('/pub/chat/end', {}, JSON.stringify({roomNo: roomNo, writer: username}));
            stomp.disconnect();
            console.log(username + ":" + msg.value + "disconnect");
        });
});