package com.jo.app.util;

public enum Role {
    ORGANISATEUR("ORGANISATEUR"),
    CONTROLEUR("CONTROLEUR"),
    PARTICIPANT("PARTICIPANT"),
    SPECTATEUR("SPECTATEUR");

    private String libelle;

    Role(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
