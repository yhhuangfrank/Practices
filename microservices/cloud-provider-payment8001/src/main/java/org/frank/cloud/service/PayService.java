package org.frank.cloud.service;

import org.frank.cloud.entity.Pay;

import java.util.List;

public interface PayService {
    void add(Pay pay);

    void delete(Integer id);

    void update(Pay pay);

    Pay getById(Integer id);

    List<Pay> getAll();
}
