//header
document.addEventListener('click', e => {
    const isDropdownButton = e.target.matches('[data-dropdown-button]') || e.target.closest('[data-dropdown-button]');
    if (!isDropdownButton && e.target.closest('[data-dropdown]') != null) return

    let currentDropdown;
    if (isDropdownButton) {
        currentDropdown = e.target.closest('[data-dropdown]');
        currentDropdown.classList.toggle('active');
    }

    document.querySelectorAll('[data-dropdown].active').forEach(dropdown => {
        if (dropdown === currentDropdown) return
        dropdown.classList.remove('active');
    });
});

//footer
// Get the button:
let mybutton = document.getElementById("myBtn");

// When the user scrolls down 20px from the top of the document, show the button
window.onscroll = function() {scrollFunction()};

function scrollFunction() {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        mybutton.style.display = "block";
    } else {
        mybutton.style.display = "none";
    }
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
    document.body.scrollTop = 0; // For Safari
    document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
}

//setting status
// Namespace for handling 'available' and 'unavailable' statuses
(function() {
    let currentButton;

    function openStatusModal(button) {
        currentButton = button;
        if (currentButton.dataset.status === 'fully-booked') {
            return;
        }
        $('#statusModal').modal('show');
    }

    function changeStatus(newStatus) {
        currentButton.dataset.status = newStatus;
        currentButton.textContent = capitalizeFirstLetter(newStatus);
        $('#statusModal').modal('hide');
        updateButtonStyles(currentButton);
    }

    function capitalizeFirstLetter(string) {
        return string.charAt(0).toUpperCase() + string.slice(1).replace('-', ' ');
    }

    function updateButtonStyles(button) {

        // Update styles based on the new status
        if (button.dataset.status === 'available') {
            button.style.backgroundColor = 'green';
            button.style.color = 'white';
        } else if (button.dataset.status === 'unavailable') {
            button.style.backgroundColor = 'red';
            button.style.color = 'white';
        }
    }

    // Initialize button styles on page load
    document.addEventListener('DOMContentLoaded', function() {
        document.querySelectorAll('.status-button.available-unavailable').forEach(updateButtonStyles);
    });

    // Expose functions to the global scope if needed
    window.openStatusModalAvailable = openStatusModal;
    window.changeStatusAvailable = changeStatus;
})();

// Namespace for handling 'complete' and 'deny' statuses
(function() {
    let currentButton;

    function openStatusModal(button) {
        currentButton = button;
        if (currentButton.dataset.status === 'pending') {
            return;
        }
        $('#statusModal').modal('show');
    }

    function changeStatus(newStatus) {
        currentButton.dataset.status = newStatus;
        currentButton.textContent = capitalizeFirstLetter(newStatus);
        $('#statusModal').modal('hide');
        updateButtonStyles(currentButton);
    }

    function capitalizeFirstLetter(string) {
        return string.charAt(0).toUpperCase() + string.slice(1).replace('-', ' ');
    }

    function updateButtonStyles(button) {

        // Update styles based on the new status
        if (button.dataset.status === 'complete') {
            button.style.backgroundColor = 'green';
            button.style.color = 'white';
        } else if (button.dataset.status === 'deny') {
            button.style.backgroundColor = 'red';
            button.style.color = 'white';
        }
    }

    // Initialize button styles on page load
    document.addEventListener('DOMContentLoaded', function() {
        document.querySelectorAll('.status-button.complete-deny').forEach(updateButtonStyles);
    });

    // Expose functions to the global scope if needed
    window.openStatusModalComplete = openStatusModal;
    window.changeStatusComplete = changeStatus;
})();


// let currentButton;
//
// function openStatusModal(button) {
//     currentButton = button;
//     if (currentButton.dataset.status === 'fully-booked') {
//         return;
//     }
//     $('#statusModal').modal('show');
// }
//
// function changeStatus(newStatus) {
//     currentButton.dataset.status = newStatus;
//     currentButton.textContent = capitalizeFirstLetter(newStatus);
//     $('#statusModal').modal('hide');
//     updateButtonStyles(currentButton);
// }
//
// function capitalizeFirstLetter(string) {
//     return string.charAt(0).toUpperCase() + string.slice(1).replace('-', ' ');
// }
//
// function updateButtonStyles(button) {
//
//     // Update styles based on the new status
//     if (button.dataset.status === 'available') {
//         button.style.backgroundColor = 'green';
//         button.style.color = 'white';
//     } else if (button.dataset.status === 'unavailable') {
//         button.style.backgroundColor = 'red';
//         button.style.color = 'white';
//     }
// }
//
// // Initialize button styles on page load
// document.addEventListener('DOMContentLoaded', function() {
//     document.querySelectorAll('.status-button').forEach(updateButtonStyles);
// });





