package bo.jads.myfinancesbackend.app.domain.entities;

import bo.jads.myfinancesbackend.app.domain.entities.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 64)
    private String username;

    @Column(name = "password", nullable = false, length = 512)
    private String password;

    @Column(name = "code", unique = true, length = 64)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 64)
    private UserStatus status;

    @Column(name = "names", nullable = false, length = 128)
    private String names;

    @Column(name = "first_surname", length = 128)
    private String firstSurname;

    @Column(name = "second_surname", length = 128)
    private String secondSurname;

    @Column(name = "email", unique = true, nullable = false, length = 320)
    private String email;

    @Column(name = "phone", length = 64)
    private String phone;

    @Column(name = "photo_path", length = 512)
    private String photoPath;

    @PrePersist
    @PreUpdate
    private void prePersistOrUpdate() {
        username = username.toLowerCase().trim();
        names = names.toUpperCase().trim();
        if (firstSurname != null && firstSurname.isBlank()) {
            firstSurname = null;
        }
        if (firstSurname != null) {
            firstSurname = firstSurname.toUpperCase().trim();
        }
        if (secondSurname != null && secondSurname.isBlank()) {
            secondSurname = null;
        }
        if (secondSurname != null) {
            secondSurname = secondSurname.toUpperCase().trim();
        }
        email = email.toLowerCase();
    }

}
