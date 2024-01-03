package org.example.models.Task02;

import org.example.models.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Course course = Course.create();
            session.save(course);
            Course recievedCourse = session.get(Course.class, course.getId());
            System.out.println(recievedCourse);
            recievedCourse.updateDuration();
            recievedCourse.updateTitle();
            session.update(recievedCourse);
            session.delete(recievedCourse);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
