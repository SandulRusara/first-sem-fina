package lk.ijse.firstsemfinal.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class ingredientDTO {
    private Integer ingredientId;
    private String ingredientName;
    private Integer qtyOnHand;
}
