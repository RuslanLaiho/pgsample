package com.example.pgsample.models;

public class Rooster {
    public int roostersId;
    public String name;
    public String describleRooster;

    public String toString() {
        return roostersId + ", " + name + ", " + describleRooster;
    }

    Rooster (int roostersId, String name, String describaleRooster)
    {
        this.roostersId = roostersId;
        this.name = name;
        this.describleRooster = describaleRooster;
    }

    public Rooster() {}
}
