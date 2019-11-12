package com.med.library.service.impl;

import com.med.library.entity.Penalty;
import com.med.library.repository.PenaltyRepository;
import com.med.library.service.PenaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PenaltyServiceImpl implements PenaltyService {

    private PenaltyRepository penaltyRepository;

    @Autowired
    public PenaltyServiceImpl(PenaltyRepository penaltyRepository) {
        this.penaltyRepository = penaltyRepository;
    }

    @Override
    public List<Penalty> findAll() {
        return penaltyRepository.findAll();
    }

    @Override
    public Optional<Penalty> findById(Long penaltyId) {
        return penaltyRepository.findById(penaltyId);
    }

    @Override
    public Penalty save(Penalty penalty) {
        return penaltyRepository.save(penalty);
    }

    @Override
    public void deleteById(Long penaltyId) {
        penaltyRepository.deleteById(penaltyId);
    }
}
