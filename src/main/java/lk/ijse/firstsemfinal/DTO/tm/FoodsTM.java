package lk.ijse.firstsemfinal.DTO.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class FoodsTM {
    private String id;
    private String name;
    private String Categories;
    private String price;
    private JFXButton deleteButton;
    private JFXButton updateButton;
}
