package site.adithk.usermanagementservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.adithk.usermanagementservice.enums.UserRole;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "app_user")
public class UserEntity {

    public UserEntity(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @SequenceGenerator(
            name = "app_user_sequence",
            sequenceName = "app_user_sequence",
            initialValue = 1001,
            allocationSize = 1

    )

    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "app_user_sequence"
    )
    @Id
    private Integer id;

    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    private Boolean isBlocked=true;
    private UserRole userRole=UserRole.APP_USER;
    private String profileImageUrl;
    private Date joinDate;
    private String jobTitle;
    private String aboutMe;
    private String department;
    private Integer teamId;
    private Boolean isOnline;

    @OneToOne(cascade = CascadeType.ALL)
    private UserVerificationData verificationData;

}
