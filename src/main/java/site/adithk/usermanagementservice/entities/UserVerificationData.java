package site.adithk.usermanagementservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVerificationData {

    @SequenceGenerator(
            name = "verification_link_sequence",
            sequenceName = "verification_link_sequence",
            initialValue = 10001,
            allocationSize = 1

    )

    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "verification_link_sequence"
    )
    @Id
    private Integer id;

    private String verificationLink;

    private Boolean isVerified;

    private LocalDateTime generationTime;
}
