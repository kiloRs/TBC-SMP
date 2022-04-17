package fun.tbcraft.play.hotspot;

public interface BasicWaypoint {
    public String getID();

    public String getName();

    public double getRate();

    public boolean needsPermission();
}
