
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

    let string = "add?accept=1&id="+this.id;

    xhr.open("PUT", string , true);
    xhr.send();

    // xhr.open("POST", "reject" , true);
    // xhr.send("id="+this.id);
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

function displayReimbursementList(reimb) {
    let tableBody = document.getElementById("employee-table").getElementsByTagName('tbody')[0];
    let count;
    for(let i = 0; i < reimb.length; i++) {
        let row = tableBody.insertRow(-1);
        //row.setAttribute("name",i);
        //row.innerHTML = "id=\"i\"";
        count = 0;
        for (let property in reimb[i]) {
            if (reimb[i].hasOwnProperty(property)) {
                let val = reimb[i][property];
                let cell = row.insertCell(count); 
                if(count == 0){
                    let cell2 = row.insertCell(count++); 
                    cell2.innerHTML = "<tr><td><div><div><button class=\"option-button\" name=\"accept\" id=\""+val+"\">"+
                    "<span>Accept</span></button></div><form method=\"POST\" action=\"supervisor\">"+
                    "<input type=\"submit\" class=\"option-button\" name=\"id\" value=\"Deny "+val+"\">"+
                    "</input></form></div></td></tr>";

                    let cell = row.insertCell(count);
                    cell.innerHTML = val;     
                    //row.setAttribute("id",val);
                }else if(count == 4){
                    let d = val.dayOfMonth;
                    let m = val.monthValue + 1; // Month is 0-indexed
                    let y = val.year;
                    cell.innerHTML = m+"-"+d+"-"+y;
                }else if(count == 8){
                    cell.innerHTML = "$" + val;
                }else if(count > 9){
                    cell.innerHTML = (val === true) ? "Yes" : "No";
                }else{
                    cell.innerHTML = val;    
                }
                count++;
            }
          }
    }
    //buttonListener();
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