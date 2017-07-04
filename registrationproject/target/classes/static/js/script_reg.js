//Registration submit
function sendToServer(){
	if(!hasEmptyField()){
	    if(checkEmailReg()){
	        if(checkPhone()){
	            addUser();
	        }else{
	            alert("Helytelen telefonszám!");
	        }
	    }else{
	        alert("Helytelen email cím!");
	    }
    }else{
        alert("A *-al megjelölt mezők kitöltése kötelező!");
    }
}

function addUser() {
    //Check NewsLetter input
    var status = "off";
    if(document.querySelector(".newsLetter").checked){
        status = "on";
    }

    //Create JSON
    var user = {
    	"userName" : document.querySelector(".userName").value,
    	"password" : document.querySelector(".password").value,
    	"firstName" : document.querySelector(".firstName").value,
        "lastName" : document.querySelector(".lastName").value,
        "emailAddress" : document.querySelector(".emailAddress").value + "@" + document.querySelector(".emailDomain").value,
    	"phone" : document.querySelector(".phone").value,
    	"education" : document.querySelector(".education").value,
    	"englishLevel" : document.querySelector(".englishLevel").value,
    	"age" : document.querySelector(".age").value,
    	"newsLetter" : status
    };

    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'api/registration');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.responseType = 'json';
    xhr.onreadystatechange = function () {
        var DONE = 4;
        var OK = 200;
        var DUPLICATE = 500;
        if (xhr.readyState === DONE) {
            if (xhr.status === OK) {
                correctInputStylePage();
            } else if(xhr.status === DUPLICATE){
                alert("Felhasználónév már foglalt!");
            } else {
                console.log('Error: ' + xhr.status);
            }
        }
    };
    xhr.send(JSON.stringify(user));
    return false;
}

//Validate fields
function hasEmptyField(){
	var arrayOfDatas =[
		document.querySelector(".userName").value,
        document.querySelector(".password").value,
        document.querySelector(".firstName").value,
        document.querySelector(".lastName").value,
        document.querySelector(".emailAddress").value,
        document.querySelector(".emailDomain").value,
        document.querySelector(".education").value,
        document.querySelector(".englishLevel").value,
        document.querySelector(".age").value
	];
	for(var i = 0; i <arrayOfDatas.length; i++){
		if(arrayOfDatas[i] == ""){
			return true;
		}
	}
	return false;
}

function checkEmailReg(){
	var emailAddress = document.querySelector(".emailAddress").value + "@" + document.querySelector(".emailDomain").value;
	var regex = /^([a-záéiíóöőúüűÁÉÍÓÖŐÚÜŰA-Z0-9_.-])+@([a-záéiíóöőúüűÁÉÍÓÖŐÚÜŰA-Z0-9_.-])+\.([a-záéiíóöőúüűÁÉÍÓÖŐÚÜŰA-Z])+([a-záéiíóöőúüűÁÉÍÓÖŐÚÜŰA-Z])+/;
	if(regex.test(emailAddress)){
		return true;
	}
	return false;
}

function checkPhone(){
    var phone = document.querySelector(".phone").value;
    if(phone == ""){
        return true;
    }
    var regex = /^(\+)+36+[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]+/;
    if(regex.test(phone)){
      	return true;
    }
   	return false;
}

//Correct input page design
function correctInputStylePage(){
    document.querySelector("#submitBtn").style.display = "none";

    document.querySelector(".userName").readOnly=true;
    document.querySelector(".password").readOnly = true;
    document.querySelector(".firstName").readOnly = true;
    document.querySelector(".lastName").readOnly = true;
    document.querySelector(".emailAddress").readOnly = true;
    document.querySelector(".emailDomain").readOnly = true;
    document.querySelector(".phone").readOnly = true;

    document.querySelector(".education").disabled = true;
    document.querySelector(".englishLevel").disabled = true;
    document.querySelector(".age").disabled = true;
    document.querySelector(".newsLetter").disabled = true;

    document.querySelector("#message").style.display = "block";
}