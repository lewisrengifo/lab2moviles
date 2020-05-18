package com.example.laboratorio2.entidades;

public class Trabajo {

    private String jodId;
    private String jobTitle;
    private int minSalary;
    private int maxSalary;
    private String createdBy;

    public String getJodId() {
        return jodId;
    }

    public void setJodId(String jodId) {
        this.jodId = jodId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(int minSalary) {
        this.minSalary = minSalary;
    }

    public int getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(int maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
