    $(document).ready(function() {
            var token = localStorage.getItem("AUTH");

    		$.ajax({
    			type : "GET",
    			url : "/member/myinfo",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Content-type","application/json");
                    xhr.setRequestHeader("AUTH", token);
                },
    			success: function(data){
    			var result = data.data;
                $("#memNic").text(result.memNic);
    		   },
    		   error: function (request, textStatus, errorThrown) {
    		       
    		   }
    		});

    });