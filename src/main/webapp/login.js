var lButton = document.getElementById('loginbutton');
var rButton = document.getElementById('registerbutton');
var form = document.getElementById("login-form");
rButton.addEventListener("click", changeActionToRegister);
lButton.addEventListener("click", changeActionToLogin);

function changeActionToRegister(event){
    form.action = "register"
}

function changeActionToLogin(event){
    form.action = "login"
}