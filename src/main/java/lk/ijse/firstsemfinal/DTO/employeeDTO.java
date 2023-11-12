package lk.ijse.firstsemfinal.DTO;

import lombok.*;
import org.w3c.dom.Text;

import java.util.Date;

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
    private Date date;

}
