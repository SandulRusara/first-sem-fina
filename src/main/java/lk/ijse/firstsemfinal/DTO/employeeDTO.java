package lk.ijse.firstsemfinal.DTO;

import lombok.*;
import org.w3c.dom.Text;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class employeeDTO {
    private Integer employeeId;
    private String employeeName;
    private Text employeeAddress;
    private String employeeContact;
    private Integer userId;
}
