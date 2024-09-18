package JustTest;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;


public class EmployeeOperation {
    List<Employee> employees = Arrays.asList(new Employee("Rajeev","CS"), new Employee("Kavya","HP"));

    public Optional<Employee> get(int i){
        if(i< employees.size())
            return Optional.of(employees.get(i));
        return Optional.empty();
    }
}
