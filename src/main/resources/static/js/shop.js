$("#createShopBtn").on("click", function() {
    		var formData = $("#shopFrom").serializeObject();
    		var jsonObj = JSON.stringify(formData);
    $.ajax({
        type : "POST",
        url : "/shop/create/",
        data : jsonObj,
        contentType: 'application/json; charset=utf-8',
        success: function(data){
            if(data == "FAILURE"){
               alert("중복등록입니다.")
            }else{
                alert("등록완료")
                location.reload("/shop/myshop");
            };

       },
       error: function (request, textStatus, errorThrown) {
            alert("error");
       }
    });
});