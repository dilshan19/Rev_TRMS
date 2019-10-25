window.onload = function() {
    this.console.log("Hello World");
    this.getMyReimbursements(); 
}

function displayReimbursementList(rList) {
    let tableBody = document.getElementById("reimbTable").getElementsByTagName('tbody')[0];
    let count;
    for(let i = 0; i < rList.length; i++) {
        let row = tableBody.insertRow(-1);
        console.log(rList[i]);
        count = 0;
        for (let property in rList[i]) {
            if (rList[i].hasOwnProperty(property)) {
                let val = rList[i][property];
                let cell = row.insertCell(count); 
                if(count == 3){
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
    //buttonListener();
}

function getMyReimbursements() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                console.log("Break1: ");
                displayReimbursementList(JSON.parse(xhr.responseText));
            } else {
                console.log("Failed to retrieve reimbursement");
            }
        } else {
            console.log("Fetching the particular reimbs...");
        }
    }
    xhr.open("GET", "login", true);//this will fetch the email
    xhr.send();
}
