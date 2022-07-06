package cloudServer.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "MYUSER")
@Data
@AllArgsConstructor
public class MyUser {

    @Id
    @GeneratedValue
    private long id;

    @Column(name="firstName")
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String customPassword;

    @Column
    private String customToken;

    public MyUser() {
        super();
    }

    public MyUser(String firstName, String lastName, String customPassword, String customToken) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.customPassword = customPassword;
        this.customToken = customToken;
    }
}
