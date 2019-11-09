package com.med.library.service;

import com.med.library.entity.Penalty;

import java.util.List;
import java.util.Optional;

public interface PenaltyService {
    public List<Penalty> findAll();
    public Optional<Penalty> findById(Long penaltyId);
    public Penalty save(Penalty penalty);
    public void deleteById(Long penaltyId);
}
