package lk.ijse.firstsemfinal.Model;

import lk.ijse.firstsemfinal.DTO.customerDTO;
import lk.ijse.firstsemfinal.DTO.itemDTO;
import lk.ijse.firstsemfinal.DTO.supplierDTO;
import lk.ijse.firstsemfinal.DbConnection.Dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class supplierModle {
    public static boolean saveSupplier(supplierDTO supplierDTO) throws SQLException {
        Connection connection= Dbconnection.getInstance().getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("insert into supplier values(?,?,?,?)");
        preparedStatement.setInt(1,supplierDTO.getSupplierId());
        preparedStatement.setString(2,supplierDTO.getSupplierName());
        preparedStatement.setString(3,supplierDTO.getSupplierAddress());
        preparedStatement.setString(4,supplierDTO.getSupplierContact());
        boolean isSaved=preparedStatement.executeUpdate()>0;
        return isSaved;
    }
    public static ArrayList<supplierDTO> getAllSupplier(){
        ArrayList<supplierDTO> arrayList = new ArrayList<>();
        try {
            Connection connection = Dbconnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from supplier");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                supplierDTO supplierDTO = new supplierDTO(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4));
                arrayList.add(supplierDTO);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return arrayList;
    }
    public static boolean deleteButton(int supplierId){

        try {
            Connection connection=Dbconnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from supplier where supplierId = ?");
            preparedStatement.setInt(1,supplierId);
            int i = preparedStatement.executeUpdate();
            return i>0;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;

    }
    public static boolean updateSupplier(supplierDTO supplierDTO){
        try {
            Connection connection = Dbconnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(" update supplier set supplierContact = ? , supplierAddress = ? , supplierName = ? where supplierId = ?");
            preparedStatement.setString(1,supplierDTO.getSupplierContact());
            preparedStatement.setString(2,supplierDTO.getSupplierAddress());
            preparedStatement.setString(3,supplierDTO.getSupplierName());
            preparedStatement.setString(4, String.valueOf(supplierDTO.getSupplierId()));

            int i = preparedStatement.executeUpdate();
            return i > 0;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }


}
