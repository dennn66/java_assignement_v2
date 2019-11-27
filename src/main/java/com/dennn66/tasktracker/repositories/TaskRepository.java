package com.dennn66.tasktracker.repositories;

import com.dennn66.tasktracker.entities.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class TaskRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Task findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Task task = session.get(Task.class, id);
        return task;
    }

    public List<Task> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Task> tasks = session.createQuery("SELECT i FROM Task i").getResultList();
        return tasks;
    }

    public void insert(Task task) {
        Session session = sessionFactory.getCurrentSession();
        session.save(task);
    }

    public void update(Task task) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(task);
    }

    public Optional<Task> findOneById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Task task = session.get(Task.class, id);
        session.getTransaction().commit();
        if(task == null) return Optional.empty();
        return Optional.of(task);
    }
}