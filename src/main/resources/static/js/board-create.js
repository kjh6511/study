$('#addMenuBtn').on('click', function () {
    var str = '';
    str += '<div class="row">';
    str += '<div class="col-sm-4">';
    str += '<div>메뉴</div>';
    str += '<input type="text" class="form-control form-control-user" name="stoMenuName"/>';
    str += '</div>';
    str += '<div class="col-sm-4">';
    str += '<div>가격</div>';
    str += '<input type="text" class="form-control form-control-user" name="stoMenuPrice"/>';
    str += '</div>';
    str += '<div class="col-sm-4">';
    str += '<div>사진(사진파일만)</div>';
    str += '<input type="file" accept="image/*" class="btn" name="stoMenuFile"/>';
    str += '</div>';
    str += '</div>';
    str += '<div>';
    str += '<div>소개</div>';
    str += '<input type="text" class="form-control form-control-user" name="stoMenuInfo"/>';
    str += '</div>';
    str += '<hr class="sidebar-divider">';
    $('#totalMenu').append(str);
});

$("#createBoardBtn").on("click", function() {
        var token = localStorage.getItem("AUTH");
		var formData = $("#createBoardForm").serializeObject();
		var jsonObj = JSON.stringify(formData);
		$.ajax({
			type : "POST",
			url : "/store",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Content-type","application/json");
                xhr.setRequestHeader("AUTH", token);
            },
			data : jsonObj,
			contentType: 'application/json; charset=utf-8',
			success: function(data, textStatus, request){
			    alert('등록이 완료되었습니다.');
                window.history.back();
		   },
		   error: function (request, textStatus, errorThrown) {
		        alert("error");
		   }
		});
});

$('#selectMap').on('click', function(){
    var position = new naver.maps.LatLng(37.5285189, 126.9201979);
    var curTitle = $(document).find("title").text();

    if(curTitle == '서울'){
        position = new naver.maps.LatLng(37.5558432, 126.97234);
    };
    if(curTitle == '부산'){
        position = new naver.maps.LatLng(35.1698658, 129.1768583);
    };

    var map = new naver.maps.Map('map', {
        center : position, // 지도 중앙 위치 : 위도, 경도 설정
        zoom : 15, // 줌 설정 : 1~14, 수치가 클수록 지도 확대(줌인), 이 옵션 생략시 기본값 9
        zoomControl : true, // 줌 컨트롤 표시 (기본값 표시안함)
        zoomControlOptions : { // 줌 컨트롤 오른쪽 위로 위치 설정
            position : naver.maps.Position.TOP_RIGHT // 오른쪽 위로 설정값
        },
        mapTypeControl : true, // 일반ㆍ위성 지도보기 컨트롤 표시 (기본값 표시안함)
    });
    var marker = new naver.maps.Marker({
        position: position,
        map: map
    });

    // 클릭이벤트
    naver.maps.Event.addListener(map, 'click', function(e){
        var latlng = e.coord;
        marker.setPosition(latlng);
        naver.maps.Service.reverseGeocode({
            coords: latlng,
            orders: [
              naver.maps.Service.OrderType.ADDR,
              naver.maps.Service.OrderType.ROAD_ADDR
            ].join(',')
          }, function(status, response) {
            if (status === naver.maps.Service.Status.ERROR) {
              if (!latlng) {
                return alert('ReverseGeocode Error, Please check latlng');
              }
              if (latlng.toString) {
                return alert('ReverseGeocode Error, latlng:' + latlng.toString());
              }
              if (latlng.x && latlng.y) {
                return alert('ReverseGeocode Error, x:' + latlng.x + ', y:' + latlng.y);
              }
              return alert('ReverseGeocode Error, Please check latlng');
            }

            var address = response.v2.address,
                htmlAddresses = [];
            if(address.roadAddress != ''){
                if(confirm('['+ address.roadAddress +'] 주소를 선택합니다.') == true){
                    $('#stoAddr1').val(e.coord.lat());  // 위도
                    $('#stoAddr2').val(e.coord.lng());  // 경도
                    $('#stoAddr').val(address.roadAddress);  // 도로명주소
                    $('#createBoardMapModal').modal('hide');
                };
            } else{
                alert('다시 선택해주세요');
            };
          });

    });

});
