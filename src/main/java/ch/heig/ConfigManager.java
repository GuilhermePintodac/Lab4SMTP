package ch.heig;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class ConfigManager {

    private final int nbGroup;


    public ConfigManager(int nbGroup) {
        this.nbGroup = nbGroup;
    }

    /**
     * @param file     Le fichier à lire.
     * @param encoding L'encodage du fichier.
     * @return Une liste d'objets Message ou null en cas d'erreur.
     * @brief Charge les messages à partir d'un fichier.
     * <p>
     * Lit le contenu d'un fichier avec un encodage donné, extrait les messages
     * et les retourne sous forme d'une liste d'objets Message.
     */
    public List<Message> chargerMessages(File file, Charset encoding) {
        List<Message> messageList = new ArrayList<>();
        try (var reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding))) {
            StringBuilder resultString = new StringBuilder();
            String line;
            String sujet = "";

            while ((line = reader.readLine()) != null) {

                if (line.startsWith("Subject:")) {
                    sujet = line.substring(9);
                } else if (line.equals("--")) {
                    messageList.add(new Message(sujet, resultString.toString()));
                    resultString = new StringBuilder();
                } else {
                    resultString.append(line).append("\n");
                }
            }

            if (!sujet.isEmpty() && !resultString.isEmpty()) {
                messageList.add(new Message(sujet, resultString.toString()));
            }

            return messageList;

        } catch (IOException e) {
            System.out.println("Exception: " + e);
            return null;
        }
    }

    /**
     * @param file     Le fichier à lire.
     * @param encoding L'encodage du fichier.
     * @return Une liste d'objets GroupEmail ou null en cas d'erreur.
     * @brief Charge les adresses e-mail des victimes à partir d'un fichier.
     * <p>
     * Lit le contenu d'un fichier avec un encodage donné, mélange les adresses e-mail
     * et crée des groupes d'e-mails avec un expéditeur et un nombre aléatoire de destinataires.
     */
    public List<GroupEmail> chargerVictimes(File file, Charset encoding) {
        List<GroupEmail> groupesEmail = new ArrayList<>();
        List<String> adressesMail = new ArrayList<>();
        try (var reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding))) {
            String line;


            while ((line = reader.readLine()) != null) {
                adressesMail.add(line);
            }

            try {
                int totalVictimes = adressesMail.size() < 6 ? adressesMail.size() - 2 : 3;
                Random rand = new Random();
                for (int i = 0; i < nbGroup; i++) {
                    Collections.shuffle(adressesMail);
                    List<String> tmp = new ArrayList<>(adressesMail);
                    String expediteur = tmp.get(0);
                    tmp.remove(0);
                    int nbDestinataires = rand.nextInt(totalVictimes) + 2;
                    groupesEmail.add(new GroupEmail(expediteur, tmp.subList(0, nbDestinataires)));
                }

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }

            return groupesEmail;

        } catch (IOException e) {
            System.out.println("Exception: " + e);
            return null;
        }
    }
}
