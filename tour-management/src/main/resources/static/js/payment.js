document.addEventListener('DOMContentLoaded', function() {
    const today = new Date().toISOString().substring(0, 10);
    document.getElementById('paymentDate').value = today;
});
