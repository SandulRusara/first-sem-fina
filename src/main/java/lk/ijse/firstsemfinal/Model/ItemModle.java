package lk.ijse.firstsemfinal.Model;

import lk.ijse.firstsemfinal.DTO.itemDTO;
import lk.ijse.firstsemfinal.DbConnection.Dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModle {
    public static boolean updateItem(itemDTO itemDTO){
        try {
            Connection connection = Dbconnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(" update item set itemCategory = ? , itemName = ? , itemPrice = ? where itemId = ?");
            preparedStatement.setString(1,itemDTO.getItemCategory());
            preparedStatement.setString(2,itemDTO.getItemName());
            preparedStatement.setString(3,itemDTO.getPrice());
            preparedStatement.setInt(4,itemDTO.getItemId());

            int i = preparedStatement.executeUpdate();
            return i > 0;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public static boolean saveItem(itemDTO itemDto) throws SQLException {
       Connection connection= Dbconnection.getInstance().getConnection();
       PreparedStatement preparedStatement=connection.prepareStatement("insert into item values(?,?,?,?,?)");
       preparedStatement.setInt(1,itemDto.getItemId());
       preparedStatement.setString(2,itemDto.getItemCategory());
       preparedStatement.setString(3,itemDto.getItemName());
       preparedStatement.setString(4,itemDto.getPrice());
       preparedStatement.setString(5,itemDto.getType());

       boolean isSaved=preparedStatement.executeUpdate()>0;
       return isSaved;
    }

    public static ArrayList<itemDTO> getAllItem(String itemType) throws SQLException {
        ArrayList<itemDTO>arrayList=new ArrayList<>();
        try {
            Connection connection=Dbconnection.getInstance().getConnection();
            PreparedStatement pstm=connection.prepareStatement(" select * from item where type=?");
            pstm.setString(1,itemType);
            ResultSet resultSet=pstm.executeQuery();
            while (resultSet.next()){
                itemDTO itemDTO=new itemDTO(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)

                );
                arrayList.add(itemDTO);
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return arrayList;
    }
    public static boolean deleteButton(int Id) throws SQLException {
        try {
            Connection connection= Dbconnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("delete from item where itemId = ?");
            pstm.setInt(1,Id);
            int i = pstm.executeUpdate();
            return i>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;

    }
}
