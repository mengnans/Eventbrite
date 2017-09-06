function onloadFunction(){
    var access_token = getCookie("access_token");
    var url = "https://www.eventbriteapi.com/v3/users/me/owned_events/?token=" + access_token;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {

        if (this.readyState == 4 && this.status == 200) {
            responseJSON = JSON.parse(this.responseText);
            var num = responseJSON.pagination.object_count;
            var innerHTML = "";
            for(i = 0; i < num && i < 50; i ++){
                var name = responseJSON.events[i].name.text;
                var id = responseJSON.events[i].id;
                var url = responseJSON.events[i].url;
                var createdDate = responseJSON.events[i].created.split('T')[0];
                innerHTML += "    <tr class='clickable-row' data-href='saveEventId?eventId=" + id +"'>\n" +
                    "        <td>" +name +"</td> <td>"+ createdDate + "</td> <td>"+ url+ "</td>\n" +
                    "    </tr>";
            }
            document.getElementById("tbody").innerHTML = innerHTML;
            jQuery(document).ready(function($) {
                $(".clickable-row").click(function() {
                    window.location = $(this).data("href");
                });
            });
        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();

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

function logout(){
    delete_cookie("access_token");
    location.replace("login.html");
}

function delete_cookie( cname ) {
    document.cookie = cname + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}