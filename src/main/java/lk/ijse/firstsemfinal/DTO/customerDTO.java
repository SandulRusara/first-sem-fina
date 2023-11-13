package lk.ijse.firstsemfinal.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class customerDTO {
    private Integer customerId;
    private String customerName;
    private String custAddrss;
    private String customerContact;
    private String date;
    private String email;

}
