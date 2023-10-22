const casesItem = document.querySelectorAll('.cases-item');
let previousCaseItem = null;

casesItem.forEach((caseItem) => {
    caseItem.addEventListener('mouseover', (_) => {
        if (previousCaseItem !== null) {
            previousCaseItem.classList.remove('cases-item-big');
        }
        caseItem.classList.add('cases-item-big');
        previousCaseItem = caseItem;
    });
});
