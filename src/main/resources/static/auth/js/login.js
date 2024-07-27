function validateForm() {
    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value.trim();

    let isValid = true;
    let emailError = '';
    let passwordError = '';

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (email === '') {
        emailError = 'Email is required.';
        isValid = false;
    } else if (!emailRegex.test(email)) {
        emailError = 'Invalid email format.';
        isValid = false;
    }

    if (password === '') {
        passwordError = 'Password is required.';
        isValid = false;
    }

    document.getElementById('emailError').innerText = emailError;
    document.getElementById('passwordError').innerText = passwordError;

    document.getElementById('email').classList.toggle('input-error', emailError !== '');
    document.getElementById('password').classList.toggle('input-error', passwordError !== '');

    return isValid;
}
