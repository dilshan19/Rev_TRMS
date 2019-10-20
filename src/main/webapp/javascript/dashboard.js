
window.onload = function() {
    this.getAllReimbursements();    
}

function displayReimbursementList(reimb) {

    let list = document.getElementById("reimbursementList");

    list.innerHTML = "";

    for(f of reimb) {
        let item = document.createElement("li");
        item.innerHTML = f.name;
        list.appendChild(item);
    }

}


function getAllReimbursements() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                getAllReimbursements(JSON.parse(xhr.responseText));
            } else {
                document.getElementById("reimbursementList").innerHTML = "Failed to retireve reimbursement";
            }
        } else {
            document.getElementById("reimbursementList").innerHTML = "Fetching Request...";
        }
    }
    xhr.open("GET", "add", true);
    xhr.send();
}