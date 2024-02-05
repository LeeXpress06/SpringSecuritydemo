package com.Camp.SpringSecurity.SpringSecuritydemo.WorkerService;

import com.Camp.SpringSecurity.SpringSecuritydemo.WorkerDTO.WorkerDto;
import com.Camp.SpringSecurity.SpringSecuritydemo.WorkerEntity.Worker;
import com.Camp.SpringSecurity.SpringSecuritydemo.WorkerException.ResourceNotFoundException;
import com.Camp.SpringSecurity.SpringSecuritydemo.WorkerRepository.WorkerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkerService {

     @Autowired
     private WorkerRepository workerRepository;

     @Autowired
     private PasswordEncoder encoder;
     @Autowired
     ModelMapper modelMapper;
     public  WorkerDto  savedWorkers(WorkerDto workerDto ){
         workerDto.setPassword(encoder.encode(workerDto.getPassword()));
         Worker worker = modelMapper.map(workerDto, Worker.class);

        Worker savedWorker = workerRepository.save(worker);
        WorkerDto workerDtoSaved = modelMapper.map(savedWorker,WorkerDto.class);

        return  workerDtoSaved;
                 }
    public WorkerDto getWorkerByid( long id){
         Optional<Worker> worker = workerRepository.findById(id);
         if(worker.isEmpty())  throw new ResourceNotFoundException(worker.get().getWorkerName(),id);
         else  return modelMapper.map(worker,WorkerDto.class);
    }









}
