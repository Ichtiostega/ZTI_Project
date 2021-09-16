function my_contract_view()
{
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        data = JSON.parse(this.response)
        html = "<table><tr><th>Due date</th><th>Bounty</th><th>Description</th><th>Status</th></tr>"
        data.forEach(element => {
            html += "<tr><td>" + element["due_date"] + "</td><td>" + element["bounty"] + "</td><td>" + element["description"] + "</td><td>"
            if(element["status"] == 0)
            {
                html += "Posted"
            }
            else if(element["status"] == 1)
            {
                html += "Ongoing"
            }
            else if(element["status"] == 2)
            {
                html += "Success"
            }
            else
            {
                html += "Failed"
            }
            if ((element["status"] >= 2 && new Date(element["due_date"]) < new Date(element["end_date"])) || (element["status"] == 1 && new Date(element["due_date"]) < new Date()))
            {
                html += ", Overdue"
            }
            html += "</td></tr>"
        });
        html += "</table>"
        document.getElementById("view").innerHTML = html
        }
    xhttp.open("GET", "/my_contracts", true);
    xhttp.send();
}

function hunter_contract_view()
{
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        data = JSON.parse(this.response)
        html = "<table><tr><th>Due date</th><th>Bounty</th><th>Description</th><th>Action</th></tr>"
        data.forEach(element => {
            html += "<tr><td>" + element["due_date"] + "</td><td>" + element["bounty"] + "</td><td>" + 
            element["description"] + "</td><td>";
            if(element["status"] == 1)
            {
                html += '<button onclick="contract_status(' + element["id"] + ', 2)">Success</button>' +
                '<button onclick="contract_status(' + element["id"] + ', 3)">Failure</button>';
            }
            else if(element["status"] == 2)
            {
                html += "Success"
            }
            else
            {
                html += "Failed"
            }
            if (element["status"] >= 2 && new Date(element["due_date"]) < new Date(element["end_date"]))
            {
                html += ", Overdue"
            }
            html += '</td></tr>';
        });
        html += "</table>"
        document.getElementById("view").innerHTML = html
        }
    xhttp.open("GET", "/my_contracts", true);
    xhttp.send();
}

function contract_status(id, status)
{
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        hunter_contract_view()
    }
    xhttp.open("PUT", "/contract_status", true);
    xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({ 
        "id": id,
        "status": status
    }));
}

function contract_add_view()
{
    document.getElementById("view").innerHTML = '' +
        '<div class="sec_form">' +
        '<div class="inputs">' +
        '<div class="input">Due Date: <input type="text" id="due_date"/></div>' +
        '<div class="input">Bounty: <input type="text" id="bounty"/></div>' +
        '<div class="big_input"><div>Description:</div> <textarea id="description" maxlength=1000 rows=10></textarea></div>' +
        '<div class="submit"><button onclick="add_contract()">Post</button></div>' +
        '</div>' +
        '</div>'
}

function browse_contracts()
{
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        data = JSON.parse(this.response)
        html = "<table><tr><th>Due date</th><th>Bounty</th><th>Description</th><th>Action</th></tr>"
        data.forEach(element => {
            html += "<tr><td>" + element["due_date"] + "</td><td>" + element["bounty"] + "</td><td>" + 
            element["description"] + "</td><td>" + '<button onclick="accept_contract(' + element["id"] + ')">Accept</button>' + '</td></tr>';
        });
        html += "</table>"
        document.getElementById("view").innerHTML = html
        }
    xhttp.open("GET", "/available_contracts", true);
    xhttp.send();
}

function accept_contract(id)
{
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        browse_contracts()
    }
    xhttp.open("PUT", "/accept_contract", true);
    xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhttp.send(JSON.stringify({ 
        "id": id,
    }));
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

function show_stats() {
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        data = JSON.parse(this.response)
        html = "<table><tr><th>Ongoing</th><th>Done</th><th>Failed</th><th>Overdue</th><th>Success ratio</th><th>Overdue ratio</th></tr><tr>";
        data.forEach(element => {
            html += "<td>" + element + "</td>";
        });
        html += "<td>" + Math.floor((parseInt(data[1]) / (parseInt(data[1]) + parseInt(data[2]))) * 100).toString() + "%</td>";
        html += "<td>" + Math.floor((parseInt(data[3]) / (parseInt(data[0]) + parseInt(data[1]) + parseInt(data[2]))) * 100).toString() + "%</td>";
        html += "</tr></table>"
        document.getElementById("view").innerHTML = html
        }
    xhttp.open("GET", "/my_stats", true);
    xhttp.send();
}