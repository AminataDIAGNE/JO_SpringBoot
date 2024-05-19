package com.jo.app.util;

public enum Etat {
	EN_ATTENTE("En attente"),
    VALIDE("Valide"),
    INVALIDE("Invalide"),
    ANNULE("Annulé");

    private String libelle;

    Etat(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}

