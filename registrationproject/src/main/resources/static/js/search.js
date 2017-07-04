//Search in Datas

//Event listeners
document.querySelector("#findLastName").addEventListener("keypress", find);
document.querySelector("#findFirstName").addEventListener("keypress", find);
document.querySelector("#findEmail").addEventListener("keypress", find);
document.querySelector("#findPhone").addEventListener("keypress", find);
document.querySelector("#findEducation").addEventListener("keypress", find);
document.querySelector("#findEnglishLevel").addEventListener("keypress", find);
document.querySelector("#findAge").addEventListener("keypress", find);
	
function find(e){
	
	//Enter press
	if (e.keyCode == 13){
		var input = this.value.toLowerCase();
		var table = document.querySelector(".resultTableRows");
		var tr = table.getElementsByTagName("tr");
		var td;
		for(var i = 0; i < tr.length; i++){
			
			//Witch input box is selected
			if(this.id == "findLastName"){
				td = tr[i].getElementsByTagName("td")[0];
			}else if(this.id == "findFirstName"){
				td = tr[i].getElementsByTagName("td")[1];
			}else if(this.id == "findEmail"){
				td = tr[i].getElementsByTagName("td")[2];
			}else if(this.id == "findPhone"){
				td = tr[i].getElementsByTagName("td")[3];
			}else if(this.id == "findEducation"){
				td = tr[i].getElementsByTagName("td")[4];
			}else if(this.id == "findEnglishLevel"){
				td = tr[i].getElementsByTagName("td")[5];
			}else if(this.id == "findAge"){
				td = tr[i].getElementsByTagName("td")[6];
			}
			
			//Hide those who don't match the search query
			if(td){
				if(td.innerHTML.toLowerCase().indexOf(input) > -1){
					tr[i].style.display = "";
				}else{
					tr[i].style.display = "none";
				}
			} 
		}
	}
}

//Show users who want newsLetter
function showCheck(){
	var tableSortRows = "";
	for(var k in usersContainer){
		if(usersContainer[k].newsLetter == "<span class='glyphicon glyphicon-ok'></span>"){
			tableSortRows += "<tr><td>" +
				usersContainer[k].lastName + "</td><td>" +
				usersContainer[k].firstName + "</td><td>" +
				usersContainer[k].emailAddress + "</td><td>" +
				usersContainer[k].phone + "</td><td>" +
				usersContainer[k].education + "</td><td>" +
				usersContainer[k].englishLevel + "</td><td>" +
				usersContainer[k].age + "</td><td style='text-align:center'>" +
				usersContainer[k].newsLetter + "</td></tr>";
		}
	}
	document.querySelector(".resultTableRows").innerHTML = tableSortRows;
}