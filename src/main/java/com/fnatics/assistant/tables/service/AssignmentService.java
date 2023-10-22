package com.fnatics.assistant.tables.service;

import com.fnatics.assistant.tables.Assignment;
import com.fnatics.assistant.tables.Student;
import com.fnatics.assistant.tables.repo.AssignmentRepo;
import com.fnatics.assistant.tables.repo.StudentRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AssignmentService {

    private EntityManagerFactory emf;

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Autowired
    private AssignmentRepo assignmentRepo;

    public List<Assignment> getAllAssignments(){
        return assignmentRepo.findAll();
    }

    public List<Assignment> getMyAssignmentDues(Date firstDateOfWeek, Date lastDayOfWeek){

        List<Assignment> result = null;
        EntityManager em = this.emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Assignment> cq = cb.createQuery(Assignment.class);

            Root<Assignment> assignment = cq.from(Assignment.class);
            Predicate greaterThanOrEqualTo = cb.greaterThanOrEqualTo(assignment.get("end_date"), firstDateOfWeek);
            Predicate lessThanOrEqualTo = cb.lessThanOrEqualTo(assignment.get("end_date"), lastDayOfWeek);

            cq.where(greaterThanOrEqualTo, lessThanOrEqualTo);

            TypedQuery<Assignment> query = em.createQuery(cq);
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
