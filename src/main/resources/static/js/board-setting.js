            $(document).ready(function() {});

        $('.icon-hover').hover(function(){
            $(this).css('color','#5bc0de');
        }, function() {
            $(this).css('color','white');
        });

   function createMenuBtn(type){
        var borCateNo = type;
        var borMenuNum;
        var borMenuName;
        var borMenuStat;
        if(type == "1"){
            borMenuName = $('#regionNameInput').val();
            borMenuStat = $("#regionStatusSelect option:selected").val();
            borMenuNum =  $('#regionTable tr').length;
        }else if(type == "2"){

        };
                var formData = JSON.stringify({
                               "borCateNo":borCateNo,
                               "borMenuNum":borMenuNum,
                               "borMenuName":borMenuName,
                               "borMenuStat":borMenuStat});
	    		$.ajax({
        			type : "POST",
        			url : "/board/menu",
        			data : formData,
        			contentType: 'application/json; charset=utf-8',
        			success: function(data, textStatus, request){
                    location.reload();
        		   },
        		   error: function (request, textStatus, errorThrown) {
        		        alert("error");
        		   }
        		});
	};
