function tableRowClick() {
    window.location = this.id;
}

function deleteConfirmation() {
    var form = this;
    msg("Удалить запись?", [{
        'caption': "Да",
        'handler': function() {
            form.submit();
        }
    }, {
        'caption': "Нет",
        'handler': function() {}
    }]);
    return false;
}

function errorMsg(element, message) {
    msg("<span style='font-weigth: bold; color: red;'>"
          + "Ошибка!"
      + "</span><br>" + message, [{
        caption: "OK",
        handler: function() {element.focus()}
    }]);
}

function formValidateProject() {
    var form = this;
    var name = form["name"];
    
    var begining = form["begining"];
    var planed = form["planed"];
    var real = form["real"];
   
    
    if(name.value.length == 0) {   	
        errorMsg(name, "поле name должно быть заполнено");
        return false;
    }
        
    if(begining.value.length == 0) {
        errorMsg(begining, "поле Begining должно быть заполнено");
        return false;
    }   
    else if(!(new RegExp("^20[0-9]{2}[-][0-1][0-9][-][0-3][0-9]$").test(begining.value))){
    	errorMsg(begining, "поле begining должно быть заполнено 20гг-мм-дд");
        return false;
    }
    
    if(planed.value.length == 0) {
        errorMsg(planed, "поле planed должно быть заполнено");
        return false;
    }
    
    else if(!(new RegExp("^20[0-9]{2}[-][0-1][0-9][-][0-3][0-9]$").test(planed.value))){
    	errorMsg(planed, "поле planed должно быть заполнено 20гг-мм-дд");
        return false;
    }
    
    if(real.value.length != 0) {   	   
	    if(!(new RegExp("^20[0-9]{2}[-][0-1][0-9][-][0-3][0-9]$").test(real.value))){
	    	errorMsg(real, "поле real должно быть заполнено 20гг-мм-дд");
	        return false;
	    }
    }
    
    return true;
}

function formValidateClient() {
    var form = this;
    var name = form["name"];
    var adress = form["adress"];
   
    
    if(name.value.length == 0) {   	
        errorMsg(name, "поле name должно быть заполнено");
        return false;
    }
    
    if(adress.value.length == 0) {   	
        errorMsg(adress, "поле adress должно быть заполнено");
        return false;
    }
    return true;
}

function formValidateUser() {
    var form = this;
    
    var login = form["login"];
    var password = form["password"];
    
    
    if(login.value.length == 0) {
        errorMsg(login, "поле login должно быть заполнено");
        return false;
    }
    if(password.value.length == 0) {
        errorMsg(password, "поле password должно быть заполнено");
        return false;
    }
    
    return true;
}

window.onload = function() {
    /* обработка тех строк таблиц, у которых есть
     * атрибут id */
    var tables = document.getElementsByTagName("table");
    for(var i = 0; i < tables.length; i++) {
        var rows = tables[i].getElementsByTagName("tr");
        for(var j = 0; j < rows.length; j++) {
            if(rows[j].getAttribute("id") !== null) {
                rows[j].onclick = tableRowClick;
            }
        }
    }
    /* обработка формы удаления (формы, у которой
     * атрибут id равен 'del-form' */
    var deleteForm = document.getElementById("del-form");
    if(deleteForm !== null) {
        deleteForm.onsubmit = deleteConfirmation;
    }

    /* валидация формы редактирования (формы, у которой
     * атрибут id равен 'edit-form' */
    var editProject = document.getElementById("editProject");
    if(editProject !== null) {
        editProject.onsubmit = formValidateProject;
    }
    
    var editClient = document.getElementById("editClient");
    if(editClient !== null) {
    	editClient.onsubmit = formValidateClient;
    }
    
    var editUser = document.getElementById("editUser");
    if(editUser !== null) {
        editUser.onsubmit = formValidateUser;
    }

    
    var lists = document.getElementsByClassName("list-group");
	for(var i = 0; i < lists.length; i++) {
		if(lists[i].parentNode.tagName.toLowerCase() == "li") {
			lists[i].style.display = "none";
			var button = document.createElement("span");
			button.style = "cursor: pointer; font-family: monospace;";
			button.onclick = dropDownList;
			button.appendChild(document.createTextNode("[+] "));
			lists[i].parentNode.insertBefore(button, lists[i].parentNode.firstChild);
		}
	}
}

function msg(message, buttons) {
    var body = document.getElementsByTagName("body")[0];
    body.style.overflow = "hidden";
    var messageElement = document.createElement("div");
    var style = new String();
    style += "position: fixed;";
    style += "height: 100%;";
    style += "width: 100%;";
    style += "z-index: 1;";
    messageElement.style = style;
    var messageContent = document.createElement("div");
    style = new String();
    style += "float: left;";
    style += "display: inline;";
    style += "background: lightgrey;";
    style += "padding: 20px;";
    style += "border: 1px solid black;";
    messageContent.style = style;
    var messageText = document.createElement("p");
    messageText.innerHTML = message;
    messageText.style = "text-align: center;";
    messageContent.appendChild(messageText);
    var buttonsElement = document.createElement("p");
    buttonsElement.style = "text-align: center;";
    for(var i = 0; i < buttons.length; i++) {
        var button = document.createElement("button");
        if(i < buttons.length - 1) {
            button.style = "margin-right: 10px;";
        }
        button.type = "button";
        button.handler = buttons[i];
        button.onclick = function() {
            body.removeChild(messageElement);
            body.style.overflow = "auto";
            this.handler.handler();
        }
        var text = buttons[i].caption;
        var textNode = document.createTextNode(text);
        button.appendChild(textNode);
        buttonsElement.appendChild(button);
    }
    messageContent.appendChild(buttonsElement);
    messageElement.appendChild(messageContent);
    body.insertBefore(messageElement, body.firstChild);
}



function dropDownList() {
	var list = this.parentNode.getElementsByClassName("list-group")[0];
	if(list.style.display == "none") {
		list.style.display = "block";
		this.innerText = "[-] ";
	} else {
		list.style.display = "none";
		this.innerText = "[+] ";
	}
}

