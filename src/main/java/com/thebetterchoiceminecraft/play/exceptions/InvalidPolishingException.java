package com.thebetterchoiceminecraft.play.exceptions;

import com.thebetterchoiceminecraft.play.TBCPlugin;

/**
 * Thrown with invalid polishing exceptions!
 */
public class InvalidPolishingException extends TBCException{

    public InvalidPolishingException(String rep) {
        super(rep);
    }

    @Override
    public void debug(String report) {
        try {
            TBCPlugin.debug(report);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
