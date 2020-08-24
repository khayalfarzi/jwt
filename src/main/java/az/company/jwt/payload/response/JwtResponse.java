package az.company.jwt.payload.response;

import az.company.jwt.enums.Language;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private List<String> roles;
    private String passportId;
    private String address;
    private String mobileNumber;
    private Language language;
    private String paymentCards;

    @Override
    public String toString() {
        return "JwtResponse{" +
                "token='" + token + '\'' +
                ", type='" + type + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                ", passportId='" + passportId + '\'' +
                ", address='" + address + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", language=" + language +
                ", paymentCards='" + paymentCards + '\'' +
                '}';
    }
}
