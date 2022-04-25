package com.thebetterchoiceminecraft.play.exceptions;

import com.thebetterchoiceminecraft.play.TBCPlugin;

/**
 * Thrown with invalid enchanting - essence exceptions!
 */
public class InvalidEssenceException extends TBCException{
    public InvalidEssenceException() {
        this("");
    }

    public InvalidEssenceException(String rep) {
        super(rep);
    }

    @Override
    public void debug(String report) {
        TBCPlugin.log(report);
    }
}
