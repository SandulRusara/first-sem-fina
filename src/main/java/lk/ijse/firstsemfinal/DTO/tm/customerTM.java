package lk.ijse.firstsemfinal.DTO.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class customerTM {
    private int custId;
    private String name;
    private String address;
    private String contact;
    private String date;
    private JFXButton button;
}
