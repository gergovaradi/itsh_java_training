//Sequence for table

//Event listeners
document.querySelector("#sortByLastName").addEventListener("click", sortBy);
document.querySelector("#sortByFirstName").addEventListener("click", sortBy);
document.querySelector("#sortByEmail").addEventListener("click", sortBy);
document.querySelector("#sortByPhone").addEventListener("click", sortBy);
document.querySelector("#sortByEducation").addEventListener("click", sortBy);
document.querySelector("#sortByEnglishLevel").addEventListener("click", sortBy);
document.querySelector("#sortByAge").addEventListener("click", sortBy);

//Sort by the selected col
function sortBy(){
	var name = this.id;
	var results = usersContainer.sort(function(first, second){
		
		//Witch col was selected 
		if(name == "sortByLastName"){
			if(first.lastName > second.lastName){
				return 1;
			}else{
				return -1;
			}
		}
		if(name == "sortByFirstName"){
			if(first.firstName > second.firstName){
				return 1;
			}else{
				return -1;
			}
		}
		if(name == "sortByEmail"){
			if(first.emailAddress > second.emailAddress){
				return 1;
			}else{
				return -1;
			}
		}
		if(name == "sortByPhone"){
			if(first.phone > second.phone){
				return 1;
			}else{
				return -1;
			}
		}
		if(name == "sortByEducation"){
			if(first.education > second.education){
				return 1;
			}else{
				return -1;
			}
		}
		if(name == "sortByEnglishLevel"){
			if(first.englishLevel > second.englishLevel){
				return 1;
			}else{
				return -1;
			}
		}
		if(name == "sortByAge"){
			if(first.age > second.age){
				return 1;
			}else{
				return -1;
			}
		}
	});
	
	//Draw the arranged table
	var tableSortRows = "";
	for(var k in usersContainer){
		tableSortRows += "<tr><td>" +
			results[k].lastName + "</td><td>" +
			results[k].firstName + "</td><td>" +
			results[k].emailAddress + "</td><td>" +
			results[k].phone + "</td><td>" +
			results[k].education + "</td><td>" +
			results[k].englishLevel + "</td><td>" +
			results[k].age + "</td><td style='text-align:center'>" +
			results[k].newsLetter + "</td></tr>";
	}
	document.querySelector(".resultTableRows").innerHTML = tableSortRows;
}