package pl.edu.pw.zad3;

import java.util.Arrays;

public class Package {

    private Portion[] portions;

    public Package(final Portion[] portions) {
        this.portions = portions;
    }

    public String toString() {
        return Arrays.toString(portions);
    }

    public int getSize() {
        return portions.length;
    }
}
