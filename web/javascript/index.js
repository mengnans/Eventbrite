function onloadFunction() {
    if (getCookie("access_token") == null) {
        var parameters = window.location.hash;
        if (!parameters.length > 0) {
            setCookie("errorMessage", "Please login in first",30);
            location.replace("error.jsp");
        } else {
            parameters = parameters.replace('#', '');
            var access_token = parameters.split('&')[1].split('=')[1];
            setCookie("access_token", access_token,30);
        }
    }
}

function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
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


function getAllEventsUrl() {

    location.replace("new.jsp");
}
