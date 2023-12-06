package ch.heig;

import java.util.ArrayList;
import java.util.List;

public class Email {
    final private String expediteur;
    final private List<String> destinataires;
    final private String sujet;
    final private String corps;


    public Email(GroupEmail listEmail, Message message) {
        if (listEmail.getDestinataires().isEmpty()) {
            throw new RuntimeException("Erreur liste de destinataire est vide");
        }

        if (listEmail.getExpediteur().isEmpty()) {
            throw new RuntimeException("Erreur l'expéditeur est vide");
        }

        if (message.getSujet().isEmpty()) {
            throw new RuntimeException("Erreur le sujet est vide");
        }

        if (message.getCorps().isEmpty()) {
            throw new RuntimeException("Erreur le corps du message est vide");
        }
        this.expediteur = listEmail.getExpediteur();
        this.destinataires = new ArrayList<>(listEmail.getDestinataires());
        this.sujet = message.getSujet();
        this.corps = message.getCorps();
    }

    public String getExepediteur() {
        return expediteur;
    }

    public String getSujet() {
        return sujet;
    }

    public String getCorps() {
        return corps;
    }

    public List<String> getDestinataires() {
        return destinataires;
    }
}
