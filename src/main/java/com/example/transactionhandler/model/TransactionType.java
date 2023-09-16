package com.example.transactionhandler.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "transaction_type")
public class TransactionType {

    @Id
    private String typeName;
}
