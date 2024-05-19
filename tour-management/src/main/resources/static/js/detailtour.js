document.addEventListener("DOMContentLoaded", function() {
    const starRatings = document.querySelectorAll('.star-rating');

    starRatings.forEach(starRating => {
        const rating = parseInt(starRating.getAttribute('data-rating'), 10);
        let starsHtml = '';

        for (let i = 0; i < 5; i++) {
            if (i < rating) {
                starsHtml += '<i class="fas fa-star" style="color: gold;"></i>';
            } else {
                starsHtml += '<i class="far fa-star" style="color: gray;"></i>';
            }
        }

        starRating.innerHTML = starsHtml;
    });
});
