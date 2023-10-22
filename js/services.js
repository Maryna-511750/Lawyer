const servicesCarousel = document.querySelector(".services-carousel");
const prevButton = document.querySelector(".prev-button");
const nextButton = document.querySelector(".next-button");
const slideWidth = servicesCarousel.querySelector(
  ".services-carousel-item",
).offsetWidth;
let currentPosition = 0;

function moveToNext() {
  currentPosition += slideWidth;
  if (currentPosition > 0) {
    currentPosition = -(slideWidth * (servicesCarousel.children.length - 1));
  }
  updateCarouselPosition();
}

function moveToPrev() {
  currentPosition -= slideWidth;
  if (
    currentPosition < -(slideWidth * (servicesCarousel.children.length - 1))
  ) {
    currentPosition = 0;
  }
  updateCarouselPosition();
}

function updateCarouselPosition() {
  servicesCarousel.style.transform = `translateX(${currentPosition}px)`;
}

prevButton.addEventListener("click", moveToPrev);
nextButton.addEventListener("click", moveToNext);