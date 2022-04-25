package com.thebetterchoiceminecraft.play;

public class BasicWrapper <A> {

    private final A tech;
    private String s;
    private boolean locked = false;

    public BasicWrapper(A t) {
        this.tech = t;
        this.locked = false;
    }

    public A getTech(boolean ignoreLock) {
        if (ignoreLock){
            return tech;
        }
        return !isLocked() ? tech : null;
    }


    public boolean isLocked(){
        return locked;
    }
    public void lock() {
        this.locked = true;
    }

    public void unlock() {

        this.locked = false;
    }
}
