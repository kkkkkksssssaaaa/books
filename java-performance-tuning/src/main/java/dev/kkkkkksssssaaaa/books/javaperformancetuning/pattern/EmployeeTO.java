package dev.kkkkkksssssaaaa.books.javaperformancetuning.pattern;

import java.io.Serializable;

public class EmployeeTO implements Serializable {

    private String empName;
    private String empID;
    private String empPhone;

    public EmployeeTO() {
        super();
    }

    public EmployeeTO(String empName, String empID, String empPhone) {
        super();
        this.empName = empName;
        this.empID = empID;
        this.empPhone = empPhone;
    }

    public String getEmpName() {
        if (empName == null) return "";
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("empName=").append(empName)
            .append(",empID=").append(empID)
            .append(",empPhone=").append(empPhone);

        return sb.toString();
    }
}
