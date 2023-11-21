package lk.ijse.firstsemfinal.DTO.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class supplierTM {
    private int id;
    private String name;
    private String address;
    private String contact;
    private JFXButton deleteButton;
    private JFXButton updateButton;
}
