package ch.heig;

public class Message {
    private final String sujet;
    private final String corps;


    Message(String sujet, String corps){
        this.sujet = sujet;
        this.corps = corps;
    }

    public String getSujet(){
        return sujet;
    }

    public String getCorps(){
        return corps;
    }
}
