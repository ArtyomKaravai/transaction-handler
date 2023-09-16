package com.example.transactionhandler.repository;

import com.example.transactionhandler.model.Transaction;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @EntityGraph(value = "fullTransaction")
    Optional<Transaction> findById(Integer id);

    @EntityGraph(value = "fullTransaction")
    @Query(value = " SELECT t FROM transaction t " +
            "WHERE (:date is null or t.date = :date)" +
            "  AND (:type is null or t.type.typeName = :type) " +
            "  AND (:actor is null or t.actor.actorName = :actor)")
    List<Transaction> search(Timestamp date, String type, String actor);
}
