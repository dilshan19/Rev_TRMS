
window.onload = function() {
    this.getAllReimbursements(); 
}

function buttonListener(){
    let theParent = document.querySelectorAll("#accept");
    console.log(theParent);
    for(elem of theParent){
        elem.addEventListener("click", updateReimb, false);
    }
    //theParent.addEventListener("click", updateReimb, false);
    //this.document.getElementById("accept").addEventListener("click", updateReimb, false); 
}

function updateReimb () {
    console.log( "RequestID: " + this.name);
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                getAllReimbursements();
            } else {
                console.log("Update unsuccessful");
            }
        } else {
            console.log("Processing");
        }
    }
    xhr.open("GET", "add", true);
    xhr.send("id="+this.name+"accept=true");
}

function displayReimbursementList(reimb) {
    let tableBody = document.getElementById("employee-table").getElementsByTagName('tbody')[0];
    let count;
    for(let i = 0; i < reimb.length; i++) {
        let row = tableBody.insertRow(-1);
        row.setAttribute("name",i);
        //row.innerHTML = "id=\"i\"";
        count = 0;
        for (let property in reimb[i]) {
            if (reimb[i].hasOwnProperty(property)) {
                let val = reimb[i][property];
                let cell = row.insertCell(count); 
                if(count == 0){
                    let cell2 = row.insertCell(count++); 
                    cell2.innerHTML = "<tr><td><div><button class=\"option-button\" id=\"accept\" name=\""+val+"\">"+
                    "<span>Accept</span></button></div><div><button class=\"option-button\" id=\"deny\">"+
                    "<span>Deny</span></button></div></td></tr>";
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
    buttonListener();
}

function getAllReimbursements() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                displayReimbursementList(JSON.parse(xhr.responseText));
            } else {
                document.getElementById("reimbursementList").innerHTML = "Failed to retrieve reimbursement";
            }
        } else {
            //console.log( xhr.readyState );
            //  console.log( xhr.responseText );

            document.getElementById("reimbursementList").innerHTML = "Fetching Request...";
        }
    }
    xhr.open("GET", "add", true);
    xhr.send();
}