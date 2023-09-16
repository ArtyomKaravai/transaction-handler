package com.example.transactionhandler.service;

import com.example.transactionhandler.model.dto.TransactionDto;

import java.sql.Timestamp;
import java.util.List;

public interface TransactionService {

    TransactionDto getById(Integer id);

    void save(TransactionDto transaction);

    void update(TransactionDto transaction);

    void delete(Integer id);

    List<TransactionDto> search(Timestamp date, String type, String actor);
}
