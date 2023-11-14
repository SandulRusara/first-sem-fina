package lk.ijse.firstsemfinal.DTO.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class BeveragesTm {
    private String id;
    private String name;
    private String price;
    private String categories;
    private JFXButton updateButton;
    private JFXButton deleteButton;

}
