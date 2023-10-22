package com.fnatics.assistant.tables.service;

import com.fnatics.assistant.tables.Takes;
import com.fnatics.assistant.tables.repo.TakesRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TakesService {

    private EntityManagerFactory emf;

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Autowired
    private TakesRepo takesRepo;

    public List<Takes> getAllTakes(){
        return takesRepo.findAll();
    }

    public List<Takes> getMyTakes(String student_id, String sub_code){

        List<Takes> result = null;
        EntityManager em = this.emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Takes> cq = cb.createQuery(Takes.class);

            Root<Takes> takes = cq.from(Takes.class);
            Predicate studentIdPredicate = cb.equal(takes.get("student_id"), student_id);

            if(sub_code!=null){
                Predicate sub_codePredicate = cb.equal(takes.get("sub_code"), sub_code);
                cq.where(studentIdPredicate, sub_codePredicate);
            }else{
                cq.where(studentIdPredicate);
            }

            TypedQuery<Takes> query = em.createQuery(cq);
            result = query.getResultList();
        }catch (Exception e){

        }finally {
            if (em != null) {
                em.close();
            }
        }
    return result;
    }
}
