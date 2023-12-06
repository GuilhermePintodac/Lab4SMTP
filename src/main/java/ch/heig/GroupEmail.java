package ch.heig;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GroupEmail {
    private final Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private final String expediteur;
    private final List<String> destinataires;

    public GroupEmail(String expediteur, List<String> destinataires) throws RuntimeException {
        if (!verifAdresseEmail(expediteur)) {
            throw new RuntimeException("Erreur l'adresse email de l'expéditeur n'est pas valide");
        }
        this.expediteur = expediteur;

        for (String adresseMail : destinataires) {
            if (!verifAdresseEmail(adresseMail)) {
                throw new RuntimeException("Erreur l'adresse email du destinataire n'est pas valide");
            }
        }
        this.destinataires = new ArrayList<>(destinataires);
    }

    public String getExpediteur() {
        return expediteur;
    }

    public List<String> getDestinataires() {
        return destinataires;
    }

    /**
     * @brief Vérifie si une adresse e-mail est valide.
     * @param adresseMail Adresse e-mail à vérifier.
     * @return True si l'adresse e-mail est valide, sinon False.
     */
    public boolean verifAdresseEmail(String adresseMail) {
        Matcher matcher = pattern.matcher(adresseMail);
        return matcher.find();
    }

}
