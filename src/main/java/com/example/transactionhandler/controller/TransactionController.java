package com.example.transactionhandler.controller;

import com.example.transactionhandler.model.dto.TransactionDto;
import com.example.transactionhandler.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("{id}")
    public ResponseEntity<TransactionDto> getTransaction(@PathVariable Integer id) {
        return ResponseEntity.ok(transactionService.getById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> updateTransaction(@PathVariable Integer id) {
        transactionService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> saveTransaction(@RequestBody TransactionDto transaction) {
        transactionService.save(transaction);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateTransaction(@RequestBody TransactionDto transaction) {
        transactionService.update(transaction);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<TransactionDto>> search(@RequestParam Timestamp date,
                                                       @RequestParam String type,
                                                       @RequestParam String actor) {
        return ResponseEntity.ok(transactionService.search(date, type, actor));
    }
}
