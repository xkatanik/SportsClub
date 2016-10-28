package cz.muni.fi.pa165.sportsclub.dao;

import cz.muni.fi.pa165.sportsclub.entity.Player;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DAO implementation of {@link PlayerDao} for DB.
 * 
 * @author Patrik Nov�k
 */
@Repository
@Transactional
public class PlayerDaoImpl implements PlayerDao{

    @PersistenceContext
    EntityManager em;
    
    public void create(Player player) {
        em.persist(player);
    }

    public Player update(Player player) {
        return em.merge(player);
    }

    public void remove(Player player) {
        em.remove(player);
    }

    public Player findById(Long id) {
        return em.find(Player.class, id);
    }
    
}
