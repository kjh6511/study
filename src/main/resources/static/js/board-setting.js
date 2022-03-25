$(document).ready(function() {
    $.ajax({
        type : "get",
        url : "/board/category",
        contentType: 'application/json; charset=utf-8',
        success: function(data, textStatus, request){
        var result = data.data;
        $.each(result,function(i){
            if(result[i].borCatNo == 1){
                var regionList = result[i].resBoardMenuList;
                if(regionList.length==0){
                    $('#regionTable > tbody').append("<tr><td colspan='4'> 정보가 없습니다.</td></tr>");
                }else{
                    var str;
                        $.each(regionList,function(j){
                            str += '<tr>';
                            str += '<tr><th class="text-center" scope="row"><input type="checkbox" value="'
                            + regionList[j].borMenuNo + '"></th>';
                            str += '<td>'+ regionList[j].borMenuName +'</td>';
                            str += '<td>'+ regionList[j].borMenuStat +'</td>';
                            str += '<td>'+ regionList[j].borMenuRegDt +'</td>';
                            str += '<input type="hidden" value="'+ regionList[j].borMenuNum +'"/>';
                            str += '</tr>';
                        });
                   $('#regionTable > tbody').append(str);
                };
            };
        });
       },
       error: function (request, textStatus, errorThrown) {
            alert("error");
       }
    });

});

$('.icon-hover').hover(function(){
    $(this).css('color','#5bc0de');
}, function() {
    $(this).css('color','white');
});

function createMenuBtn(type){
    var borCatNo = type;
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
                           "borCatNo":borCatNo,
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
