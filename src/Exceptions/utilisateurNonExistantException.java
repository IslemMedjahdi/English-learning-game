package Exceptions;

public class utilisateurNonExistantException extends Exception{

    public utilisateurNonExistantException(){
        super("utilisateur non existant");
    }
}
