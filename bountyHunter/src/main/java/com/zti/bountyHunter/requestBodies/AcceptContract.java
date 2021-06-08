package com.zti.bountyHunter.requestBodies;

public class AcceptContract {
    public AcceptContract() {
    }
    public AcceptContract(String email, Integer contractId) {
        this.email = email;
        this.contractId = contractId;
    }
    private String email;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getContractId() {
        return contractId;
    }
    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }
    private Integer contractId;
}
