package com.example.transactionhandler.model.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Map;


@Data
public class TransactionDto {

    private Integer id;
    private Timestamp date;
    private String type;
    private String actor;
    private Map<String, String> data;
}
