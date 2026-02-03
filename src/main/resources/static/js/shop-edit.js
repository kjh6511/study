function enableEditMode() {
  document.querySelectorAll('.editable').forEach(el => el.style.display = 'inline-block');
  document.querySelectorAll('.view-text').forEach(el => el.style.display = 'none');

  document.getElementById('edit-btn').style.display = 'none';
  document.getElementById('save-btn').style.display = 'inline-block';
  document.getElementById('cancel-btn').style.display = 'inline-block';
}

function resetToReadonlyMode() {
  document.querySelectorAll('.editable').forEach(el => el.style.display = 'none');
  document.querySelectorAll('.view-text').forEach(el => el.style.display = 'inline-block');

  document.getElementById('edit-btn').style.display = 'inline-block';
  document.getElementById('save-btn').style.display = 'none';
  document.getElementById('cancel-btn').style.display = 'none';
}

function cancelEdit() {
  location.reload(); // 원래 상태로 복원
}

document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("shopForm");

  if (form) {
    form.addEventListener("submit", async function (e) {
      e.preventDefault();

      const data = {
        shopNo: document.getElementById("shopNo").value,
        shopNm: document.getElementById("shopNm").value,
        shopId: document.getElementById("shopId").value,
        shopInfo: document.getElementById("shopInfo").value,
        shopTel: document.getElementById("shopTel").value,
        shopImg: document.getElementById("shopImg").value,
        shopStat: document.getElementById("shopStat").value
      };

      const response = await fetch("/shops/edit", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
      });

      if (response.ok) {
        const updated = await response.json();

        document.getElementById("viewNm").textContent = updated.shopNm;
        document.getElementById("viewId").textContent = updated.shopId;
        document.getElementById("viewInfo").textContent = updated.shopInfo;
        document.getElementById("viewTel").textContent = updated.shopTel;
        document.getElementById("viewImg").textContent = updated.shopImg;
        document.getElementById("viewStat").textContent = updated.shopStat.value;

        resetToReadonlyMode();
      } else {
        alert("수정에 실패했습니다. 다시 시도해주세요.");
      }
    });
  }
});
