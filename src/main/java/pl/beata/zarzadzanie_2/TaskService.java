package pl.beata.zarzadzanie_2;

import java.util.List;

import javax.persistence.*;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Duty> fetchAllFinishedDuties() {
        TypedQuery<Duty> selectQuery = entityManager.createQuery("SELECT d FROM Duty d WHERE d.status=1", Duty.class);
        return selectQuery.getResultList();
    }

    public List<Duty> fetchAllUnfinishedDuties() {
        TypedQuery<Duty> selectQuery = entityManager.createQuery("SELECT d FROM Duty d WHERE d.status=0 ORDER BY d.date", Duty.class);
        return selectQuery.getResultList();
    }

    @Transactional
    public void insertDuty(Duty duty) {
        entityManager.persist(duty);
    }


}

