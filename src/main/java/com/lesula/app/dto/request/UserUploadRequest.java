package com.lesula.app.dto.request;

import com.lesula.app.validators.Age;
import com.lesula.app.validators.Phone;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by enrico on 9/9/14.
 */
@XmlRootElement
public class UserUploadRequest {

    @NotBlank(message = "User first name cannot be empty")
    @Length(min = 2, message = "Minimum length is 2")
    private String firstName;

    @NotBlank(message = "User last name cannot be empty")
    @Length(min = 2, message = "Minimum length is 2")
    private String lastName;

    @Phone
    private String phone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull @Past @Age(value=18, message = "Minimum age is 18")
    private Date birthday;

    @XmlElement
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @XmlElement
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
