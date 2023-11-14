package lk.ijse.firstsemfinal.DTO.tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class BeveragesTm {
    private String id;
    private String name;
    private String category;
    private String price;
    private JFXButton updateButton;
    private JFXButton deleteButton;

}
