   package com.example.applikasjonsadministrasjon.models.tables;

    import java.util.Set;

    import io.micrometer.common.lang.Nullable;
    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.JoinColumn;
    import jakarta.persistence.OneToMany;
    import jakarta.persistence.Table;
    import lombok.Getter;
    import lombok.Setter;

    @Entity
    @Getter
    @Setter
    @Table(name = "chatsession")
    public class ChatSession {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        
        @Nullable
        private String chatName;

        @OneToMany(mappedBy = "chatSession")
        private Set<Messages> chatSessionMessages;

        private String timeStamp;
    }
 