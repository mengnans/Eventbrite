function onloadFunction() {
    if (getCookie("access_token") == "") {
        var parameters = window.location.hash;
        if (!parameters.length > 0) {
            location.replace("login.html");
            alert("Please login in first");
        } else {
            parameters = parameters.replace('#', '');
            var access_token = parameters.split('&')[1].split('=')[1];
            setCookie("access_token", access_token, 1);
        }
    }
    if (getCookie("eventId") == "") {
        $("#mailbox_center").addClass("disabled");
        $("#event_package").addClass("disabled");
        $("#template_editor").addClass("disabled");
    } else {

        $("#mailbox_center").removeClass("disabled");
        $("#event_package").removeClass("disabled");
        $("#template_editor").removeClass("disabled");
    }
}

function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function logout() {
    delete_cookie("access_token");
    location.replace("login.html");
}

function delete_cookie(cname) {
    document.cookie = cname + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}