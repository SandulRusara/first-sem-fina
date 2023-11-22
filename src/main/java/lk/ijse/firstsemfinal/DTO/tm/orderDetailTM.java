package lk.ijse.firstsemfinal.DTO.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class orderDetailTM {
    private int itemID;
    private String itemName;
    private String itemPrice;
    private String qty;
    private String total;
    private JFXButton deleteAction;
}
