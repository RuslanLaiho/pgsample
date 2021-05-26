package com.example.pgsample.models;

public class Rooster {
    public int roosters_id;
    public String name;
    public String describle_r;

    public String toString() {
        return roosters_id + ", " + name + ", " + describle_r;
    }

    Rooster (int roosters_id, String name, String describle_r) {
        this.roosters_id  = roosters_id;
        this.name = name;
        this.describle_r   = describle_r;
    }

    public Rooster() {}
}
