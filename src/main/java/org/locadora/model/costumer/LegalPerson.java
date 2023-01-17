package org.locadora.model.costumer;


import org.json.JSONObject;
import org.locadora.model.Address;
import org.locadora.model.Telephone;

import java.util.Objects;

public class LegalPerson extends Costumer {
    private String cnpj;
    private String nickname;
    private String companyDriver;
    private String driverLicense;

    public LegalPerson() {
    }

    public LegalPerson(String name, String nickname, String cnpj, String companyDriver, String driverLicense, Telephone telephone, Address adress) {
        super(name, telephone, adress);
        this.cnpj = cnpj;
        this.nickname = nickname;
        this.companyDriver = companyDriver;
        this.driverLicense = driverLicense;
    }

    public LegalPerson(String name, String nickname, String cnpj, String companyDriver, String driverLicense) {
        super(name);
        this.nickname = nickname;
        this.cnpj = cnpj;
        this.companyDriver = companyDriver;
        this.driverLicense = driverLicense;
    }

    public JSONObject toJSONObject() {
        JSONObject costumerObject = new JSONObject();
        costumerObject.put("name", this.getName());
        costumerObject.put("nickname", this.getNickname());
        costumerObject.put("cnpj", this.getCnpj());
        costumerObject.put("companyDriver", this.getCompanyDriver());
        costumerObject.put("driverLicense", this.getDriverLicense());
        costumerObject.put("address", this.getAddress());
        costumerObject.put("telephone", this.getTelephone());
        return costumerObject;
    }

    public void info(){
        System.out.println(" NOME: " + this.getName());
        System.out.println(" NOME FANTASIA: " + this.getNickname());
        System.out.println(" CNPJ: " + this.getCnpj());
        System.out.println(" MOTORISTA: " + this.getCompanyDriver());
        System.out.println(" CNH: " + this.getDriverLicense());
        System.out.println(" ENDEREÇO: " + this.getAddress());
        System.out.println(" TELEFONE: " + this.getTelephone());
    }

    public String getCompanyDriver() {
        return companyDriver;
    }

    public void setCompanyDriver(String companyDriver) {
        this.companyDriver = companyDriver;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    // TODO: SOBRESCREVER EQUALS, HASHCODE E TOSTRING

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LegalPerson that = (LegalPerson) o;
        return Objects.equals(cnpj, that.cnpj) && Objects.equals(nickname, that.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj, nickname);
    }

    @Override
    public String toString() {
        return "LegalPerson{" +
                "cnpj='" + cnpj + '\'' +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", telephone=" + telephone +
                ", address=" + address +
                '}';
    }


}
