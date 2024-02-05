package com.Camp.SpringSecurity.SpringSecuritydemo.WorkerException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException{
    private String fieldName;
    private long fieldValue;
    public ResourceNotFoundException(String fieldName, Long fieldValue){
        super("%s is not found with %s value,fieldName,fieldValue");
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
