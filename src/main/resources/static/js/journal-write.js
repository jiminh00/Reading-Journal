document.addEventListener('DOMContentLoaded', () => {
    const addThoughtBtn = document.getElementById('add-thought-btn');
    const thoughtsContainer = document.getElementById('thoughts-container');

    addThoughtBtn.addEventListener('click', () => {
        const thoughtContainer = document.createElement('div');
        thoughtContainer.classList.add('thought-container');

        thoughtContainer.innerHTML = `
            <p><strong>페이지:</strong> <input type="number" name="page" class="page-input" value=""></p>
            <textarea name="thought" class="thought-text" placeholder="문장을 입력하세요"></textarea>
            <button type="button" class="remove-btn">삭제</button>
        `;

        const removeBtn = thoughtContainer.querySelector('.remove-btn');
        removeBtn.addEventListener('click', () => {
            thoughtsContainer.removeChild(thoughtContainer);
        });

        thoughtsContainer.appendChild(thoughtContainer);
    });

    thoughtsContainer.addEventListener('click', (event) => {
        if (event.target.classList.contains('remove-btn')) {
            const thoughtContainer = event.target.parentElement;
            thoughtsContainer.removeChild(thoughtContainer);
        }
    });
});