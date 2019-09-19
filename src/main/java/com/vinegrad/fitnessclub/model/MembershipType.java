package com.vinegrad.fitnessclub.model;

public enum MembershipType {

    GOLD("Gold", 28), SILVER("Silver", 14), BRONZE("Bronze", 7);

    String display;
    int duration;

    public String getDisplay() {
        return display;
    }

    public int getDuration() {
        return duration;
    }

    MembershipType(String display, int duration) {
        this.display = display;
        this.duration = duration;
    }

}
