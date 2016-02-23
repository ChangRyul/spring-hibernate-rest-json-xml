package com.igloosec.app.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.igloosec.app.dto.ShortDateAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 * Created by enrico on 9/5/14.
 */
@XmlRootElement(name="items")
@JsonRootName("items")
public class UserResponse {
    private String code;
    private String message;
    private String name;
    private String user_id;
    private String user_name;
    private String tel;
    private String address;
    private Date birthday;
    private String person;
    private int career;
    private int loan;
    private int grade;
    private int layer_grade;
    private int keep_grade;
    private Date contract;

    @XmlElement
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlElement
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @XmlElement
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @XmlElement
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @XmlElement
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlElement
    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    @XmlElement
    public int getCareer() {
        return career;
    }

    public void setCareer(int career) {
        this.career = career;
    }

    @XmlElement
    public int getLoan() {
        return loan;
    }

    public void setLoan(int loan) {
        this.loan = loan;
    }

    @XmlElement
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @XmlElement
    public int getLayer_grade() {
        return layer_grade;
    }

    public void setLayer_grade(int layer_grade) {
        this.layer_grade = layer_grade;
    }

    @XmlElement
    public int getKeep_grade() {
        return keep_grade;
    }

    public void setKeep_grade(int keep_grade) {
        this.keep_grade = keep_grade;
    }

    @XmlElement
    @JsonFormat(pattern = "yyyy-MM-dd")
    @XmlJavaTypeAdapter(value=ShortDateAdapter.class, type = Date.class)
    public Date getContract() {
        return contract;
    }

    public void setContract(Date contract) {
        this.contract = contract;
    }

    @XmlElement
    @JsonFormat(pattern = "yyyy-MM-dd")
    @XmlJavaTypeAdapter(value=ShortDateAdapter.class, type = Date.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
