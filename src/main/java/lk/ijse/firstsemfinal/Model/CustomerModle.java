package lk.ijse.firstsemfinal.Model;

import lk.ijse.firstsemfinal.DTO.customerDTO;
import lk.ijse.firstsemfinal.DbConnection.Dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModle {
  /*  public static boolean savaCustomer(customerDTO customerDTO){
        try {
            Connection connection = Dbconnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into customer values (?,?,?,?,?)");
            preparedStatement.setInt(1, customerDTO.getCustomerId());
            preparedStatement.setString(2,customerDTO.getCustomerName());
            preparedStatement.setString(3,customerDTO.getCustAddrss());
            preparedStatement.setString(4,customerDTO.getCustAddrss());
            preparedStatement.setString(5,customerDTO.getDate());
            int i = preparedStatement.executeUpdate();
            return i>0;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }*/
    public static ArrayList<customerDTO> getAllCustomers(){
        ArrayList<customerDTO> arrayList = new ArrayList<>();
        try {
            Connection connection = Dbconnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from customer");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                customerDTO customerDTO = new customerDTO(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6));


                arrayList.add(customerDTO);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return arrayList;
    }

    public static boolean saveCustomer(customerDTO customerDTO) throws SQLException {
        Connection connection=Dbconnection.getInstance().getConnection();
       PreparedStatement pstm= connection.prepareStatement("insert into customer values (?,?,?,?,?,?)");

       pstm.setInt(1,customerDTO.getCustomerId());
       pstm.setString(2,customerDTO.getCustomerName());
       pstm.setString(3,customerDTO.getCustAddrss());
       pstm.setString(4,customerDTO.getCustomerContact());
       pstm.setString(5,customerDTO.getDate());
       pstm.setString(6,customerDTO.getEmail());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;


    }
    public static boolean deleteButton(int customerid){

        try {
            Connection connection=Dbconnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from customer where customerId = ?");
            preparedStatement.setInt(1,customerid);
            int i = preparedStatement.executeUpdate();
            return i>0;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;

    }
}
