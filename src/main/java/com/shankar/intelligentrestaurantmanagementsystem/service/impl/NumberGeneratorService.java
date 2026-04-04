package com.shankar.intelligentrestaurantmanagementsystem.service.impl;

import com.shankar.intelligentrestaurantmanagementsystem.entity.DocumentSequence;
import com.shankar.intelligentrestaurantmanagementsystem.repository.DocumentSequenceRepository;
import com.shankar.intelligentrestaurantmanagementsystem.service.INumberGeneratorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class NumberGeneratorService implements INumberGeneratorService {

    private final DocumentSequenceRepository repository;


    @Override
    @Transactional
    public String generateOrderNumber() {
        return generate("ORDER", "ORD");
    }

    @Transactional
    @Override
    public String generateKotNumber() {
        return generate("KOT", "KOT");
    }

    private String generate(String type, String prefix) {

        LocalDate today = LocalDate.now();

        DocumentSequence sequence = repository
                .findForUpdate(type)
                .orElseGet(() -> {
                    DocumentSequence newSeq = new DocumentSequence();
                    newSeq.setName(type);
                    newSeq.setLastNumber(0L);
                    newSeq.setDate(today);
                    return newSeq;
                });

        // Daily reset logic
        if (!today.equals(sequence.getDate())) {
            sequence.setLastNumber(0L);
            sequence.setDate(today);
        }

        long next = sequence.getLastNumber() + 1;
        sequence.setLastNumber(next);

        repository.save(sequence);

        return String.format(
                "%s-%s-%04d",
                prefix,
                today.format(DateTimeFormatter.BASIC_ISO_DATE),
                next
        );
    }
}
