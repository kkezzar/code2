
function overlay() {
	el = document.getElementById("dialogBox");
	el.style.visibility = (el.style.visibility == "visible") ? "hidden" : "visible";
	$("dialogBox").load();
}

