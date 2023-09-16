package com.example.transactionhandler.service.impl;

import com.example.transactionhandler.converter.TransactionConverter;
import com.example.transactionhandler.exception.TransactionNotFoundException;
import com.example.transactionhandler.model.dto.TransactionDto;
import com.example.transactionhandler.repository.TransactionRepository;
import com.example.transactionhandler.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionConverter transactionConverter;

    @Override
    public TransactionDto getById(Integer id) {
        return transactionRepository.findById(id)
                .map(transactionConverter::toDto)
                .orElseThrow(() -> new TransactionNotFoundException("There is no transaction with id:" + id));
    }

    @Override
    public void save(TransactionDto transaction) {
        transactionRepository.save(transactionConverter.toEntity(transaction));
    }

    @Override
    public void update(TransactionDto transaction) {
        transactionRepository.save(transactionConverter.toEntity(transaction));
    }

    @Override
    public void delete(Integer id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public List<TransactionDto> search(Timestamp date, String type, String actor) {
        return transactionConverter.toDtoList(transactionRepository.search(date, type, actor));
    }
}
