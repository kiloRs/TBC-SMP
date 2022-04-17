package fun.tbcraft.play.exceptions;

import org.jetbrains.annotations.Nullable;

public class InvalidWaypointException extends RuntimeException{
    private String errorMessage = "Invalid Waypoint!";
    public InvalidWaypointException(@Nullable String m){
        this.errorMessage = m==null?errorMessage:m;

    }

    @Override
    public synchronized Throwable getCause() {
        return new InvalidWaypointException(errorMessage);
    }

    @Override
    public String getMessage() {
        return "You have an invalid waypoint being used!";
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
