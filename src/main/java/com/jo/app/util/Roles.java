package com.jo.app.util;

public enum Roles {
    ORGANISATEUR("ROLE_ORGANISATEUR"),
    CONTROLEUR("ROLE_CONTROLEUR"),
    PARTICIPANT("ROLE_PARTICIPANT"),
    SPECTATEUR("ROLE_SPECTATEUR");

    private String libelle;

    Roles(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
