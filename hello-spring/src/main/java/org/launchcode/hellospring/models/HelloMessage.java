package org.launchcode.hellospring.models;

/**
 * Created by robin on 7/11/17.
 */
public class HelloMessage {

    public static String createMessage (String name, String language){
        String salutation;

        switch (language) {
            case "french":
                salutation = "Bonjour, ";
                break;
            case "spanish":
                salutation = "Hola, ";
                break;
            case "german":
                salutation = "Guten Tag, ";
                break;
            case "italian":
                salutation = "Ciao, ";
                break;
            case "hindi":
                salutation = "Namaste, ";
                break;
            default:
                salutation = "Hi, ";
        }

        return salutation + name + "!";
    }
}