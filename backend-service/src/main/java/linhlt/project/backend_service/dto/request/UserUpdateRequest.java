package linhlt.project.backend_service.dto.request;

import linhlt.project.backend_service.common.Gender;
import linhlt.project.backend_service.common.UserType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
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
}
