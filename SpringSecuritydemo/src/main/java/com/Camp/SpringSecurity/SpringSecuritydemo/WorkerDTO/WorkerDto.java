package com.Camp.SpringSecurity.SpringSecuritydemo.WorkerDTO;

import com.Camp.SpringSecurity.SpringSecuritydemo.WorkerCustomValidation.MinimumSalary;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {
    private Long worker_id;
    private String workerName;
    @Max(5)
    private String department;
    @Min(2)
    private String location;
    @MinimumSalary
    private long salary;
    private String roles;
    private String password;

    @Override
    public String toString() {
        return "WorkerDto{" +
                "worker_id=" + worker_id +
                ", workerName='" + workerName + '\'' +
                ", department='" + department + '\'' +
                ", location='" + location + '\'' +
                ", salary=" + salary +
                '}';
    }
}
