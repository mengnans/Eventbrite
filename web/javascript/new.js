function onloadFunction() {
    var address = "https://www.eventbriteapi.com/v3/users/me/owned_events/?token=";
    var accessToken = getCookie("access_token");
    address = address + accessToken;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var responseJson = JSON.parse(this.responseText);
            var eventNum = responseJson.pagination.object_count;
            for(i = 0; i < eventNum; i ++){
                document.getElementById("demo").innerHTML += responseJson.events[i].name.text + "<\br>";

            }
        }
    };
    xhttp.open("GET", address, true);
    xhttp.send();
}


function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
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