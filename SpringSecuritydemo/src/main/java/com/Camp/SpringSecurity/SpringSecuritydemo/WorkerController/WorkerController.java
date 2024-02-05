package com.Camp.SpringSecurity.SpringSecuritydemo.WorkerController;

import com.Camp.SpringSecurity.SpringSecuritydemo.WorkerDTO.AuthRequestDto;
import com.Camp.SpringSecurity.SpringSecuritydemo.WorkerDTO.WorkerDto;
import com.Camp.SpringSecurity.SpringSecuritydemo.WorkerService.JwtService;
import com.Camp.SpringSecurity.SpringSecuritydemo.WorkerService.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/worker")
public class WorkerController {
     @Autowired
     private WorkerService workerService;
     @Autowired
     private JwtService jwtService;

     @Autowired
     private AuthenticationManager authManager;
     @PostMapping("/save")
     @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<WorkerDto> savedWorker( @RequestBody WorkerDto workerDto){
         WorkerDto savedWorker = workerService.savedWorkers(workerDto);
         return new ResponseEntity<>(savedWorker,HttpStatus.CREATED);
     }
     @GetMapping("/getworker/{id}")
     public ResponseEntity<WorkerDto> getWorker (@PathVariable long id ){
         WorkerDto workerDto = workerService.getWorkerByid(id);
         return  new ResponseEntity<>(workerDto,HttpStatus.FOUND);
     }
     @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequestDto authRequestDto){

      Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken
                 (authRequestDto.getUserName(), authRequestDto.getPassword()));

      if(authentication.isAuthenticated()){
          String getToken = jwtService.generateToken(authRequestDto.getUserName());
          return getToken;   }

     else throw  new UsernameNotFoundException(" stupid user request");
     }







}
