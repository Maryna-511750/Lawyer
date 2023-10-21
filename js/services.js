/*document.addEventListener('DOMContentLoaded', function() {
    const servicesCarousel = document.querySelector('.services-carousel');
    const servicesCarouselItems = document.querySelectorAll('.services-carousel-item');
    const prevButton = document.querySelector('.prev-button');
    const nextButton = document.querySelector('.next-button');
    const slideWidth = servicesCarouselItems[0].offsetWidth;
    const visibleSlides = 3;
    let currentPosition = 0;

    // Функція для перемикання каруселі вправо
    function moveToNext() {
        currentPosition -= slideWidth * visibleSlides;
        if (currentPosition < -(slideWidth * (servicesCarouselItems.length - visibleSlides))) {
            currentPosition = 0;
        }
        updateCarouselPosition();
    }

    // Функція для перемикання каруселі вліво
    function moveToPrev() {
        currentPosition += slideWidth * visibleSlides;
        if (currentPosition > 0) {
            currentPosition = -(slideWidth * (servicesCarouselItems.length - visibleSlides));
        }
        updateCarouselPosition();
    }

    // Функція для оновлення положення каруселі
    function updateCarouselPosition() {
        servicesCarousel.style.transform = `translateX(${currentPosition}px)`;
    }

    // Додавання обробників подій для кнопок
    prevButton.addEventListener('click', moveToPrev);
    nextButton.addEventListener('click', moveToNext);
});*/
const carousel = document.querySelector('.services-carousel');
  const prevButton = document.querySelector('.prev-button');
  const nextButton = document.querySelector('.next-button');

  
  const itemWidth = carousel.querySelector('.services-carousel-item').offsetWidth;

 
  let currentPosition = 0;

  
  function moveToNext() {
    currentPosition += itemWidth;
    if (currentPosition > 0) {
      currentPosition = -(itemWidth * (carousel.children.length - 1));
    }
    updateCarouselPosition();
  }

  
  function moveToPrev() {
    currentPosition -= itemWidth;
    if (currentPosition < -(itemWidth * (carousel.children.length - 1))) {
      currentPosition = 0;
    }
    updateCarouselPosition();
  }

 
  function updateCarouselPosition() {
    carousel.style.transform = `translateX(${currentPosition}px)`;
  }

 
  prevButton.addEventListener('click', moveToPrev);
  nextButton.addEventListener('click', moveToNext);
