$("#checkUserId").on("click", function() {
    var userId = document.getElementById("userId").value;
    $.ajax({
        type : "GET",
        url : "/register/check/"+userId,
        contentType: 'application/json; charset=utf-8',
        dataType : "text",
        success: function(data){
        alert(data);
            if(data == "ID 중복"){
                $("#userId").val("");
            };
       },
       error: function (request, textStatus, errorThrown) {
            alert("error");
       }
    });
});
