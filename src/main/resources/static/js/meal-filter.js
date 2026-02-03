 function loadLevel2(button) {
     const topCateNo = button.dataset.cateNo || button.getAttribute("data-cate-no");
     if (!topCateNo) {
         alert("카테고리 번호가 없습니다!");
         return;
     }

     // cateLv1만 주소에 반영
     const url = new URL(window.location.href);
     url.searchParams.set('cateLv1', topCateNo);
     url.searchParams.delete('cateNo'); // Lv2 초기화
     window.location.href = url.toString(); // 목록 갱신을 위해 새로고침
 }

 // 페이지 로드 시 Lv1이 선택된 상태면 Lv2 버튼들 로드
 window.addEventListener("DOMContentLoaded", function () {
     const url = new URL(window.location.href);
     const lv1 = url.searchParams.get("cateLv1");

     if (lv1) {
         fetch(`/categories/children?parentNo=${lv1}`)
             .then(res => res.json())
             .then(data => {
                 const level2Area = document.getElementById('level2-buttons');
                 level2Area.innerHTML = '';

                 data.forEach(child => {
                     const btn = document.createElement('button');
                     btn.textContent = child.cateNm;
                     btn.onclick = () => {
                         const url = new URL(window.location.href);
                         url.searchParams.set('cateNo', child.cateNo);
                         window.location.href = url.toString(); // Lv2 클릭 시 새로고침
                     };
                     level2Area.appendChild(btn);
                 });
             })
             .catch(() => {
                 const level2Area = document.getElementById('level2-buttons');
                 level2Area.innerHTML = '<p>Lv2 카테고리를 불러오는 데 실패했어요.</p>';
             });
     }
 });
