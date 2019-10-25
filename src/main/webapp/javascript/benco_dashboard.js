
window.onload = function() {
    this.getAllReimbursements(); 
}

function buttonListener(){
    let acceptButs = document.querySelectorAll("[name=accept]");
    let denyButs = document.querySelectorAll("[name=deny]");
    for(elem of acceptButs){
        elem.addEventListener("click", accepts, false);
    }
    for(elem of denyButs){
        elem.addEventListener("click", rejects, false);
    }
}

function rejects(){
    console.log( "Deny RequestID: " + this.id);
    let xhr = new XMLHttpRequest();
    console.log(this);
    let but = this;
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                but.disabled=true;
            } else {
                console.log("Update unsuccessful");
            }
        } else {
            console.log("Processing");
        }
    }
    let string = "add?accept=0&id="+this.id;
    xhr.open("PUT", string , true);
    xhr.send();
}

function accepts () {
    console.log( "accept RequestID: " + this.id);
    let xhr = new XMLHttpRequest();
    console.log(this);
    let but = this;
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                but.disabled=true;
            } else {
                console.log("Update unsuccessful");
            }
        } else {
            console.log("Processing");
        }
    }
    let string = "add?accept=1&id="+this.id;
    xhr.open("PUT", string , true);
    xhr.send();
}

function displayReimbursementList(rList) {
    let tableBody = document.getElementById("employee-table").getElementsByTagName('tbody')[0];
    for (let i = 0; i < rList.length; i++) {
        let row = tableBody.insertRow(-1);
        z = 0;
                let cell = row.insertCell(z++);
                let val = rList[i]["id"];
                cell.innerHTML = "<tr><td><div><div><button class=\"option-button\" name=\"accept\" id=\""+val+"\">"+
                    "<span>Accept</span></button>"+"<button class=\"option-button\" name=\"deny\" id=\""+val+"\">"+
                    "<span>Deny</span></button>"+
                    "</div><form method=\"POST\" action=\"benco\">"+
                    "<input type=\"submit\" class=\"alter-button\" name=\"id\" value=\"Alter "+val+"\">"+
                    "</input></form></div></td></tr>";
                cell = row.insertCell(z++);
                cell.innerHTML = rList[i]["id"];
                cell = row.insertCell(z++);
                cell.innerHTML = rList[i]["requestorEmail"];
                cell = row.insertCell(z++);
                cell.innerHTML = rList[i]["location"];
                cell = row.insertCell(z++);
                val = rList[i]["date"];
                let d = val.dayOfMonth;
                let m = val.monthValue + 1; 
                let y = val.year;
                cell.innerHTML = m + "-" + d + "-" + y;
                cell = row.insertCell(z++);
                cell.innerHTML = rList[i]["type"];
                cell = row.insertCell(z++);
                cell.innerHTML = rList[i]["description"];
                cell = row.insertCell(z++);
                cell.innerHTML = rList[i]["format"];
                cell = row.insertCell(z++);
                cell.innerHTML = "$" + rList[i]["originalAmount"];
                cell = row.insertCell(z++);
                cell.innerHTML = rList[i]["tentativeAmount"];
                cell = row.insertCell(z++);
                cell.innerHTML = rList[i]["gradeUploaded"];
                cell = row.insertCell(z++);
                cell.innerHTML = rList[i]["dsapproved"];
                cell = row.insertCell(z++);
                cell.innerHTML = rList[i]["dhapproved"];
                cell = row.insertCell(z++);
                cell.innerHTML = rList[i]["bcapproved"];
                cell = row.insertCell(z++);
                cell.innerHTML = rList[i]["bcaltered"];
                cell = row.insertCell(z++);
                if(rList[i]["dsapproved"] == true && rList[i]["dhapproved"] == true && rList[i]["bcapproved"] == true){
                    row.style.backgroundColor =  "#00FA9A";
                }else{
                    row.style.backgroundColor =  "#ff9999";
                }

    }
    buttonListener();
}

function getAllReimbursements() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                displayReimbursementList(JSON.parse(xhr.responseText));
            } else {
                console.log("Failed to retrieve reimbursement");
            }
        } else {
            console.log("Fetching Request...");
        }
    }
    xhr.open("GET", "add", true);
    xhr.send();
}