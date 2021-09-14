

function test_click()
{
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        document.getElementById("box").innerHTML = this.responseText;
        }
    xhttp.open("GET", "/test", true);
    xhttp.send();
}