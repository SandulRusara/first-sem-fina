package lk.ijse.firstsemfinal.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data

public class itemDTO {
    private Integer itemId;
    private String itemCatagory;
    private String itemName;
    private String price;
}
