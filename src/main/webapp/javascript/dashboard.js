
window.onload = function() {
    this.getAllReimbursements();    
}

function displayReimbursementList(reimb) {

    let list = document.getElementById("reimbursementList");

    list.innerHTML = "";

    for(f of reimb) {
        let item = document.createElement("li");
        item.innerHTML = f.type;
        list.appendChild(item);
    }

    let table = document.getElementById("employee-table");
    let count;
    for(let i = 0; i < reimb.length; i++) {
        let row = table.insertRow(-1);
        console.log(reimb[i]);
        count = 0;
        for (let property in reimb[i]) {
            if (reimb[i].hasOwnProperty(property)) {
                let val = reimb[i][property];
                let cell = row.insertCell(count); 
                if(count == 0){
                    let cell2 = row.insertCell(count++); 
                    cell2.innerHTML = "<tr><td><div><button class=\"option-button\" id=\"accept\">"+
                    "<span>Accept</span></button></div><div><button class=\"option-button\" id=\"deny\">"+
                    "<span>Deny</span></button></div></td></tr>";
                    let cell = row.insertCell(count);
                    cell.innerHTML = val;     
                }else if(count == 3){
                    let d = val.dayOfMonth;
                    let m = val.monthValue + 1; // Month is 0-indexed
                    let y = val.year;
                    cell.innerHTML = m+"-"+d+"-"+y;
                }else if(count == 7){
                    cell.innerHTML = "$" + val;
                }else if(count > 8){
                    cell.innerHTML = (val === true) ? "Yes" : "No";
                }else{
                    cell.innerHTML = val;    
                }
                count++;
            }
          }
    }
}

function addButtons(){

}

function getAllReimbursements() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                displayReimbursementList(JSON.parse(xhr.responseText));
            } else {
                document.getElementById("reimbursementList").innerHTML = "Failed to retireve reimbursement";
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