package lk.ijse.firstsemfinal.Model;

import lk.ijse.firstsemfinal.DTO.employeeDTO;
import lk.ijse.firstsemfinal.DbConnection.Dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeModle {
    public static ArrayList<employeeDTO> getAllEmployee() throws SQLException {
        ArrayList<employeeDTO> arrayList = new ArrayList();
        try {
            Connection connection = Dbconnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("select * from employee");
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                employeeDTO employeeDTO = new employeeDTO(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getString(6));
                arrayList.add(employeeDTO);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return arrayList;

    }

    public static boolean saveEmployee(employeeDTO employeeDTO) throws SQLException {
        Connection connection=Dbconnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("insert into employee values (?,?,?,?,?,?)");

        pstm.setInt(1,employeeDTO.getEmployeeId());
        pstm.setString(2,employeeDTO.getEmployeeName());
        pstm.setString(3,employeeDTO.getEmployeeAddress());
        pstm.setString(4,employeeDTO.getEmployeeContact());
        pstm.setInt(5,employeeDTO.getUserId());
        pstm.setString(6,employeeDTO.getNic());

        boolean isSaved = pstm.executeUpdate()>0;
        return isSaved;
    }
    public static boolean deleteButton(int employeeid) throws SQLException {
        try {
            Connection connection= Dbconnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("delete from employee where employeeId = ?");
            pstm.setInt(1,employeeid);
            int i = pstm.executeUpdate();
            return i>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
       return false;

    }
}
