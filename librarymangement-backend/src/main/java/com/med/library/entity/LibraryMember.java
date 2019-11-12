package com.med.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LibraryMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Last name is mandatory")
    private String lastname;

    @NotNull(message = "First name is mandatory")
    private String firstname;

    @Temporal(value = TemporalType.DATE)
    private Date birthday;

    @NotNull(message = "CIN is mandatory")
    @Column(unique = true, length = 20)
    private String cin;

    @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", message = "Invalid mail format.")
    private String mail;

    @Pattern(regexp = "^0[1-9][0-9]{8}$", message = "Invalid phone number format.")
    private String phone;

    @NotNull(message = "Author is mandatory")
    private int maxBorrowing;

    @OneToMany(mappedBy = "libraryMember")
    private List<Borrow> borrows;

    @OneToMany(mappedBy = "libraryMember")
    private List<Penalty> penalties;

    public LibraryMember(@NotNull(message = "Last name is mandatory") String lastname, @NotNull(message = "First name is mandatory") String firstname, Date birthday, @NotNull(message = "CIN is mandatory") String cin, @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", message = "Invalid mail format.") String mail, @Pattern(regexp = "^0[1-9][0-9]{8}$", message = "Invalid phone number format.") String phone, @NotNull(message = "Author is mandatory") int maxBorrowing) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthday = birthday;
        this.cin = cin;
        this.mail = mail;
        this.phone = phone;
        this.maxBorrowing = maxBorrowing;
    }

    public void addBorrow(Borrow borrow) {
        this.borrows.add(borrow);
    }

    public void addPenalty(Penalty penalty) {
        this.penalties.add(penalty);
    }
}
