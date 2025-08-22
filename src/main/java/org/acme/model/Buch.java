package org.acme.model;

public class Buch {
    // Variablen 
    private int id;
    private String titel;

    // Standartkonstruktor um eine Instanz erstellen zu können
    public Buch() {

    }
    /*
     "Wenn ich eine Instanz mit new Buch(...) erstelle,
     muss ich ihr sofort eine id und einen titel mitgeben. 
     Der Konstruktor sorgt dafür, dass diese übergebenen Werte in den Feldern
     this.id und this.titel gespeichert werden."
     */   
    public Buch(int id, String titel){
        this.id = id;
        this.titel = titel;
    }

    // Getter und Setter um die Felder bearbeiten zu können
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    
    public String getTitel(){
        return titel;
    }
    public void setTitel(String titel){  
        this.titel = titel;
    }
}
