
window.onload = function () {
    this.console.log("Hello World");
    this.getMyReimbursements();
}
var eventRow;
function displayReimbursementList(rList) {
    let tableBody = document.getElementById("reimbTable").getElementsByTagName('tbody')[0];
    for (let i = 0; i < rList.length; i++) {
        let row = tableBody.insertRow(-1);
        z = 0;
                let cell = row.insertCell(z++);
                cell.innerHTML = rList[i]["id"];
                cell = row.insertCell(z++);
                cell.innerHTML = rList[i]["requestorEmail"];
                cell = row.insertCell(z++);
                cell.innerHTML = rList[i]["location"];
                cell = row.insertCell(z++);
                let val = rList[i]["date"];
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
                cell.innerHTML = rList[i]["xFilePath"];
                cell.addEventListener("click", fileListener);
                cell = row.insertCell(z++);
	      	    var x = document.createElement("INPUT");
	    	      x.setAttribute("type", "file");
	    	      cell.appendChild(x);
                  x.addEventListener("change", fileUpdater);
                  if(rList[i]["dsapproved"] == true && rList[i]["dhapproved"] == true && rList[i]["bcapproved"] == true){
                    row.style.backgroundColor =  "#00FA9A";
                }else{
                    row.style.backgroundColor =  "#ff9999";
                }
    }

}

function fileListener(event){
	//let newCell = document.getElementById("reimbTable").rows[document.getElementById("reimbTable").rows.length - 1].cells[14];
	//console.log(event.target.parentElement.row);
	//newCell = document.getElementById("reimbTable").rows[event.target.row].cells[14];
	//console.log(eventRow);
	let jValue = event.target.innerHTML;
	let xhr = new XMLHttpRequest();
	xhr.open("POST", "file", true);
    xhr.send(JSON.stringify(jValue));
};

function fileUpdater(event){
	//let newCell = document.getElementById("reimbTable").rows[document.getElementById("reimbTable").rows.length - 1].cells[14];
	let newCell2 = event.target;
	let jValue = newCell2.value;
	let xhr = new XMLHttpRequest();
	xhr.open("POST", "file", true);
    xhr.send(JSON.stringify(jValue));
    eventRow = event.currentTarget.parentNode.parentNode.rowIndex;
    let changeCell = document.getElementById("reimbTable").getElementsByTagName('tbody')[0].rows[eventRow-1].cells[14];
    changeCell.innerHTML = this.files[0]["name"];
    while(!xhr.readyState==4 && !xhr.status==200) {};
	idCell = document.getElementById("reimbTable").getElementsByTagName('tbody')[0].rows[eventRow-1].cells[0];
	stringCell = document.getElementById("reimbTable").getElementsByTagName('tbody')[0].rows[eventRow-1].cells[14];
	xhr = new XMLHttpRequest();
	xhr.open("POST", "filetodb", true);
    xhr.send(JSON.stringify(idCell.innerHTML + "%" + stringCell.innerHTML));
};

function getMyReimbursements() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
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
    xhr.open("GET", "add", true);//this will fetch the email
    xhr.send();
}
