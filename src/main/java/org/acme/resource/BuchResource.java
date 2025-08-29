package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Buch;
import org.acme.repository.BuchRepository;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import io.quarkus.qute.Template;


import java.net.URI;
import java.util.List;

@Path("/mariadb")

public class BuchResource {

    // Datenbankinteraktionen Klasse
    @Inject
    BuchRepository buchRepository;

    // Anzeigetemlate
    @Inject
    Template meinebuecher;

    // POST-Endpunkt für Formulardaten
    @POST
    @Path("/buecher")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createBuchForm(@FormParam("titel") String titel) {
        Buch buch = new Buch();
        buch.setTitel(titel);
        
        buchRepository.insertBuch(buch);
        
        // Nach dem Hinzufügen zur Startseite weiterleiten
        // Keine React dynamische Seite!
        return Response.seeOther(URI.create("/mariadb/meinebuecher")).build();
    }

    // JSON Daten verarbeiten z.b mit curl 
    // Die Seite Funktioniert auch ohne diese Methode, sie war nur zum Testen der Datenbankverbindung erstellt
    @POST
    @Path("/buecher")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBuch(Buch buchRequest) {
        // Neues Buch-Objekt erstellen und den Titel übernehmen
        Buch buchToInsert = new Buch();
        buchToInsert.setTitel(buchRequest.getTitel());
       

        buchRepository.insertBuch(buchToInsert);
        return Response.status(Response.Status.CREATED).entity(buchToInsert).build();
    }

    // DELETE Endpunkt 
    @POST
    @Path("/buecher/delete/{id}")
    @Produces(MediaType.TEXT_HTML)
    public Response deleteBuch(@PathParam("id") Long id){
        // Woran soll ich ausmachen welches buch gelöscht werden soll?
        // Die id ist ja autogeneriert in der Datenbank - bleibt also nur der Name des Buches übrig?
        // -> Dann würden aber alle gleichnamigen Bücher gelöscht - nuer die id ist Eindeutig!

        buchRepository.deleteBuchById(id);
        // Neuladen der Seite
        return Response.seeOther(URI.create("/mariadb/meinebuecher")).build();
    }

    // Datenbankinhalte Anzeigen im Template
    @GET
    @Path("/meinebuecher")
    @Produces(MediaType.TEXT_HTML)
    public String getBuecher() {
        List<Buch> buecher = buchRepository.findAll();
        return meinebuecher.data("buecher", buecher).render();
    }
    
}