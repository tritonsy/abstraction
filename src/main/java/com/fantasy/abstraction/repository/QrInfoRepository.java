package com.fantasy.abstraction.repository;

import com.fantasy.abstraction.entity.QrInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QrInfoRepository extends JpaRepository<QrInfo, Long> {
}
