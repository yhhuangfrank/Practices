package org.frank.cloud.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.frank.cloud.entity.Pay;
import org.frank.cloud.repository.PayRepository;
import org.frank.cloud.service.PayService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PayServiceImpl implements PayService {

    private final PayRepository payRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Pay add(Pay pay) {
        Pay saved = payRepository.saveAndFlush(pay);
        entityManager.refresh(saved);
        return saved;
    }

    @Override
    @Transactional
    public Pay save(Pay pay) {
        Pay saved = payRepository.saveAndFlush(pay);
        entityManager.refresh(saved);
        return saved;
    }

    @Override
    public void delete(Integer id) {
        payRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Pay update(Pay pay) {
        Pay updated = payRepository.saveAndFlush(pay);
        entityManager.refresh(updated);
        return updated;
    }

    @Override
    public Pay getById(Integer id) {
        return payRepository.findById(id).orElse(null);
    }

    @Override
    public List<Pay> getAll() {
        return payRepository.findAll();
    }
}
