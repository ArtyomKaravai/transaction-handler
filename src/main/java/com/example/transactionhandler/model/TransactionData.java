package com.example.transactionhandler.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity(name = "transaction_data")
public class TransactionData {

    @EmbeddedId
    private TransactionDataId compositeId;

    private String value;

    @MapsId("transactionId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    private Transaction transaction;

    @Getter
    @Setter
    @Embeddable
    @NoArgsConstructor
    public static class TransactionDataId implements Serializable {

        public TransactionDataId(Integer transactionId, String key) {
            this.transactionId = transactionId;
            this.key = key;
        }

        @Column(name = "transaction_id")
        private Integer transactionId;
        private String key;
    }
}
