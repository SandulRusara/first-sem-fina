package lk.ijse.firstsemfinal.DTO;

import lombok.*;
import org.w3c.dom.Text;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class supplierDTO {
    private Integer supplierId;
    private String supplierName;
    private String supplierAddress;
    private String supplierContact;
}
