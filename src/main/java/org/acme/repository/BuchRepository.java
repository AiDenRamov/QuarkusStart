package org.acme.repository;

import org.acme.model.Buch;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class BuchRepository {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void insertBuch(Buch buch) {
        entityManager.persist(buch);
    }

    @Transactional
    public void deleteBuchById(Long id) {
        // Finde das zu löschende Buch anhand der id
        Buch buch = entityManager.find(Buch.class, id);
        // Falls das Buch exestiert, lösche disen
        if (buch != null) {
            entityManager.remove(buch);
        }
    }

    @Transactional
    public List<Buch> findAll() {
        // JPA-Querry für Hybernate 
        // Nativ: entityManager.createNativeQuery("SELECT * FROM buecher"
        return entityManager.createQuery("SELECT b FROM Buch b", Buch.class)
                            .getResultList();
    }
}
