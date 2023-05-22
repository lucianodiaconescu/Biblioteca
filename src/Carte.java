import java.util.Date;

public class Carte {
    private String numeCarte;
    private String numeAutor;
    private String continutCarte;

    public Carte(String numeCarte, String numeAutor, String continutCarte) {
        this.numeCarte = numeCarte;
        this.numeAutor = numeAutor;
        this.continutCarte = continutCarte;
    }

    public String getNumeCarte() {
        return numeCarte;
    }

    public void setNumeCarte(String numeCarte) {
        this.numeCarte = numeCarte;
    }

    public String getNumeAutor() {
        return numeAutor;
    }

    public void setNumeAutor(String numeAutor) {
        this.numeAutor = numeAutor;
    }

    public String getContinutCarte() {
        return continutCarte;
    }

    public void setContinutCarte(String continutCarte) {
        this.continutCarte = continutCarte;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "numeCarte='" + numeCarte + '\'' +
                ", numeAutor='" + numeAutor + '\'' +
                ", continutCarte='" + continutCarte + '\'' +
                '}';
    }
}
