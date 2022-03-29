$(document).ready(function(){
    var token = localStorage.getItem("AUTH");
    var borMenuNo = getParameterByName('borMenuNo');

    $.ajax({
        type : "GET",
        url : "/store/list/" + borMenuNo,
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Content-type","application/json");
            xhr.setRequestHeader("AUTH", token);
        },
        success: function(data){
        var result = data.data;
        alert(result);
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

