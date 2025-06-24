package linhlt.project.backend_service.dto.request;

import jakarta.validation.constraints.Size;
import linhlt.project.backend_service.common.Gender;
import linhlt.project.backend_service.common.UserType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserRequest {
     String username;
     @Size(min = 5, message = "PASSWORD_INVALID")
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

