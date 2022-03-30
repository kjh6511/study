$(document).ready(function(){
    var token = localStorage.getItem("AUTH");
    var borCatNo = getParameterByName('borCatNo');
    var borMenuNo = getParameterByName('borMenuNo');

    /* store 게시판 _ 지역별 */
    if(borMenuNo == 1){
    		$.ajax({
            type : "GET",
            url : "/store/list/" + borMenuNo,
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Content-type","application/json");
                xhr.setRequestHeader("AUTH", token);
            },
            success: function(data){
            var result = data.data;
            var str = '';
            $.each(result, function(i){
                str +=  '<tr style="cursor: pointer;" onclick="location.href=\'store-view?borCatNo='+borCatNo+'&borMenuNo='+borMenuNo+'&stoNo='+result[i].stoNo+'\'">';
                str +=  '<td> <input type="checkbox" value='+result[i].stoNo+'/></td>';
                str +=  '<td>'+ (i+1) +'</td>';
                str +=  '<td>'+ result[i].stoName+'</td>';
                str +=  '<td>'+ result[i].memNic+'</td>';
                str +=  '<td>'+ result[i].stoAddr+'</td>';
                str +=  '<td>'+ result[i].stoRegDt+'</td>';
                str += '</tr>';
            });
            $('#boardTable > tbody').append(str);
           },
           error: function (request, textStatus, errorThrown) {
           }
        });
    };
});

function getParameterByName(name) {
name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
results = regex.exec(location.search);
return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

