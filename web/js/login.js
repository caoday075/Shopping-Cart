

function validUserName(input) {
    var regex = /^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$/g;
//Only contains alphanumeric characters, underscore and dot.
//Underscore and dot can't be at the end or start of a username (e.g _username / username_ / .username / username.).
//Underscore and dot can't be next to each other (e.g user_.name).
//Underscore or dot can't be used multiple times in a row (e.g user__name / user..name).
//Number of characters must be between 2 to 20.
    var errorMsg = document.getElementsByClassName("errorMsg");
    if (input.value.match(regex)) {
        errorMsg[0].textContent = "";
    } else {
        errorMsg[0].textContent = "Invalid Username";
        errorMsg[0].style.color = "red";
    }
}

function validFullName(input) {
    var regex = /^[a-zA-Z\s\.']*$/;
    var errorMsg = document.getElementsByClassName("errorMsg");
    if (input.value.match(regex)) {
        errorMsg[0].textContent = "";
    } else {
        errorMsg[0].textContent = "Invalid Full Name";
        errorMsg[0].style.color = "red";
    }
}



function validEmail(inputEmail) {
    var regex = /[a-z0-9_-]{3,30}@[a-z0-9-]{3,30}(.[a-z1-9]{2,5}){1,2}/gm;
    var errorEmail = document.getElementById("errorMsg");

    if (inputEmail.value !== "") {
        if (!inputEmail.value.match(regex)) {
            errorEmail.textContent = 'Invalid Email';
            errorEmail.style.color = "red";
        } else {
            errorEmail.textContent = '';
        }

    }
}

//function register() {
//    var password = document.getElementById('password').value;
//    var confirm = document.getElementById('confirmPassword').value;
//    var field = document.getElementById("checkconfirm")
//    if (confirm != password) {
//        field.innerHTML = "Password NOT matching";
//    } else {
//        field.innerHTML = " aa";
//    }
//}