document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('numMembers').addEventListener('input', function() {
        let maxCapacity = parseInt(this.max); // More reliable access to the 'max' attribute
        let numMembers = parseInt(this.value);

        // Ensure the number does not exceed max capacity
        if (numMembers > maxCapacity) {
            alert("Number of members exceeds tour capacity!");
            this.value = maxCapacity; // Reset value to maximum capacity
            numMembers = maxCapacity; // Ensure subsequent logic uses the corrected number
        }

        let container = document.getElementById('membersContainer');
        container.innerHTML = ''; // Clear existing fields if number changes
        for (let i = 0; i < numMembers; i++) {
            container.innerHTML += `
                <div>
                    <label>Member ${i + 1} Name:</label>
                    <input type="text" name="listOfMember" required />
                </div>`;
        }
        updateTotalPrice(numMembers);
    });

    function updateTotalPrice(numMembers) {
        let pricePerPerson = parseFloat(document.getElementById('pricePerPerson').textContent.replace('$', ''));
        let totalPrice = pricePerPerson * numMembers;
        document.querySelector('.total-price span').textContent = totalPrice.toFixed(2);
    }
});
