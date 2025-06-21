package linhlt.project.backend_service.model;

import jakarta.persistence.*;
import linhlt.project.backend_service.common.Gender;
import linhlt.project.backend_service.common.UserStatus;
import linhlt.project.backend_service.common.UserType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserEntity {
     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     String id;

     String username;

     String password;

     String firstName;

     String lastName;

     String email;

     @Enumerated(EnumType.STRING)
     Gender gender;

     @Temporal(TemporalType.DATE)
     Date birthDate;

     String phoneNumber;

     @Enumerated(EnumType.STRING)
     UserType userType;

     @Enumerated(EnumType.STRING)
     UserStatus status;

     @Temporal(TemporalType.TIMESTAMP)
     @CreationTimestamp
     Date createDate;

     @Temporal(TemporalType.TIMESTAMP)
     @UpdateTimestamp
     Date updateDate;

}


