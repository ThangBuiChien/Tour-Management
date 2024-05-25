document.addEventListener('DOMContentLoaded', function() {
    const numMembersInput = document.getElementById('numMembers');
    const container = document.getElementById('membersContainer');
    const submitBtn = document.getElementById('submitBtn');

    numMembersInput.addEventListener('input', function() {
        let numMembers = parseInt(this.value);
        let maxCapacity = parseInt(this.max);

        // Check if the input value is a positive number
        if (!/^[1-9]\d*$/.test(this.value)) {
            alert("Please enter a positive number.");
            this.value = ''; // Reset the input value
            numMembers = 0; // Ensure subsequent logic uses the corrected number
        }

        // Ensure the number does not exceed max capacity
        if (numMembers > maxCapacity) {
            alert("Number of members exceeds tour capacity!");
            this.value = maxCapacity; // Reset value to maximum capacity
            numMembers = maxCapacity; // Ensure subsequent logic uses the corrected number
        }

        container.innerHTML = ''; // Clear existing fields if number changes
        for (let i = 0; i < numMembers; i++) {
            container.innerHTML += `
            <div>
                <label>Member ${i + 1} Name:</label>
                <input type="text" name="listOfMember" required minlength="1" />
            </div>`;
        }

        // Add event listener to each input field
        const memberNameInputs = container.querySelectorAll('input[name="listOfMember"]');
        memberNameInputs.forEach(input => {
            input.addEventListener('input', function() {
                if (this.value.trim() === '') {
                    this.setCustomValidity('Please enter a name for this member.');
                } else {
                    this.setCustomValidity('');
                }
            });
        });

        updateSubmitButton();
        updateTotalPrice(numMembers);
    });

    function updateTotalPrice(numMembers) {
        let pricePerPerson = parseFloat(document.getElementById('pricePerPerson').textContent.replace('$', ''));
        let totalPrice = pricePerPerson * numMembers;
        document.querySelector('.total-price span').textContent = totalPrice.toFixed(2);
    }

    function updateSubmitButton() {
        const memberNameInputs = container.querySelectorAll('input[name="listOfMember"]');
        const allInputsFilled = Array.from(memberNameInputs).every(input => input.value.trim() !== '');
        submitBtn.disabled = !allInputsFilled || numMembersInput.value.trim() === '';
    }

    numMembersInput.addEventListener('input', updateSubmitButton);
    container.addEventListener('input', updateSubmitButton);
});