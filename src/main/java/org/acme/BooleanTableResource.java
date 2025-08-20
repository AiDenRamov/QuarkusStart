package org.acme;


import io.quarkus.qute.Template;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path("/warheitstabellen")
public class BooleanTableResource {

    private Template table;

    public BooleanTableResource(Template table) {
        this.table = table;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String showTable() {
        // Listen Instanzen erstellen
        List<TruthRow> andRows = new ArrayList<>();
        List<TruthRow> orRows = new ArrayList<>();
        List<TruthRow> xorRows = new ArrayList<>();
        List<NotRow> notRows = new ArrayList<>();

        // Werte generieren
        for (boolean a : new boolean[]{true, false}) {
            // NOT -> Seperat für bessere lesbarkeit?
            notRows.add(new NotRow(a, !a));

            for (boolean b : new boolean[]{true, false}) {
                // Wird 4x ausgeführt mit allen Kombinationen:
                // 1. a=true, b=true
                // 2. a=true, b=false  
                // 3. a=false, b=true
                // 4. a=false, b=false

                // AND
                andRows.add(new TruthRow(a, b, a && b));
                // OR
                orRows.add(new TruthRow(a, b, a || b));
                // XOR
                xorRows.add(new TruthRow(a, b, a ^ b));
            }
        }
        
        // Alle Daten an Template übergeben
        // data() Methode aus .qute.Template
        return table.data("andRows", andRows)
                    // Fügt andRows unter dem Namen "andRows" hinzu
                    .data("orRows", orRows)
                    .data("xorRows", xorRows)
                    .data("notRows", notRows)
                    .render();
                    //Erzeugt den finalen Objekt
    }

    // Hier muss ich defineren wie ein Zeile aussehen soll danach kann ich es als Datentyp in einer Liste benutzen
    // Jeder Index in List<TruthRow> andRows = new ArrayList<>(); hat dann die Zeilen nachdem diese generiert wurden
    public static class TruthRow {
        public final boolean a;
        public final boolean b;
        public final boolean result;

        public TruthRow(boolean a, boolean b, boolean result) {
            this.a = a;
            this.b = b;
            this.result = result;
        }
    }

    public static class NotRow {
        public final boolean a;
        public final boolean result;

        public NotRow(boolean a, boolean result) {
            this.a = a;
            this.result = result;
        }
    }
}