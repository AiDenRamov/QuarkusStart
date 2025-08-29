package org.acme.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="buecher")
public class Buch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buch_id;
    
    private String titel;

    // Getter und Setter
    public Long getId() { return buch_id; }
    public void setId(Long buch_id) { this.buch_id = buch_id; }
    public String getTitel() { return titel; }
    public void setTitel(String titel) { this.titel = titel; }
}
