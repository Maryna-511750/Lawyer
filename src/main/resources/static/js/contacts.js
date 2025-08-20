const form = document.querySelector(".lawyer-contacts-form");
    
function handleSubmit(event) {
    event.preventDefault();
    const status = document.querySelector(".lawyer-contacts-status");
    fetch(event.target.action, {
    method: 'POST',
    body: new FormData(event.target),
    headers: {
        'Accept': 'application/json'
    }
    }).then(response => {
        if (response.ok) {
            status.innerHTML = "Thanks for your submission!";
            form.reset()
        } else {
        response.json().then(data => {
            if (Object.hasOwn(data, 'errors')) {
            status.innerHTML = data["errors"].map(error => error["message"]).join(", ")
            } else {
            status.innerHTML = "Oops! There was a problem submitting your form"
            }
        })
        }
    }).catch(error => {
        status.innerHTML = "Oops! There was a problem submitting your form"
    });
}
form.addEventListener("submit", handleSubmit)


function isMobileDevice() {
    return /Mobi|Android/i.test(navigator.userAgent);
}


if (isMobileDevice()) {
    document.querySelector('a[href^="tel:"]').addEventListener('click', function(e) {
        e.preventDefault(); // Зупиняємо перехід за посиланням
        var phoneNumber = this.getAttribute('href').replace('tel:', '');
        window.location.href = 'tel:' + phoneNumber;
    });
}else {
    document.querySelector('a[href^="tel:"]').addEventListener('click', function(e) {
        e.preventDefault(); 
    });
}