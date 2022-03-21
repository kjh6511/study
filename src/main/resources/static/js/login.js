$(document).ready(function() {
	$("#loginBtn").on("click", function() {
		$.ajax({
			type : "POST",
			url : "/login?memId=" + $("#memId").val() + "&memPw=" + $("#memPw").val(),
			success: function(data, textStatus, request){
				var responseHeader = request.getResponseHeader('AUTH');
		        localStorage.setItem('AUTH', responseHeader);
                location.href="/front/main";
		   },
		   error: function (request, textStatus, errorThrown) {
		        alert("ID와 비밀번호를 확인해주세요.");
		   }
		});
	});

});