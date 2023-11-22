package lk.ijse.firstsemfinal.Model;

import lk.ijse.firstsemfinal.DTO.OrderItemDTO;
import lk.ijse.firstsemfinal.DbConnection.Dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDetailModel {

    public static boolean saveOrderDetail(OrderItemDTO orderItemDTO){
        try {
            Connection connection = Dbconnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into orderitemdetails values (?,?)");
            preparedStatement.setInt(1,orderItemDTO.getOId());
            preparedStatement.setInt(2,orderItemDTO.getItemID());

            int i = preparedStatement.executeUpdate();
            return i>0;
        }catch (SQLException e){
         e.printStackTrace();
        }
        return false;
    }
}
