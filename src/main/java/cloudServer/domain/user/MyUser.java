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
    private String customPassword;

    @Column
    private String customToken;

    public MyUser() {
        super();
    }

    public MyUser(String username, String customPassword, String customToken) {
        super();
        this.username = username;
        this.customPassword = customPassword;
        this.customToken = customToken;
    }

    public MyUser(String username, String customPassword) {
        super();
        this.username = username;
        this.customPassword = customPassword;
    }
}
