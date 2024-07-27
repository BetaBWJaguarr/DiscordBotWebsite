function validateForgotPasswordForm() {
    const emailInput = document.getElementById('email');
    const emailError = document.getElementById('emailError');

    emailError.textContent = '';

    const email = emailInput.value.trim();

    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(email)) {
        emailError.textContent = 'Please enter a valid email address.';
        return false;
    }

    return true;
}
