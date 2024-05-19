package com.jo.app.util;

public enum Role {
    ORGANISATEUR("Organisateur"),
    CONTROLEUR("Controleur");

    private String libelle;

    Role(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
