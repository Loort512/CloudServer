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

    @Column
    private String username;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String customPassword;

    @Column
    private String customToken;

    @Column
    private boolean isAdmin;

    public MyUser() {
        super();
    }

    public MyUser(String username,
                  String customPassword,
                  String customToken,
                  String firstName,
                  String lastName,
                  boolean isAdmin) {
        super();
        this.username = username;
        this.customPassword = customPassword;
        this.customToken = customToken;
        this.isAdmin = isAdmin;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public MyUser(String username, String customPassword, String firstName, String lastName, boolean isAdmin) {
        super();
        this.username = username;
        this.customPassword = customPassword;
        this.isAdmin = isAdmin;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
