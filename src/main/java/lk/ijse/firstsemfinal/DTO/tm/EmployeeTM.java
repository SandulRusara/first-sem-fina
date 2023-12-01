package lk.ijse.firstsemfinal.DTO.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmployeeTM {
    private String idcolum;
    private String nameColum;
    private String AddressColum;
    private String contactColumn;
    private String NicColum;
    private JFXButton deleteColum;
    private JFXButton updateColum;
}
