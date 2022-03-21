    $("#registerBtn").on("click", function() {

		var formData = $("#registerForm").serializeObject();
		var jsonObj = JSON.stringify(formData);

		$.ajax({
			type : "POST",
			url : "/member/register",
			data : jsonObj,
			contentType: 'application/json; charset=utf-8',
			success: function(data, textStatus, request){
			    alert("회원가입이 완료되었습니다.")
                location.href="/front/login";
		   },
		   error: function (request, textStatus, errorThrown) {
		        alert("error");
		   }
		});
	});
