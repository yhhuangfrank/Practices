package org.frank.cloud.service.impl;

import lombok.RequiredArgsConstructor;
import org.frank.cloud.entity.Pay;
import org.frank.cloud.repository.PayRepository;
import org.frank.cloud.service.PayService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PayServiceImpl implements PayService {

    private final PayRepository payRepository;

    @Override
    public void add(Pay pay) {
        payRepository.save(pay);
    }

    @Override
    public void delete(Integer id) {
        payRepository.deleteById(id);
    }

    @Override
    public void update(Pay pay) {
        payRepository.save(pay);
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
