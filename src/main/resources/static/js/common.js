     jQuery.fn.serializeObject = function() {
      var obj = null;
      try {
          if(this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) {
              var arr = this.serializeArray();
              if(arr){ obj = {};
              jQuery.each(arr, function() {
                  obj[this.name] = this.value; });
              }
          }
      }catch(e) {
          alert(e.message);
      }finally {}
      return obj;
    }

    function logout(){
        localStorage.removeItem("AUTH");
        location.href="/front/login";
    }


    $(document).ready(function(){
    //아이템 카테고리
    $.ajax({
            type : "GET",
            url : "/item/category/",
            contentType: 'application/json; charset=utf-8',
            success: function(data){
            var result = data;
            var html ='';
             $.each(result, function(idx, val){
                html += '<a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#catogoryLayouts'+idx+'"';
                html += 'aria-expanded="false" aria-controls="catogoryLayouts'+idx+'">';
                html += '<div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>';
                html += val.icName;
                html += '<div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div></a>';
                if(val.children != null){
                    html += '<div class="collapse" id="catogoryLayouts'+idx+'" aria-labelledby="headingOne"';
                    html += 'data-bs-parent="#sidenavAccordion">';
                    html += '<nav class="sb-sidenav-menu-nested nav">';
                    $.each(val.children, function(i,v){
                        html += '<a class="nav-link" href="/shop/' + v.icNum + '">';
                        html += v.icName
                        html+= '</a>';
                    });
                    html += '</nav></div>';
                };
             });
             $('#category').append(html);
           },
           error: function (request, textStatus, errorThrown) {
                alert("시스템 오류");
           }
        });

    })