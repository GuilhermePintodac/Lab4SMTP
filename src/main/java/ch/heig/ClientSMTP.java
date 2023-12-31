package ch.heig;

import java.io.*;
import java.net.Socket;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ClientSMTP {

    private final String adresseServer;
    private final int portServer;

    public ClientSMTP(String adresseServer, int portServer) {
        this.adresseServer = adresseServer;
        this.portServer = portServer;
    }

    /**
     * @param email L'objet Email contenant les informations sur l'e-mail à envoyer.
     * @return void
     * @throws IOException En cas d'erreur liée à l'entrée/sortie lors de la communication avec le serveur SMTP.
     * @brief Envoie un e-mail via le protocole SMTP.
     * Établit une connexion avec le serveur SMTP spécifié, envoie les commandes SMTP
     * nécessaires pour transférer l'e-mail, et ferme la connexion.
     * @details Cette fonction réalise les étapes suivantes :
     * - Établit une connexion avec le serveur SMTP.
     * - Envoie les commandes SMTP telles que EHLO, MAIL FROM, RCPT TO, DATA, etc.
     * - Construit le message MIME avec les informations de l'e-mail.
     * - Envoie le message au serveur SMTP.
     * - Termine la connexion avec la commande QUIT.
     * - Gère les erreurs d'entrée/sortie et les affiche en cas d'échec.
     */
    public void envoyerEmail(Email email) {
        System.out.println("Creating connection");

        try (Socket socket = new Socket(adresseServer, portServer);
             var in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream(), UTF_8));
             var out = new BufferedWriter(
                     new OutputStreamWriter(socket.getOutputStream(), UTF_8))) {

            String line = in.readLine();
            System.out.println(line);
            out.write("ehlo " + adresseServer + "\r\n");
            out.flush();
            in.readLine();

            do {
                line = in.readLine();
                System.out.println(line);
            } while (!line.startsWith("250 "));

            out.write("mail from:<" + email.getExepediteur() + ">\r\n");
            System.out.println("Expediteur:" + email.getExepediteur());
            out.flush();

            in.readLine();

            for (String destinataire : email.getDestinataires()) {
                out.write("rcpt to:<" + destinataire + ">\r\n");
                System.out.println("Destinataire:" + destinataire);
                out.flush();
                in.readLine();
            }

            out.write("data" + "\r\n");
            out.flush();
            in.readLine();

            out.write("Content-Type: text/plain; charset=utf-8" + "\r\n");
            out.write("From:" + email.getExepediteur() + "\r\n");

            out.write("To:" + email.getDestinataires().get(0));
            for (int i = 1; i < email.getDestinataires().size(); i++) {
                out.write(", " + email.getDestinataires().get(i));
            }
            out.write("\r\n");

            out.write("Subject: =?utf-8?B?" + Base64.getEncoder().encodeToString(email.getSujet().getBytes())
                    + "?=" + "\r\n");
            out.write("Suject: " + email.getSujet() + "\r\n");

            out.write("\r\n");
            out.flush();
            out.write(email.getCorps() + "\r\n");
            out.write(".\r\n");
            out.flush();

            in.readLine();

            out.write("quit");
            out.flush();


        } catch (IOException e) {
            System.err.println("Error connection: " + e.getMessage());
        }
        System.out.println("Finished");
    }

}