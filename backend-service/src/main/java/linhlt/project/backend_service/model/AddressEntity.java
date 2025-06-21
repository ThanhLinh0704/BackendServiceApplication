package linhlt.project.backend_service.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String apartmentNumber;

    String floor;

    String building;

    String streetNumber;

    String street;

    String city;

    String country;

    Integer addressType;

    String userId;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    Date updatedAt;

}


