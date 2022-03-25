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

            $.ajax({
                type : "get",
                url : "/board/category",
                contentType: 'application/json; charset=utf-8',
                success: function(data, textStatus, request){
                    var borCatNo = getParameterByName('borCatNo');
                    var borMenuNo = getParameterByName('borMenuNo');
                    var result = data.data;
                    var titleName ='';
                    var str = '';
                    $.each(result,function(i){
                        str += '<li class="nav-item">';
                        str += '<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapse'
                                +i+'"  aria-expanded="true" aria-controls="collapse'+i+'">';
                        str += '<span>'+ result[i].borCatName +'</span>';
                        str += '</a>';
                        str += '<div id="collapse'+i+'" aria-labelledby="headingTwo" data-parent="#accordionSidebar"  class="collapse';
                        if(borCatNo == result[i].borCatNo){
                            str += ' show';
                        }
                        str += '">';
                        str += '<div class="bg-white py-2 collapse-inner rounded">';
                        var regionList = result[i].resBoardMenuList;
                        $.each(regionList, function(j){
                            str += '<a href="board?borCatNo='+regionList[j].borCatNo+'&borMenuNo='+regionList[j].borMenuNo+'" class="collapse-item';
                            if(borCatNo == result[i].borCatNo && borMenuNo == regionList[j].borMenuNo){
                            str += ' active';
                            titleName = regionList[j].borMenuName;
                            }
                            str += '">'+regionList[j].borMenuName+'</a>';
                        });
                        str += '</div></div></li>';
                    });
                    $('#navBoardCategory').append(str);

                    if($(document).find("title").text() == ''){
                        $('#title').text(titleName);
                        $('#boardTitle').text(titleName);
                    };

               },
               error: function (request, textStatus, errorThrown) {
               }
            });

    });

    function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
    results = regex.exec(location.search);
    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    }
