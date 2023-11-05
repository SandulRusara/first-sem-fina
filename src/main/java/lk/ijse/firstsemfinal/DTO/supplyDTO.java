package lk.ijse.firstsemfinal.DTO;


import lombok.*;

import java.util.Date;
import java.util.Locale;
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class supplyDTO {
    private Integer supplierId;
    private Integer ingredientId;
    private Date date;
    private Integer qty;
}
