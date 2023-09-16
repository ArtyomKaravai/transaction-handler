package com.example.transactionhandler.model;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity(name = "transaction")
@NamedEntityGraph(name = "fullTransaction", includeAllAttributes = true)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Timestamp date;

    @JoinColumn(name = "type")
    @ManyToOne(fetch = FetchType.LAZY)
    private TransactionType type;

    @JoinColumn(name = "actor")
    @ManyToOne(fetch = FetchType.LAZY)
    private TransactionActor actor;

    @OneToMany(fetch = FetchType.LAZY)
    private List<TransactionData> data;
}
