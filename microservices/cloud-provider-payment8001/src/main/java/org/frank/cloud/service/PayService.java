package org.frank.cloud.service;

import org.frank.cloud.entity.Pay;

import java.util.List;

public interface PayService {
    Pay add(Pay pay);

    void delete(Integer id);

    Pay update(Pay pay);

    Pay getById(Integer id);

    List<Pay> getAll();

    Pay save(Pay pay);
}
