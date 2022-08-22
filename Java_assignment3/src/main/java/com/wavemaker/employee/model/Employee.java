package com.wavemaker.employee.model;

public class Employee {
    private String ID;
    private String first_name;
    private String last_name;
    private String date_of_birth;
    private String company_name;
    private String blood_group;

    public Employee() {
    }

    public Employee(String id, String first_name, String last_name, String date_of_birth,
                    String company_name, String blood_group) {
        ID = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_of_birth = date_of_birth;
        this.company_name = company_name;
        this.blood_group = blood_group;
    }

    public String getId() {
        return ID;
    }

    public void setId(String id) {
        ID = id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getDOB() {
        return date_of_birth;
    }

    public void setDOB(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getCompanyName() {
        return company_name;
    }

    public void setCompanyName(String company_name) {
        this.company_name = company_name;
    }

    public String getBloodGroup() {
        return blood_group;
    }

    public void setBloodGroup(String blood_group) {
        this.blood_group = blood_group;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "Id=" + ID +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", dob=" + date_of_birth +
                ", company='" + company_name + '\'' +
                ", blood_group='" + blood_group + '\'' +
                '}';
    }
}
