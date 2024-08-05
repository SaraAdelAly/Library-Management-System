package librarymanagementsystem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Patron implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patronId;

    @Column(name="name", nullable = false)
    private String name;

    @Column (name="email",nullable = false)
    private String email;

    @Column(name="phone_number",nullable = false)
    private String phoneNumber;


    @Column(name="gender")
    private String gender;


    @OneToMany(mappedBy = "patron")
    private Set<Borrowing> borrowings;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patron patron = (Patron) o;
        return Objects.equals(patronId, patron.patronId) && Objects.equals(name, patron.name) && Objects.equals(email, patron.email) && Objects.equals(phoneNumber, patron.phoneNumber) && Objects.equals(borrowings, patron.borrowings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patronId, name, email, phoneNumber, borrowings);
    }
}
