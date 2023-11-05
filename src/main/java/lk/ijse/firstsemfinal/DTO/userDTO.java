package lk.ijse.firstsemfinal.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class userDTO {
    private Integer userID;
    private String useName;
    private String password;
    private String email;
}
