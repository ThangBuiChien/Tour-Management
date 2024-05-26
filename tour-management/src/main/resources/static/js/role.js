// scripts.js

// Function to validate the form inputs
function validateForm() {
    let roleName = document.querySelector('input[placeholder="Role Name"]').value;

    if (roleName.trim() === "") {
        alert("Role Name cannot be empty.");
        return false;
    }
    return true;
}

// Attach event listeners to the forms to validate before submission
document.addEventListener('DOMContentLoaded', function() {
    let forms = document.querySelectorAll('form');

    forms.forEach(function(form) {
        form.addEventListener('submit', function(event) {
            if (!validateForm()) {
                event.preventDefault();
            }
        });
    });
});
