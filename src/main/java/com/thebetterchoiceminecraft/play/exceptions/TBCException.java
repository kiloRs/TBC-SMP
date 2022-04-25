package com.thebetterchoiceminecraft.play.exceptions;

public abstract class TBCException extends RuntimeException{

    public TBCException(String  rep){
        super((rep));
        debug(rep);
    }

    public abstract void debug(String report);
}
