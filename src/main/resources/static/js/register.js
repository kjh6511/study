$("#checkMemId").on("click", function() {
    var memId = document.getElementById("memId").value;
    $.ajax({
        type : "GET",
        url : "/register/check/"+memId,
        contentType: 'application/json; charset=utf-8',
        dataType : "text",
        success: function(data){
        alert(data);
            if(data == "ID 중복"){
                $("#memId").val("");
            };
       },
       error: function (request, textStatus, errorThrown) {
            alert("error");
       }
    });
});