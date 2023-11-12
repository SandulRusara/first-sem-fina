package lk.ijse.firstsemfinal.Model;

import lk.ijse.firstsemfinal.DTO.userDTO;
import lk.ijse.firstsemfinal.DbConnection.Dbconnection;

import java.awt.image.DataBuffer;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserModel {
    public static Boolean saveUser(userDTO userDTO){
        try {
            Connection connection = Dbconnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user VALUES (?,?,?,?)");
            preparedStatement.setInt(1,0);
            preparedStatement.setString(2, userDTO.getUseName());
            preparedStatement.setString(3,userDTO.getPassword());
            preparedStatement.setString(4,userDTO.getEmail());
            int i = preparedStatement.executeUpdate();
            return i>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public static ArrayList<userDTO> getAllUser(){
        ArrayList<userDTO> userDTOS = new ArrayList<>();
        try {
            Connection connection = Dbconnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                userDTO user = new userDTO(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
                userDTOS.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userDTOS;
    }
}
