package lk.ijse.firstsemfinal.Model;

import lk.ijse.firstsemfinal.DTO.itemDTO;
import lk.ijse.firstsemfinal.DbConnection.Dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModle {
    public static boolean saveItem(itemDTO itemDto) throws SQLException {
       Connection connection= Dbconnection.getInstance().getConnection();
       PreparedStatement preparedStatement=connection.prepareStatement("insert into item values(?,?,?,?)");
       preparedStatement.setInt(1,itemDto.getItemId());
       preparedStatement.setString(2,itemDto.getItemCatagory());
       preparedStatement.setString(3,itemDto.getItemName());
       preparedStatement.setString(4,itemDto.getPrice());

       boolean isSaved=preparedStatement.executeUpdate()>0;
       return isSaved;
    }

    public static ArrayList<itemDTO> getAllItem() throws SQLException {
        ArrayList<itemDTO>arrayList=new ArrayList<>();
        try {
            Connection connection=Dbconnection.getInstance().getConnection();
            PreparedStatement pstm=connection.prepareStatement("select * from item");
            ResultSet resultSet=pstm.executeQuery();
            while (resultSet.next()){
                itemDTO itemDTO=new itemDTO(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)

                );
                arrayList.add(itemDTO);
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return arrayList;
    }
}
