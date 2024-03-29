package com.Camp.SpringSecurity.SpringSecuritydemo.WorkerRepository;

import com.Camp.SpringSecurity.SpringSecuritydemo.WorkerEntity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

  Optional<Worker>findByWorkerName(String workerName);




}
