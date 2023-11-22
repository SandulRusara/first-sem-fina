package lk.ijse.firstsemfinal.Model;

import lk.ijse.firstsemfinal.DTO.ordersDTO;
import lk.ijse.firstsemfinal.DbConnection.Dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderModel {
    public static boolean saveOrder(ordersDTO ordersDTO){
        try {
            Connection connection = Dbconnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert  into orders values (?,?,?,?,?)");
            preparedStatement.setInt(1,ordersDTO.getOrderId());
            preparedStatement.setString(2,ordersDTO.getOrderPayment());
            preparedStatement.setString(3,ordersDTO.getOrderType());
            preparedStatement.setString(4,ordersDTO.getOrderDate());
            preparedStatement.setInt(5,ordersDTO.getCustomerId());

            int i = preparedStatement.executeUpdate();
            return i>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public static ArrayList<ordersDTO> getAllOrdes(){
        ArrayList<ordersDTO> arrayList = new ArrayList<>();
        try {
            Connection connection = Dbconnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from orders");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ordersDTO ordersDTO = new ordersDTO(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5));
                arrayList.add(ordersDTO);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return arrayList;
    }
}
