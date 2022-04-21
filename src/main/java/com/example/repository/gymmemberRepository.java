package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface gymmemberRepository extends JpaRepository<gymmember, Long>{

	com.example.model.gymmember save(com.example.model.gymmember gymmember);

}
