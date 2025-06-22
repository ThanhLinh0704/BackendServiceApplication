package linhlt.project.backend_service.dto.response;

import linhlt.project.backend_service.common.Gender;
import linhlt.project.backend_service.common.UserType;
import linhlt.project.backend_service.dto.request.AddressRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
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
     Set<String> roles;
}
