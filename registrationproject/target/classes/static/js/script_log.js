//Registration submit
function sendToServer(){
	if(!hasEmptyField()){
	    checkLogin();
    }else{
        alert("A *-al megjelölt mezők kitöltése kötelező!");
    }
}

function checkLogin() {
    //Create JSON
    var user = {
    	"userName" : document.querySelector(".userName").value,
    	"password" : document.querySelector(".password").value
    };

    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'api/login');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.responseType = 'json';
    xhr.onreadystatechange = function () {
        var DONE = 4;
        var OK = 200;
        if (xhr.readyState === DONE) {
            if (xhr.status === OK) {
                if(xhr.response){
                    correctInputStylePage();
                }else{
                    alert("Hibás felhasználónév vagy jelszó!");
                }
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
        document.querySelector(".password").value
	];
	for(var i = 0; i <arrayOfDatas.length; i++){
		if(arrayOfDatas[i] == ""){
			return true;
		}
	}
	return false;
}

//Correct input page design
function correctInputStylePage(){
    document.querySelector("#submitBtn").style.display = "none";

    document.querySelector(".userName").readOnly = true;
    document.querySelector(".password").readOnly = true;

    document.querySelector("#message").style.display = "block";
}