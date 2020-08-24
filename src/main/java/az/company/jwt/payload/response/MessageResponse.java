package az.company.jwt.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {
	private String message;

	@Override
	public String toString() {
		return "MessageResponse{" +
				"message='" + message + '\'' +
				'}';
	}
}
