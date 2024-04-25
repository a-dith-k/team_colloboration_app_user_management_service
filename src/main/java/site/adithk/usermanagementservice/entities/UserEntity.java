package site.adithk.usermanagementservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
