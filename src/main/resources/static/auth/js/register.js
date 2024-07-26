document.getElementById('registerForm').addEventListener('submit', function(event) {
    var termsChecked = document.getElementById('terms').checked;
    if (!termsChecked) {
        event.preventDefault();
        var alertMessage = document.getElementById('alertMessage');
        alertMessage.classList.add('show');
        setTimeout(function() {
            alertMessage.classList.add('hidden');
        }, 6000);
        setTimeout(function() {
            alertMessage.classList.remove('show', 'hidden');
        }, 7000);
    }
});