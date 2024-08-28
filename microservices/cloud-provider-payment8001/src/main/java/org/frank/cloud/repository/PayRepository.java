package org.frank.cloud.repository;

import org.frank.cloud.entity.Pay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayRepository extends JpaRepository<Pay, Integer> {
}