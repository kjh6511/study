function submitAIMeal() {
    const form = document.getElementById("stepForm");
    const formData = new FormData(form);
    const jsonData = {};

    formData.forEach((value, key) => {
        jsonData[key] = value;
    });

    document.getElementById('loading').style.display = 'block';
    document.getElementById("aiResult").innerHTML = "";

    fetch("/ai", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(jsonData)
    })
    .then(res => res.text())
    .then(text => {
        document.getElementById('loading').style.display = 'none';

        let data;
        try {
            data = JSON.parse(text);
        } catch (e) {
            document.getElementById("aiResult").innerHTML = "<p>⚠️ AI 응답을 해석하는 데 문제가 발생했어요.</p>";
            return;
        }

        let html = `
            <h3>🥗 추천 식단</h3>
            <p>${formatSectionObject(data.content)}</p>
        `;

        if (data.workout) {
            html += `
                <h4>🏋️ 운동 루틴</h4>
                <p>${formatSectionObject(data.workout)}</p>
            `;
        }

        if (data.shoppingList && Array.isArray(data.shoppingList)) {
            html += `
                <h4>🛒 장보기 리스트</h4>
                <ul>
                    ${data.shoppingList.map(item => `<li>${item}</li>`).join('')}
                </ul>
            `;
        }

        if (data.youtube && Array.isArray(data.youtube)) {
            html += `
                <h4>📺 관련 유튜브 검색</h4>
                <ul>
                    ${data.youtube.map(keyword => {
                        const link = `https://www.youtube.com/results?search_query=${encodeURIComponent(keyword)}`;
                        return `<li><a href="${link}" target="_blank">${keyword}</a></li>`;
                    }).join('')}
                </ul>
            `;
        }

        if (data.message) {
            html += `
                <h4>💬 응원 메시지</h4>
                <p><em>${data.message}</em></p>
            `;
        }

        document.getElementById("aiResult").innerHTML = html;
    })
    .catch(err => {
        document.getElementById('loading').style.display = 'none';
        document.getElementById("aiResult").innerHTML = "<p>😢 식단 추천에 실패했어요. 다시 시도해주세요.</p>";
        console.error(err);
    });
}

function formatSectionObject(obj) {
    if (typeof obj !== 'object') return obj;
    return Object.entries(obj).map(([key, value]) => `<strong>${key}</strong>: ${value}`).join('<br>');
}
