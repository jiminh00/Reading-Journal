document.addEventListener('DOMContentLoaded', () => {
    console.log('Script loaded!');

    const addThoughtBtn = document.getElementById('add-thought-btn');
    const thoughtsContainer = document.getElementById('thoughts-container');
    const form = document.querySelector('form');

    if (!form) {
        console.error('Form not found!');
        return;
    }

    const journalIdValue = document.querySelector('input[name="journalId"]')?.value || '';
    console.log('Journal ID:', journalIdValue);

    const updateThoughtContainerIndices = () => {
        const thoughtContainers = thoughtsContainer.querySelectorAll('.thought-container');
        console.log('Updating indices for thought containers');
        thoughtContainers.forEach((container, index) => {
            const pageInput = container.querySelector('.page-input');
            const phraseTextarea = container.querySelector('.thought-text');
            const hiddenIdInput = container.querySelector('.hidden-id');
            const isDeletedInput = container.querySelector('.is-deleted');
            const journalIdInput = container.querySelector('.journal-id');

            pageInput.setAttribute('name', `memorablePhraseResList[${index}].page`);
            phraseTextarea.setAttribute('name', `memorablePhraseResList[${index}].phrase`);
            isDeletedInput.setAttribute('name', `memorablePhraseResList[${index}].isDeleted`);

            if (hiddenIdInput) {
                hiddenIdInput.setAttribute('name', `memorablePhraseResList[${index}].memorablePhraseId`);
            }
            if (journalIdInput) {
                journalIdInput.setAttribute('name', `memorablePhraseResList[${index}].journalId`);
            }
        });
    };

    addThoughtBtn?.addEventListener('click', () => {
        const thoughtContainer = document.createElement('div');
        thoughtContainer.classList.add('thought-container');
        thoughtContainer.innerHTML = `
            <input type="hidden" class="hidden-id" name="">
            <input type="hidden" class="journal-id" name="" value="${journalIdValue}">
            <input type="hidden" class="is-deleted" name="" value="false">
            <p>
                <strong>페이지</strong>
                <input type="number" class="page-input" required>
            </p>
            <textarea class="thought-text" placeholder="문장을 입력하세요" required></textarea>
            <button type="button" class="remove-btn">삭제</button>
        `;
        thoughtsContainer.appendChild(thoughtContainer);
        updateThoughtContainerIndices();
    });

    thoughtsContainer.addEventListener('click', (event) => {
        if (event.target.classList.contains('remove-btn')) {
            const thoughtContainer = event.target.parentElement;
            const isDeletedInput = thoughtContainer.querySelector('.is-deleted');

            if (isDeletedInput) {
                isDeletedInput.value = 'true';
                console.log('삭제 후 isDeleted 값:', isDeletedInput.value);
                thoughtContainer.style.display = 'none';
                const inputs = thoughtContainer.querySelectorAll('input, textarea');
                inputs.forEach(input => input.removeAttribute('required'));
            } else {
                thoughtsContainer.removeChild(thoughtContainer);
            }
            updateThoughtContainerIndices();
        }
    });
});