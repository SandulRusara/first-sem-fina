package lk.ijse.firstsemfinal.DTO;

import lombok.*;

import javax.xml.crypto.Data;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class ordersDTO {
    private Integer orderId;
    private String orderPayment;
    private String orderType;
    private String orderDate;
    private Integer customerId;
}
