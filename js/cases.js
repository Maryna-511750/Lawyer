const cases = [
  {
    id: "1",
    name: "",
    image: "",
    briefDescription: "In favor of the plaintiff, $13,500 was recovered from the respondent surgeon for improperly performing cosmetic surgery for correcting the shape of the plaintiff’s nose.",
    additionalText: "The plaintiff claimed that she, as a patient, had entered into a contract with the surgeon, and he agreed to perform plastic surgery on her nose to increase her aesthetic appeal. The plaintiff's appearance didn't improve; in fact, her face was disfigured. I helped the woman receive compensation for her pain and suffering."
  },
  {
    id: "2",
    name: "",
    image: "",
    briefDescription: "On 04.03.2017, the Family Court of the State of New York satisfied the claims of attorney William Rhoades in the interests of the client about the deprivation of parental rights.",
    additionalText: "The court recognized that the father must be deprived of parental rights as he does not fulfill his obligations to the child in accordance with the norms of family law. It was a challenging case, but I proved that the presence of the father negatively affects the child, causing immediate aggression and psychological harm."
  },
  {
    id: "3",
    name: "",
    image: "",
    briefDescription: "In June 2019, the inheritance division case was considered at the Surrogate’s Court of the State of New York. The plaintiff insisted that his father died without leaving a will, and the plaintiff is the primary heir.",
    additionalText: "I helped the plaintiff prove to the court that he is the rightful successor and is entitled to 80% of the entire inheritance, which included significant amounts in American bank accounts, as well as three mansions in the Hamptons, a fleet of yachts, and 500 square meters of commercial real estate in New York."
  }
];

renderCases(cases, "1");

function renderCases (cases, itemId) {
  let casesDomString = '';
  for (const someCase of cases){
    if (someCase.id == itemId){
      casesDomString += `
      <div class="cases-item cases-item-big" id = ${someCase.id}>
          <p>${someCase.briefDescription}</p>
          <p class="cases-paragraph-hidden">${someCase.additionalText}</p>
        </div>
      `;
    }
  }  
  for (const someCase of cases){  
    if (someCase.id !== itemId){
      casesDomString += `
      <div class="cases-item" id = ${someCase.id}>
        <p>${someCase.briefDescription}</p>
        <p class="cases-paragraph-hidden">${someCase.additionalText}</p>
      </div>
      `;
    }
  }
  const caseContainer = document.querySelector('.cases-content');
  caseContainer.innerHTML = casesDomString;
  eventCases();
};

function eventCases (){
  const casesItem = document.querySelectorAll(".cases-item");
  casesItem.forEach((caseItem) => {
    caseItem.addEventListener("click", (_) => {
      renderCases(cases, caseItem.id);
    });
  });
}