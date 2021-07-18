function showPassive(){
	var x = document.getElementById("passiva");

	if(x.style.display == "none"){
		setAllNone();
		x.style.display = "block";
	} else {
		x.style.display = "none";
	}

}

function showQ(){
	var x = document.getElementById("q");

	if(x.style.display == "none"){
		setAllNone();
		x.style.display = "block";
	} else {
		x.style.display = "none";
	}
}

function showW(){
	var x = document.getElementById("w");

	if(x.style.display == "none"){
		setAllNone();
		x.style.display = "block";
	} else {
		x.style.display = "none";
	}	
}

function showE(){
	var x = document.getElementById("e");

	if(x.style.display == "none"){
		setAllNone();
		x.style.display = "block";
	} else {
		x.style.display = "none";
	}
}

function showR(){
	var x = document.getElementById("r");

	if(x.style.display == "none"){
		setAllNone();
		x.style.display = "block";
	} else {
		x.style.display = "none";
	}
}

function setAllNone(){
	document.getElementById("passiva").style.display = "none";
	document.getElementById("q").style.display = "none";
	document.getElementById("w").style.display = "none";
	document.getElementById("e").style.display = "none";
	document.getElementById("r").style.display = "none";
}