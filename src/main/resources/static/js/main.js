/**
 *  Fonction de suppression enregistrement 
 * */
$(document).ready(function(){
	$('.table .del').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$('#MyModal #delete').attr('href',href); // Mettre à jour l'attribut href avec l'URL de suppression
		$('#MyModal').modal();
	});
});

/**
 *  Suppression 
 * */
$(document).ready(function(){
	$('.box-profile .del').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$('#MyModal #delete').attr('href',href); 
		$('#MyModal').modal();
	});
});

/**
 * Edit delegation
 * */

$(document).ready(function() {
	$('.table .editDelegation').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(modele, status) {
			$('.formEdit #idDelegation').val(modele[0].id);
			$('.formEdit #nom').val(modele[0].nom);
			$('.formEdit #or').val(modele[0].nombreMedaillesOr);
			$('.formEdit #argent').val(modele[0].nombreMedaillesArgent);
			$('.formEdit #bronze').val(modele[0].nombreMedaillesBronze);
		});
		$('.formEdit #modal-primary').modal();
	});
});

function afficherListeComplete() {
	// Récupérer l'élément ul contenant la liste des participants
	var listeParticipants = document.getElementById("listeParticipants");

	// Afficher tous les éléments li de la liste
	var participants = listeParticipants.getElementsByTagName("li");
	for (var i = 0; i < participants.length; i++) {
		participants[i].style.display = "block";
	}

	// Cacher le lien "Tous les participants"
	document.getElementById("lienTousParticipants").style.display = "none";
}

/**
 * Edit épreuve
 * */
$(document).ready(function() {
	$('.table .editEpreuve').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(modele, status) {
			$('.formEdit #idEpreuve').val(modele[0].id);
			$('.formEdit #nom').val(modele[0].nom);
			$('.formEdit #nbPlace').val(modele[0].nombrePlacesMisesEnVente);
			$('.formEdit #reservationtime').val(modele[0].date);
			$('.formEdit #infras').val(modele[1].nom);
		});
		$('.formEdit #modal-primary').modal();
	});
});

/**
 * Edit User
 * */
$(document).ready(function() {
	$('.table .editUser').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(modele, status) {
			$('.formEdit #idUser').val(modele[0].id);
			$('.formEdit #prenom').val(modele[0].name.split(' ')[0]);
			$('.formEdit #nom').val(modele[0].name.split(' ')[1]);
			$('.formEdit #role').val(modele[0].roles);
			$('.formEdit #email').val(modele[0].email);
			$('.formEdit #pwd').val(modele[0].password);
			$('.formEdit #retypePwd').val(modele[0].password);
		});
		$('.formEdit #modal-primary').modal();
	});
});

/**
 * Edit Role
 * */
$(document).ready(function() {
	$('.table .editRole').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(modele, status) {
			$('.formEdit #idRole').val(modele[0].id);
			$('.formEdit #name').val(modele[0].name);
		});
		$('.formEdit #modal-primary').modal();
	});
});

// -----------------------------------------------------------------------------




