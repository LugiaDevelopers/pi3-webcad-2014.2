function validaIndex() {
	var regexEmail = /^([0-9a-zA-Z]+([_.-]?[0-9a-zA-Z]+)*@[0-9a-zA-Z]+[0-9,a-z,A-Z,.,-]*(.){1}[a-zA-Z]{2,4})+$/;

	var email = form1.imputMatricula.value;

	if (regexEmail.test(email) == false) {
		alert("Email inválido!");

	}

	var senha = form1.imputSenha.value;

	if (senha == "") {
		alert("Senha inválida!");
	}

}

function validaAdministrador() {
	var regexNome = /^[a-zA-ZéúíóáÉÚÍÓÁèùìòàçÇÈÙÌÒÀõãñÕÃÑêûîôâÊÛÎÔÂëÿüïöäËYÜÏÖÄ\-\ \s]+$/;
	var nome = formAdm.nomeAdm.value;

	if (regexNome.test(nome) == false) {
		alert("Nome vazio ou inválido!");
		

	}
	
	var regexMatricula = /^\d*[0-9](\d*[0-9])?$/;
	var matricula = formAdm.matriculaAdm.value;
	
	if (regexMatricula.test(matricula) == false) {
		alert("Matrícula vazia ou inválida!");

	}
	
	var regexEmailMatricula = /^([0-9a-zA-Z]+([_.-]?[0-9a-zA-Z]+)*@[0-9a-zA-Z]+[0-9,a-z,A-Z,.,-]*(.){1}[a-zA-Z]{2,4})+$/;

	var emailadm = formAdm.emailAdm.value;

	if (regexEmailMatricula.test(emailadm) == false) {
		alert("Email inválido!");

	}
	
	var senhaAdm = formAdm.senhaAdm.value;

	if (senhaAdm == "") {
		alert("Senha inválida!");
	}
	
	
	
	
	
	function validaProfessor() {
		var regexNomeProfessor = /^[a-zA-ZéúíóáÉÚÍÓÁèùìòàçÇÈÙÌÒÀõãñÕÃÑêûîôâÊÛÎÔÂëÿüïöäËYÜÏÖÄ\-\ \s]+$/;
		var nomeProf = formProf.nomeProfessor.value;

		if (regexNomeProfessor.test(nomeProf) == false) {
			alert("Nome vazio ou inválido!");
			

		}
		
		var regexMatriculaProfessor = /^\d*[0-9](\d*[0-9])?$/;
		var matricula = formProf.matriculaProfessor.value;
		
		if (regexMatriculaProfessor.test(matricula) == false) {
			alert("Matrícula vazia ou inválida!");

		}
		
		var regexEmailProfessor = /^([0-9a-zA-Z]+([_.-]?[0-9a-zA-Z]+)*@[0-9a-zA-Z]+[0-9,a-z,A-Z,.,-]*(.){1}[a-zA-Z]{2,4})+$/;

		var emailProf = formProf.emailProfessor.value;

		if (regexEmailProfessor.test(emailProf) == false) {
			alert("Email inválido!");

		}
		
		var senhaProf = formProf.senhaProfessor.value;

		if (senhaAdm == "") {
			alert("Senha inválida!");
		}
}
}