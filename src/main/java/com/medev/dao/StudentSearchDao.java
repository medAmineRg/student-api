package com.medev.dao;

import com.medev.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentSearchDao {

    private final EntityManager em;
    public StudentSearchDao(EntityManager em) {
        this.em = em;
    }

    public List<Student> filterStudent(
            String firstName,
            String lastName,
            String phone
    ) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = builder.createQuery(Student.class);

        Root<Student> root = criteriaQuery.from(Student.class);

        Predicate firstNamePredicate = builder.like(root.get("firstName"), "%"+firstName+"%");
        Predicate lastNamePredicate = builder.like(root.get("lastName"), "%"+lastName+"%");
        Predicate phonePredicate = builder.like(root.get("phone"), "%"+phone+"%");

        Predicate flpOrPredicate = builder.or(firstNamePredicate, lastNamePredicate, phonePredicate);

        criteriaQuery.where(flpOrPredicate);

        TypedQuery<Student> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
