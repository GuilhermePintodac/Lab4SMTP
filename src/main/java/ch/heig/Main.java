package ch.heig;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Main {

    final static String adresseServer = "localhost";
    final static int portSMTP = 1025;
    final static int nbGroup = 3;
    final static String messages = "messagesList.txt";
    final static String victimes = "victimsList.txt";

    public static void main(String[] args) {

        ConfigManager configManager = new ConfigManager(nbGroup);

        ArrayList<Message> messageList = new ArrayList<>(configManager.chargerMessages(new File(messages), StandardCharsets.UTF_8));
        ArrayList<GroupEmail> groupEmails = new ArrayList<>(configManager.chargerVictimes(new File(victimes), StandardCharsets.UTF_8));
        ArrayList<Email> emails = new ArrayList<>();
        for (int i = 0; i < nbGroup; i++) {
            emails.add(new Email(groupEmails.get(i), messageList.get(i % messageList.size())));
        }

        ClientSMTP clientSMTP = new ClientSMTP(adresseServer, portSMTP);
        for (Email email : emails) {
            clientSMTP.envoyerEmail(email);
        }

    }
}