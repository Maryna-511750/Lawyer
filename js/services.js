const prevButton = document.querySelector(".prev-button");
const nextButton = document.querySelector(".next-button");

const servicesSlides = [
  '<div class="services-carousel-item"> <h3>Family Law</h3> <div><p>Uncontested/Contested Divorce</p> <p>Arbitration</p> <p>Spousal Maintenance</p> <p>Marital Property</p> <p>Visitation Rights and Co-Parenting</p> <p>Declaration of Marriage Invalid</p><p>Paternity</p></div> <a href="#contacts">Get consultation</a></div>',
  '<div class="services-carousel-item"><h3>Hereditary Cases</h3> <div><p>Consultation on Complicated Cases</p><p>Wills by Disabled Persons</p> <p>Drafting of Wills</p> <p>Corporate Succession</p> <p>Statutory Share</p> <p>Contestation of an Inheritance</p> <p>Insolvency of the Estate</p></div> <a href="#contacts">Get consultation</a> </div>',
  '<div class="services-carousel-item"> <h3>Administrative Cases</h3> <div><p>Administrative Hearings</p> <p>Licensing and Permitting</p> <p>Professional Discipline Hearings</p> <p>Competitive Procurement and Bid Protests</p> <p>Medicaid Program Integrity Cases</p> <p>General Regulatory Matters</p> <p>Rulemaking and Rule Challenge Proceedings</p></div> <a href="#contacts">Get consultation</a> </div>',
  '<div class="services-carousel-item"> <h3>Economic Disputes</h3> <div><p>Merger Agreements</p> <p>Stock and Asset Purchase Agreements</p> <p>Tender and Exchange Offers</p> <p>Leveraged Buyouts</p> <p>Joint Ventures</p> <p>Recapitalizations</p> <p>Anti-takeover Strategies</p></div> <a href="#contacts">Get consultation</a> </div>'
]

let currentSlideIdx = 0;

function renderSlide() {
  const slideContainer = document.querySelector('.services-carousel-slide');
  slideContainer.innerHTML = servicesSlides[currentSlideIdx];
  if (window.matchMedia('(min-width: 768px)').matches) {
    const secondSlideIdx = currentSlideIdx + 1 >= servicesSlides.length ? 0 : currentSlideIdx + 1;
    slideContainer.innerHTML += servicesSlides[secondSlideIdx];
    if (window.matchMedia('(min-width: 1024px)').matches) {
      const thirdSlideIdx = secondSlideIdx + 1 >= servicesSlides.length ? 0 : secondSlideIdx + 1;
      slideContainer.innerHTML += servicesSlides[thirdSlideIdx];
    }
  }
}

function nextSlide() {
  currentSlideIdx = currentSlideIdx + 1 >= servicesSlides.length ? 0 : currentSlideIdx + 1;
  renderSlide();
}

function prevSlide() {
  currentSlideIdx = currentSlideIdx - 1 < 0 ? servicesSlides.length - 1 : currentSlideIdx - 1;
  renderSlide();
}

renderSlide()

prevButton.addEventListener("click", prevSlide);
nextButton.addEventListener("click", nextSlide);

window.addEventListener('resize', renderSlide);