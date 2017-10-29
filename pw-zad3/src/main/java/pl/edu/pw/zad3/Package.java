package pl.edu.pw.zad3;

public class Package {

    private Portion[] portions;

    Package(Portion[] portions) {
        this.portions = portions;
    }

    public String toString() {
        String elem = portions[0].toString();
        for (int i = 1; i < portions.length; i++) {
            elem += ", " + portions[i];
        }
        return ("Package of " + portions.length + " elements: " + elem);
    }

    public int getSize() {
        return portions.length;
    }
}
