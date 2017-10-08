function logout() {
    delete_cookie("access_token");
    location.replace("login.html");
}

function delete_cookie(cname) {
    document.cookie = cname + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}

