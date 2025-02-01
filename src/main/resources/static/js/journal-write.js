document.addEventListener('DOMContentLoaded', () => {
    const addThoughtBtn = document.getElementById('add-thought-btn');
    const thoughtsContainer = document.getElementById('thoughts-container');

    const updateThoughtContainerIndices = () => {
        const thoughtContainers = thoughtsContainer.querySelectorAll('.thought-container');
        thoughtContainers.forEach((container, index) => {
            container.querySelector('.page-input').setAttribute('name', `phraseList[${index}].page`);
            container.querySelector('.thought-text').setAttribute('name', `phraseList[${index}].phrase`);
        });
    };

    addThoughtBtn.addEventListener('click', () => {
        const thoughtContainer = document.createElement('div');
        thoughtContainer.classList.add('thought-container');

        thoughtContainer.innerHTML = `
            <p>
                <strong>페이지</strong>
                <input type="number" class="page-input" required>
            </p>
            <textarea class="thought-text" placeholder="문장을 입력하세요" required></textarea>
            <button type="button" class="remove-btn">삭제</button>
        `;

        // 삭제 버튼 이벤트 리스너 추가
        thoughtContainer.querySelector('.remove-btn').addEventListener('click', () => {
            thoughtsContainer.removeChild(thoughtContainer);
            updateThoughtContainerIndices();
        });

        thoughtsContainer.appendChild(thoughtContainer);
        updateThoughtContainerIndices();
    });

    thoughtsContainer.addEventListener('click', (event) => {
        if (event.target.classList.contains('remove-btn')) {
            const thoughtContainer = event.target.parentElement;
            thoughtsContainer.removeChild(thoughtContainer);
            updateThoughtContainerIndices();
        }
    });
});