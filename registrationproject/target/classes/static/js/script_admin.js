//Contains the users in a global variable
var usersContainer;

window.onload = function() {
    updateAdminTable();
};

//Data request
function updateAdminTable() {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'api/admin');
    xhr.onreadystatechange = function () {
      var DONE = 4;
      var OK = 200;
      if (xhr.readyState === DONE) {
        if (xhr.status === OK) {
          var users = JSON.parse(xhr.responseText);
          niceDatas(users);
          generateTable(users);
          usersContainer = users;
        } else {
          console.log('Error: ' + xhr.status);
        }
      }
    };
    xhr.send(null);
}


//Users editing
function niceDatas(users){
	for(var k in users){
		//Newsletter
		if(users[k].newsLetter == "off"){
			users[k].newsLetter = "<span class='glyphicon glyphicon-remove'></span>";
		}else{
			users[k].newsLetter = "<span class='glyphicon glyphicon-ok'></span>";
		}
	}
}

//Table fill with users
function generateTable(users){
	var tableRows = "";
	for(var k in users){
		tableRows += "<tr class='tableRows'><td>" +
			users[k].lastName + "</td><td>" +
			users[k].firstName + "</td><td>" +
			users[k].emailAddress + "</td><td>" +
			users[k].phone + "</td><td>" +
			users[k].education + "</td><td>" +
			users[k].englishLevel + "</td><td>" +
			users[k].age + "</td><td style='text-align:center'>" +
			users[k].newsLetter + "</td></tr>";
	}
	document.querySelector(".resultTableRows").innerHTML = tableRows;
};