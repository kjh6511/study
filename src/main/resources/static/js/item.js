$("#createItemifBtn").on("click", function() {
    var itemCount = $("input[name=stockNm]").length;
    var stocks = new Array();
    for(var i=0; i<itemCount; i++){
        var stock = new Object();
        stock.stockNm = $("input[name=stockNm]").eq(i).val();
        stock.stockSize = $("input[name=stockSize]").eq(i).val();
        stock.stockQtq = $("input[name=stockQtq]").eq(i).val();
        stocks.push(stock);
    }

    var formData = $("#itemifFrom").serializeObject();
    formData.stocks = stocks;
    var jsonObj = JSON.stringify(formData);
    $.ajax({
        type : "POST",
        url : "/item/create/",
        data : jsonObj,
        contentType: 'application/json; charset=utf-8',
        success: function(data){
                alert("등록완료");
                document.location.href ="/shop/myshop";
       },
       error: function (request, textStatus, errorThrown) {
            alert("error");
       }
    });
});

function topSelect(top){
    var topIcNum = top.value;
    $('#icNum option').remove();
        $.ajax({
            type : "GET",
            url : "/item/category/select/" + topIcNum,
            contentType: 'application/json; charset=utf-8',
            success: function(data){
                if(data != null){
                    var html = '';
                    $.each(data, function(i,v){
                       html += '<option id="downSelect" value="'+v.icNum+'">'+v.icName+'</option>';
                    });
                    $('#icNum').append(html);
                }
           },
           error: function (request, textStatus, errorThrown) {
                alert("error");
           }
        });
}