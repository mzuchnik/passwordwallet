
let buttonPasswordForm = document.getElementById('showNewPasswordForm');

buttonPasswordForm.onclick = function () {
    let passwordForm = document.getElementById('password-form');
    passwordForm.classList.toggle('hidden-form');
}

