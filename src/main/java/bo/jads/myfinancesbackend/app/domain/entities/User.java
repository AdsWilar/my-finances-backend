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
        username = username.trim().toLowerCase();
        names = names.trim().toUpperCase();
        if (firstSurname != null) {
            if (firstSurname.isBlank()) {
                firstSurname = null;
            } else {
                firstSurname = firstSurname.trim().toUpperCase();
            }
        }
        if (secondSurname != null) {
            if (secondSurname.isBlank()) {
                secondSurname = null;
            } else {
                secondSurname = secondSurname.trim().toUpperCase();
            }
        }
        email = email.trim().toLowerCase();
    }

}
