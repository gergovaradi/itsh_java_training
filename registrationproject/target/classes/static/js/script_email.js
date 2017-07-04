function saveEmail(){
    if(checkEmail()){
        addEmail();
    }else{
        alert("Helytelen email cím!");
    }
}

function checkEmail(){
	var email = document.querySelector("#email").value;
	if(email == ""){
	    return false;
	}
	var regex = /^([a-záéiíóöőúüűÁÉÍÓÖŐÚÜŰA-Z0-9_.-])+@([a-záéiíóöőúüűÁÉÍÓÖŐÚÜŰA-Z0-9_.-])+\.([a-záéiíóöőúüűÁÉÍÓÖŐÚÜŰA-Z])+([a-záéiíóöőúüűÁÉÍÓÖŐÚÜŰA-Z])+/;
	if(regex.test(email)){
		return true;
	}
	return false;
}

function addEmail(){
    var email = {
        "email" : document.querySelector("#email").value
    };
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'api/email');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.responseType = 'json';
    xhr.onreadystatechange = function () {
        var DONE = 4;
        var OK = 200;
        var DUPLICATE = 500;
        if (xhr.readyState === DONE) {
            if (xhr.status === OK) {
                alert("Köszönjük, hamarosan küldjük az információkat!");
            } else if(xhr.status === DUPLICATE){
                alert("Az email cím már jelen van a listán!");
            } else {
                console.log('Error: ' + xhr.status);
            }
        }
    };
    xhr.send(JSON.stringify(email));
    return false;
}