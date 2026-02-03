document.addEventListener("DOMContentLoaded", function () {
  // 전체 선택/해제
  const checkAll = document.getElementById("checkAll");
  checkAll?.addEventListener("change", function () {
    const checkboxes = document.querySelectorAll("input[name='selectedCartIds']");
    checkboxes.forEach(cb => cb.checked = checkAll.checked);
  });

  // 수량 증가
  document.querySelectorAll(".btn-plus").forEach(btn => {
    btn.addEventListener("click", () => {
      const cartNo = btn.dataset.id;
      updateQuantity(cartNo, 1);
    });
  });

  // 수량 감소
  document.querySelectorAll(".btn-minus").forEach(btn => {
    btn.addEventListener("click", () => {
      const cartNo = btn.dataset.id;
      updateQuantity(cartNo, -1);
    });
  });
});

// Ajax로 수량 업데이트 요청
function updateQuantity(cartNo, delta) {
  const quantitySpan = document.querySelector(`span[data-cart-no="${cartNo}"]`);
  let currentQty = parseInt(quantitySpan.textContent, 10);
  const newQty = currentQty + delta;

  if (newQty < 1) {
    Swal.fire({
      icon: "warning",
      title: "수량 오류",
      text: "수량은 최소 1개 이상이어야 해요!",
    });
    return;
  }

  if (newQty > 99) {
    Swal.fire({
      icon: "warning",
      title: "수량 초과",
      text: "수량은 최대 99개까지만 담을 수 있어요!",
    });
    return;
  }

  fetch(`/carts/updateQuantity`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      cartNo: cartNo,
      delta: delta
    })
  })
    .then(res => {
      if (!res.ok) throw new Error("응답 실패");
      return res.json();
    })
    .then(data => {
      if (data.success) {
        window.location.reload();
      } else {
        Swal.fire({
          icon: "error",
          title: "변경 실패",
          text: "수량 변경이 되지 않았어요.",
        });
      }
    })
    .catch(err => {
      console.error("오류 발생:", err);
      Swal.fire({
        icon: "error",
        title: "문제가 생겼어요!",
        text: "수량 변경 중 오류가 발생했어요.",
      });
    });
}

 function submitOrderForm() {
    const form = document.getElementById("cartOrderForm");
    const checked = document.querySelectorAll("input[name='selectedCartIds']:checked");

    if (checked.length === 0) {
      alert("선택된 항목이 없습니다!");
      return;
    }

    checked.forEach(cb => {
      const hiddenInput = document.createElement("input");
      hiddenInput.type = "hidden";
      hiddenInput.name = "cartNos";
      hiddenInput.value = cb.value;
      form.appendChild(hiddenInput);
    });

    form.submit();
  }