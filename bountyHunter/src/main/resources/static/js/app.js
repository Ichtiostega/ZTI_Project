function my_contract_view()
{
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        data = JSON.parse(this.response)
        html = "<table><tr><th>Due date</th><th>Bounty</th><th>Description</th><th>Status</th></tr>"
        data.forEach(element => {
            html += "<tr><td>" + element["due_date"] + "</td><td>" + element["bounty"] + "</td><td>" + element["description"] + "</td><td>" + element["status"] + "</td></tr>"
        });
        html += "</table>"
        document.getElementById("view").innerHTML = html
        }
    xhttp.open("GET", "/my_contracts", true);
    xhttp.send();
}

function contract_add_view()
{
    document.getElementById("view").innerHTML = '' +
        '<div><label> Due Date: <input type="text" id="due_date"/> </label></div>' +
        '<div><label> Bounty: <input type="text" id="bounty"/> </label></div>' +
        '<div><label> Description: <input type="text" id="description"/> </label></div>' +
        '<div><button onclick="add_contract()">Post</button></div>'
}

function add_contract()
{
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        my_contract_view()
    }
    xhttp.open("POST", "/add_contract", true);
    xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({ 
        "bounty": document.getElementById("bounty").value,
        "due_date": document.getElementById("due_date").value,
        "description": document.getElementById("description").value
    }));
}