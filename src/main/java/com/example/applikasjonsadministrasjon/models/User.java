    package com.example.applikasjonsadministrasjon.models;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import com.fasterxml.jackson.annotation.JsonManagedReference;
    import jakarta.persistence.*;

    import lombok.Getter;
    import lombok.Setter;

    import java.util.Collection;
    import java.util.List;
    import java.util.Set;

    @Entity
    @Getter
    @Setter
    @Table(name = "users")
    public class User {
        @Id
        @Column(unique = true, nullable = false, updatable = false)
        private String id;

        @Column(length = 50,nullable = false)
        private String firstName;
        @Column(length = 50,nullable = false)
        private String lastName;
        @Column(length = 50,nullable = false)
        private String email;
        @Column(nullable = false)
        private boolean selger;

        @OneToMany(mappedBy = "madeByUser")
        private Set<Stilling> madePositions;
        @ManyToMany
        @JoinTable(name = "Bruker_Stilling",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "stilling_id"))

        private Set<Stilling> stilling;


    }
