package linhlt.project.backend_service.dto.response;

import linhlt.project.backend_service.common.Gender;
import linhlt.project.backend_service.common.UserType;
import linhlt.project.backend_service.dto.request.AddressRequest;
import linhlt.project.backend_service.model.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResponse implements Serializable {
     String id;
     String username;
     String password;
     String firstName;
     String lastName;
     String email;
     Gender gender;
     Date birthDate;
     String phoneNumber;
     UserType userType;
     List<AddressRequest> addressRequests;
     Set<Role> roles;
}
