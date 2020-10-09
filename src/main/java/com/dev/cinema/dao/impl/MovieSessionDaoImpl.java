package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.MovieSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            Query<MovieSession> query = session.createQuery("from MovieSession where "
                    + "movie_id = :movie_id and showTime BETWEEN :startDateTime "
                    + "and :finishDateTime", MovieSession.class);

            query.setParameter("movie_id", movieId);
            query.setParameter("startDateTime", LocalDateTime.of(date, LocalTime.of(0, 0)));
            query.setParameter("finishDateTime", LocalDateTime.of(date, LocalTime.of(23, 59)));
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving all cinema halls. ", e);
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long itemId = (Long) session.save(movieSession);
            transaction.commit();
            movieSession.setId(itemId);

            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert Cinema Hall entity", e);
        }
    }

    @Override
    public MovieSession findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<MovieSession> query = session.createQuery("from MovieSession where id = :id");

            query.setParameter("id", id);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Error finding movie session by id. ", e);
        }
    }
}
