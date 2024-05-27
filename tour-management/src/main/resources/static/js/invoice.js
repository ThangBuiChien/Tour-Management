document.addEventListener('DOMContentLoaded', () => {
    // Example: Alert when the form is submitted
    const forms = document.querySelectorAll('.button_update');
    forms.forEach(form => {
        form.addEventListener('submit', (event) => {
            alert('Status update submitted!');
        });
    });
});
