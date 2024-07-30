document.getElementById('registerForm').addEventListener('submit', function(event) {
    var termsChecked = document.getElementById('terms').checked;
    if (!termsChecked) {
        event.preventDefault();
        var alertMessage = document.getElementById('alertMessage');
        alertMessage.classList.add('alert-danger');
        alertMessage.classList.remove('d-none');

        setTimeout(function() {
            alertMessage.classList.add('d-none');
        }, 6000);
    }
});
