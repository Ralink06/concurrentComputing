package pl.edu.pw.zad3;

public class Package {

    private Portion[] portions;

    public Package(final Portion[] portions) {
        this.portions = portions;
    }

    public String toString() {
        StringBuilder elem = new StringBuilder(portions[0].toString());
        for (int i = 1; i < portions.length; i++) {
            elem.append(", ").append(portions[i]);
        }
        return ("Package of " + portions.length + " elements: " + elem);
    }

    public int getSize() {
        return portions.length;
    }
}
