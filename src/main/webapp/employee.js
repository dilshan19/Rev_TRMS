// Find a <table> element with id="myTable":
var table = document.getElementById("employee-table").getElementByTagName('tbody')[0];
//var button = document.getElementById("employee-submit-request");
var table = document.getElementById("employee-table").getElementByTagName('tbody')[0];
/* button.addEventListener("click", addRow) */

function addRow(event){
    // Create an empty <tr> element and add it to the 1st position of the table:
    var counter = table.rows.length;
    var row = table.insertRow(counter);

    // Insert new cells (<td> elements) at the 1st and 2nd position of the "new" <tr> element:
    row.setAttribute("scope", "row");
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);

    // Add some text to the new cells:
    cell1.innerHTML = counter.toString().bold();
    cell2.innerHTML = "Money";
    cell3.innerHTML = "@Money";
    cell4.innerHTML = "@Money2";
}
