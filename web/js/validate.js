function validName(input) {
    var regex = /^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$/g;

    var errorMsg = document.getElementsByClassName("errorMsg");
    if (input.value.match(regex)) {
        errorMsg[0].textContent = "";
    } else {
        errorMsg[0].textContent = "Invalid Name";
        errorMsg[0].style.color = "red";
    }
}

function validAddress(input) {
    var regex = /^[0-9]{1,4}(([\-\/][0-9]{1,4})|([ABCDFGHJKLMNPRSTV]{1,2}))/g;
    // var regex = /^[0-9]{1,4}([\-\/][0-9]{1,4})+\s[A-z]+\s[A-z]+/g;

    var errorMsg = document.getElementsByClassName("errorMsg");
    if (input.value.match(regex)) {
        errorMsg[0].textContent = "";
    } else {
        errorMsg[0].textContent = "Invalid Address";
        errorMsg[0].style.color = "red";
    }
}

function validPhone(input) {
    var regex = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im;

    var errorMsg = document.getElementsByClassName("errorMsg");
    if (input.value.match(regex)) {
        errorMsg[0].textContent = "";
    } else {
        errorMsg[0].textContent = "Invalid Phone";
        errorMsg[0].style.color = "red";
    }
}





