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

// -----------------------------------------------------------------------------








$(document).ready(function() {
	$('.table .paireEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(modele, status) {
			$('.formEdit #id_paire').val(modele[0].idPaire);
			$('.formEdit #word_1').val(modele[0].word1);
			$('.formEdit #word_2').val(modele[0].word2);
			$('.formEdit #catego').val(modele[1].idCategorie);
		});
		$('.formEdit #modal-primary').modal();
	});
});

$(document).ready(function() {
	$('.table .paireEvaluer').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(modele, status) {
			$('.formEvaluer #paire').val(modele[0].idPaire);
		});
		$('.formEvaluer #modal-primary').modal();
	});
});


/**
 * Edit Achat
 * */


$(document).ready(function() {
	$('.table .achatEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(modele, status) {
			$('.formEdit #id_achat').val(modele[0].id);
			$('.formEdit #date_achat').val(modele[0].dateAchat);
			$('.formEdit #desc').val(modele[0].description);
			$('.formEdit #agent').val(modele[1]);
			$('.formEdit #sapolin').val(modele[0].sapolin);
			$('.formEdit #lessiv').val(modele[0].lessive);
			$('.formEdit #papier_toillete').val(modele[0].papierToillette);
			$('.formEdit #sac_poub').val(modele[0].sacPoubelle);
			$('.formEdit #sac_cong').val(modele[0].sacCongelation);
			$('.formEdit #qte').val(modele[0].quantite);
			$('.formEdit #autres').val(modele[0].autres);
		});
		$('.formEdit #modal-primary').modal();
	});
});




/**
 * 

// MODELE
$(document).ready(function() {
	$('.table .modalEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(modele, status) {
			$('.formEdit #codemodel').val(modele.codemodel);
			$('.formEdit #libellemodel').val(modele.libellemodel);
			$('.formEdit #desciption').val(modele.desciption);
		});
		$('.formEdit #modalEDIT').modal();
	});
});

// PANNE
$(document).ready(function() {
	$('.table .panneEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(data, status) {
			$('.formEdit #codepanne').val(data[0].codepanne);
			$('.formEdit #produit').val(data[1].codeproduit);
			$('.formEdit #typepanne').val(data[0].typepanne);
			$('.formEdit #datepanne').val(data[0].datepanne);
		});
		$('.formEdit #panneEDIT').modal();
	});
});

// REPARATION
$(document).ready(function() {
	$('.table .reparationEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(reparation, status) {
			console.log(panne);
			$('.formEdit #codereparation').val(reparation.codereparation);
			$('.formEdit #panne').val(reparation.panne);
			$('.formEdit #action').val(reparation.action);
		});
		$('.formEdit #reparationEDIT').modal();
	});
});

// MARQUE
$(document).ready(function() {
	$('.table .marqueEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(marque, status) {
			console.log(marque);
			$('.formEdit #codemarque').val(marque.codemarque);
			$('.formEdit #libellemarque').val(marque.libellemarque);
		});
		$('.formEdit #marqueEDIT').modal();
	});
});

// --------TypeDemande
$(document).ready(function() {
	$('.table .typedemandeEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(type, status) {
			$('.formEdit #codetypedemande').val(type.codetypedemande);
			$('.formEdit #libelle').val(type.libelle);
		});
		$('.formEdit #typedemandeEDIT').modal();
	});
});

// -----For Demande---
$(document).ready(function() {
	$('.table .demandeEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(data, status) {
			console.log("testettsttetttetete"+data);
			$('.formEdit #codedemande').val(data[0].codedemande);
			$('.formEdit #description').val(data[0].description);
			$('.formEdit #utilisateur').val(data[1].codeuser);
			$('.formEdit #typedemande').val(data[2].codetypedemande);
			$('.formEdit #quantite').val(data[0].quantite);
			$('.formEdit #etat').val(data[0].etat);
			$('.formEdit #status').val(data[0].status);
			$('.formEdit #datedemande').val(data[0].datedemande);
		});
		$('.formEdit #demandeEDIT').modal();
	});
});
//-----For Deleguer demande---
$(document).ready(function() {
	$('.table .delegueEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(data, status) {
			console.log(data);
			$('.formEdit #codedelegue').val(data[0].codedemande);
			$('.formEdit #demande').val(data[1].codedemande);
			$('.formEdit #datedelegue').val(data[0].datedelegue);
			$('.formEdit #utilisateur').val(data[2].codeuser);		
		});
		$('.formEdit #delegueEDIT').modal();
	});
});
// -------For Departement-----
$(document).ready(
		function() {
			$('.table .departementEdit').on(
					'click',
					function(event) {
						event.preventDefault();
						var href = $(this).attr('href');
						$.get(href, function(departement, status) {
							$('.formEdit #codedepartement').val(
									departement.codedepartement);
							$('.formEdit #libelledepartement').val(
									departement.libelledepartement);
							// $('.formEdit
							// #desciption').val(modele.desciption);
						});
						$('.formEdit #departementEDIT').modal();
					});
		});

// for demande
$(document).ready(function() {
	$('.table .detailEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(data, status) {
			console.log(data);
			$('.formEdit #codetypedemande').val(data[0].codedetaildemande);
			$('.formEdit #produit').val(data[1]);
			$('.formEdit #demande').val(data[2]);
		});
		$('.formEdit #detailEDIT').modal();
	});
});
// ---------Stock
$(document).ready(function() {
	$('.table .stockEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(data, status) {
			console.log(data);
			$('.formEdit #codestock').val(data[0].codestock);
			$('.formEdit #produit').val(data[1]);
			$('.formEdit #quantite').val(data[0]);
			$('.formEdit #lieu').val(data[0].lieu);
		});
		$('.formEdit #stockEDIT').modal();
	});
});

// ----categorieProd
$(document).ready(
		function() {
			$('.table .categorieEdit').on(
					'click',
					function(event) {
						event.preventDefault();
						var href = $(this).attr('href');
						$.get(href, function(categorieprod, status) {
							$('.formEdit #codecategorie').val(
									categorieprod.codecategorie);
							$('.formEdit #libellecategorie').val(
									categorieprod.libellecategorie);
						});
						$('.formEdit #categorieEDIT').modal();
					});
		});
// -----acquisition
/*$(document).ready(function() {
	$('.table .acquisitionEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(acquisition, status) {
			console.log(acquisition);
			$('.myForm #refacquisition').val(acquisition.refacquisition);
			$('.myForm #description').val(acquisition.description);
			$('.myForm #dateacquisition').val(acquisition.dateacquisition);
			$('.myForm #dateexpirationgarantie').val(acquisition.dateexpirationgarantie);
			
		});
		$('.myForm #modal-primary').modal();
	});
	
});


//------Mes config

//-----acquisition
$(document).ready(function() {
	$('.table .acquisitionEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(data, status) {
			console.log(data);
			$('.myForm #refacquisition').val(data[0].refacquisition);
			$('.myForm #description').val(data[0].description);
			$('.myForm #dateacquisition').val(data[0].dateacquisition);
			$('.myForm #dateexpirationgarantie').val(data[0].dateexpirationgarantie);
			$('.myForm #structure').val(data[1]);
			
		});
		$('.myForm #modal-primary').modal();
	});
	
});

//----- delete aacquisition
$(document).ready(function(){
	$('.table .del').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$('#MyModal #delRef').attr('href',href);
		$('#MyModal').modal();
	});
});

//-----role
$(document).ready(function() {
	$('.table .roleEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(role, status) {
			console.log(role);
			$('.myForm #libellerole').val(role.libellerole);
			$('.myForm #descriptionrole').val(role.descriptionrole);	
		});
		$('.myForm #modal-primary').modal();
	});
	
});

//-----CHEKLIST
$(document).ready(function() {
	$('.table .checklistEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(checklist, status) {
			console.log(checklist);
			$('.myForm #idchecked').val(checklist.idchecked);
			$('.myForm #etat').val(checklist.etat);
			$('.myForm #commentaire').val(checklist.commentaire);	
		});
		$('.myForm #modal-primary').modal();
	});
	
});
//-----structure
$(document).ready(function() {
	$('.table .structureEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(structure, status) {
			console.log(structure);
			$('.myForm #idstructure').val(structure.idstructure);
			$('.myForm #nomstructure').val(structure.nomstructure);	
		});
		$('.myForm #modal-primary').modal();
	});
	
});
//-----marque
$(document).ready(function() {
	$('.table .marqueEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(marque, status) {
			console.log(marque);
			$('.myForm #refmarque').val(marque.refmarque);
			$('.myForm #libellemarque').val(marque.libellemarque);	
		});
		$('.myForm #modal-primary').modal();
	});
	
});

//-----view details acquisition
$(document).ready(function() {
	$('.table .detailsAcquisitionEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(data, status) {
			console.log(data);
			$('.myForm #idacquisition').val(data[0].idacquisition);
			$('.myForm #sinitial').val(data[0].stockinitial);
			$('.myForm #scourant').val(data[0].stockcourant);
			$('.myForm #daterenouvellement').val(data[0].daterenouvellement);
			$('.myForm #prixunitaire').val(data[0].prixunitaire);
			$('.myForm #statut').val(data[0].statut);
			$('.myForm #emplacement').val(data[0].emplacement);
			$('.myForm #produit').val(data[1]);
			$('.myForm #acquisition').val(data[2]);
			$('.myForm #fournisseur').val(data[3]);
		});
		$('.myForm #modal-success').modal();
	});
	
});

//-----Fiche Affectation
$(document).ready(function() {
	$('.table .ficheEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(data, status) {
			console.log(data);
			$('.myForm #idficheaffectation').val(data[0].idficheaffectation);
			$('.myForm #numeroserie').val(data[0].numeroserie);
			$('.myForm #numeromodele').val(data[0].numeromodele);
			$('.myForm #refaffectation').val(data[0].refaffectation);
			$('.myForm #datecloture').val(data[0].datecloture);
			$('.myForm #statut').val(data[0].statut);
			$('.myForm #checklist').val(data[1]);
			$('.myForm #detailsdemande').val(data[2]);
		});
		$('.myForm #modal-primary').modal();
	});
	
});


//-----CategorieProduit
$(document).ready(function() {
	$('.table .categorieEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(categorie, status) {
			console.log(categorie);
			$('.myForm #idcategorie').val(categorie.idcategorie);
			$('.myForm #libellecategorie').val(categorie.libellecategorie);	
		});
		$('.myForm #modal-primary').modal();
	});
	
});

//-----CategorieProduit
$(document).ready(function() {
	$('.table .qualificationEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(qualification, status) {
			console.log(qualification);
			$('.myForm #idqualification').val(qualification.idqualification);
			$('.myForm #libellequalification').val(qualification.libellequalification);	
		});
		$('.myForm #modal-primary').modal();
	});
	
});

//-----Compte
$(document).ready(function() {
	$('.table .compteEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(dataset, status) {
			console.log(dataset);
			$('.myForm #log').val(dataset[0].login);
			$('.myForm #pass').val(dataset[0].password);
			$('.myForm #active').val(dataset[0].active);
			$('.myForm #agents').val(dataset[1]);
		});
		$('.myForm #modal-primary').modal();
	});
	
});

//-----Demande
$(document).ready(function() {
	$('.table .demandeEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(dataset, status) {
			console.log(dataset);
			$('.myForm #iddemande').val(dataset[0].iddemande);
			$('.myForm #compte').val(dataset[1]);	
			$('.myForm #description').val(dataset[0].description);
			$('.myForm #statut').val(dataset[0].statut);
			$('.myForm #datedemande').val(dataset[0].datedemande);
			$('.myForm #datecloture').val(dataset[0].datecloture);
		});
		$('.myForm #modal-primary').modal();
	});
	
});

//-----Details Demande
$(document).ready(function() {
	$('.table .detailsEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(data, status) {
			console.log(data);
			$('.myForm #iddetailsdem').val(data[0].iddetailsdem);
			$('.myForm #naturedemande').val(data[0].naturedemande);
			$('.myForm #statut').val(data[0].statut);
			$('.myForm #datereception').val(data[0].datereception);
			$('.myForm #dateattribution').val(data[0].dateattribution);
			$('.myForm #quantiteapprouve').val(data[0].quantiteapprouve);
			$('.myForm #motivation').val(data[0].motivation);
			$('.myForm #decision').val(data[0].decision);
			$('.myForm #fonction').val(data[0].fonction);
			$('.myForm #agents').val(data[1]);
			$('.myForm #demande').val(data[2]);
			$('.myForm #stockacquisitions').val(data[3]);
			$('.myForm #qualification').val(data[4]);
			
		});
		$('.myForm #modal-primary').modal();
	});
	
});

//-----Details Demande qualification
$(document).ready(function() {
	$('.table .detailsQualEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(data, status) {
			console.log(data);
			$('.mForm #iddetailsdem').val(data[0].iddetailsdem);
			$('.mForm #naturedemande').val(data[0].naturedemande);
			$('.mForm #statut').val(data[0].statut);
			$('.mForm #datereception').val(data[0].datereception);
			$('.mForm #dateattribution').val(data[0].dateattribution);
			$('.mForm #quantiteapprouve').val(data[0].quantiteapprouve);
			$('.mForm #motivation').val(data[0].motivation);
			$('.mForm #decision').val(data[0].decision);
			$('.mForm #fonction').val(data[0].fonction);
			$('.mForm #agents').val(data[1]);
			$('.mForm #demande').val(data[2]);
			$('.mForm #stockacquisitions').val(data[3]);
			$('.mForm #qualification').val(data[4]);
			
		});
		$('.mForm #modal-primary').modal();
	});
	
});
//-----produit
$(document).ready(function() {
	$('.table .produitEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(data, status) {
			console.log(data);
			$('.myForm #refprod').val(data[0].refprod);
			$('.myForm #categorie').val(data[1]);
			$('.myForm #libelleprod').val(data[0].libelleprod);
			$('.myForm #marque').val(data[2]);
		
			
		});
		$('.myForm #modal-primary').modal();
	});
	
});

//-----agents
$(document).ready(function() {
	$('.table .agentEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(data, status) {
			console.log(data);
			$('.myForm #numero').val(data[0].numero);
			$('.myForm #nom').val(data[0].nom);
			$('.myForm #prenom').val(data[0].prenom);
			$('.myForm #sexe').val(data[0].sexe);
			$('.myForm #datenaissance').val(data[0].datenaissance);
			$('.myForm #matrimoniale').val(data[0].matrimoniale);
			$('.myForm #profil').val(data[0].profil);
			$('.myForm #email').val(data[0].email);
			$('.myForm #tel').val(data[0].tel);
			$('.myForm #structure').val(data[1]);
			
		});
		$('.myForm #modal-primary').modal();
	});
	
});



// --------affectation
$(document).ready(function() {
	$('.table .affectationEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(data, status) {
			$('.formEdit #codeaffectation').val(data[0].codeaffectation);
			$('.formEdit #produit').val(data[1]);
			$('.formEdit #dateaff').val(data[0].dateaff);
			$('.formEdit #service').val(data[0].service);
		});
		$('.formEdit #affectationEDIT').modal();
	});
});

// -----commande
/*$(document).ready(function() {
	$('.table .commandeEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(modele, status) {
			$('.formEdit #codemodel').val(modele.codemodel);
			$('.formEdit #libellemodel').val(modele.libellemodel);
			$('.formEdit #desciption').val(modele.desciption);
		});
		$('.formEdit #commandeEDIT').modal();
	});
});
// ------- fournisseur
$(document).ready(function() {
	$('.table .fournisseurEDIT').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(fournisseur, status) {
			$('.formEdit #codefournisseur').val(fournisseur.codefournisseur);
			$('.formEdit #email').val(fournisseur.email);
			$('.formEdit #libelle').val(fournisseur.libelle);
			$('.formEdit #codepostal').val(fournisseur.codepostal);
			$('.formEdit #telephone').val(fournisseur.telephone);
			$('.formEdit #adresse').val(fournisseur.adresse);
			$('.formEdit #ville').val(fournisseur.ville);
			$('.formEdit #pays').val(fournisseur.pays);
		});
		$('.formEdit #fournisseurEDIT').modal();
	});
});
// -----------------produit--------------
$(document).ready(function() {
	$('.table .produitEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(data, status) {
			$('.formEdit #codeproduit').val(data[0].codeproduit);
			$('.formEdit #libelleprod').val(data[0].libelleprod);
			$('.formEdit #quantite').val(data[0].quantite);
			$('.formEdit #prixunitaire').val(data[0].prixunitaire);
			$('.formEdit #numSerie').val(data[0].numSerie);
			$('.formEdit #fournisseur').val(data[1]);
			$('.formEdit #marque').val(data[2]);
			$('.formEdit #modele').val(data[3]);
			$('.formEdit #categorieprod').val(data[4]);
		});
		$('.formEdit #produitEDIT').modal();
	});
});

// --------Intervention------------------
$(document).ready(function() {
	$('.table .interventionEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(data, status) {
			$('.formEdit #codentervention').val(data[0].codentervention);
			$('.formEdit #intervenant').val(data[0].intervenant);
			$('.formEdit #dateintervention').val(data[0].dateintervention);
			$('.formEdit #description').val(data[0].description);
			$('.formEdit #typeintervention').val(data[0].typeintervention);
			$('.formEdit #demande').val(data[1]);
		});
		$('.formEdit #interventionEDIT').modal();
	});
});

// --------Lignecommande------------------
$(document).ready(function() {
	$('.table .lignecommandeEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(data, status) {
			$('.formEdit #codelignecom').val(data[0].codelignecom);
			$('.formEdit #produit').val(data[1]);
			$('.formEdit #commande').val(data[2]);
		});
		$('.formEdit #lignecommandeEDIT').modal();
	});
});

//-------utilisateur-----------------
$(document).ready(function() {
	$('.table .userEDIT').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(data, status) {
			//$('.formEdit #codeuser').val(data[0].codeuser);
			$('.formEdit #noms').val(data[0].nom);
			$('.formEdit #prenoms').val(data[0].prenom);
			$('.formEdit #telephones').val(data[0].telephone);
			$('.formEdit #adresses').val(data[0].adresse);
			$('.formEdit #logins').val(data[0].login);
			$('.formEdit #passwords').val(data[0].password);
			$('.formEdit #specialites').val(data[0].specialite);
			$('.formEdit #actifs').val(data[0].actif);
			$('.formEdit #departements').val(data[1]);
		});
		$('.formEdit #userEDIT').modal();
	});
});

//--------Userrole------------------
$(document).ready(function() {
	$('.table .userroleEdit').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(data, status) {
			$('.formEdit #codeusersroles').val(data[0].codeusersroles);
			$('.formEdit #role').val(data[1]);
			$('.formEdit #utilisateur').val(data[2]);
		});
		$('.formEdit #userroleEDIT').modal();
	});
});
 */