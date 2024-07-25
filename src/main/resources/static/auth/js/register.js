document.getElementById('registerForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const termsAccepted = document.getElementById('terms').checked;

    if (!termsAccepted) {
        alert("You must agree to the terms to proceed.");
        return;
    }
});
