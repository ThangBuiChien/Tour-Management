document.addEventListener("DOMContentLoaded", function() {
    const starRatings = document.querySelectorAll('.star-rating');

    starRatings.forEach(starRating => {
        const rating = parseInt(starRating.getAttribute('data-rating'), 10);
        let starsHtml = '';

        for (let i = 0; i < 5; i++) {
            if (i < rating) {
                starsHtml += '<i class="fas fa-star"></i>';
            } else {
                starsHtml += '<i class="far fa-star"></i>';
            }
        }

        starRating.innerHTML = starsHtml;
    });
});

function updateRatingText(ratingDescription) {
    document.getElementById('rating-text').innerText = ratingDescription;
}

function parseTourDescription(description) {
    const days = description.split(/Day \d+/).filter(Boolean);
    return days.map((day, index) => `Day ${index + 1}: ${day.trim()}`);
}