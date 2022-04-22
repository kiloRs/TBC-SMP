package fun.tbcraft.play.exceptions;

import fun.tbcraft.play.TBCPlugin;

public class InvalidTBCException extends RuntimeException{

    public InvalidTBCException(String id){
        super(id);

    }
    public void debug(String e){
        try {
            TBCPlugin.debug(e);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
