document.getElementById('registrationForm').addEventListener('submit', function (event) {
    var password = document.getElementById('password').value;
    var passwordConfirm = document.getElementById('passwordConfirm').value;
    var passwordMatchMessage = document.getElementById('passwordMatchMessage');
    if (password !== passwordConfirm) {
        passwordMatchMessage.textContent = 'Passwords do not match';
        event.preventDefault();
    } else {
        passwordMatchMessage.textContent = '';
    }
});