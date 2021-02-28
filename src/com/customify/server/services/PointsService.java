//package com.customify.server.services;
//
//import com.customify.server.Db.Db;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
///**
// * @author GISA KAZE Fredson
// */
//public class PointsService {
//    public static boolean recordPointsAfterSale(SaleDataFormat saleDataFormat) throws SQLException {
//        try {
//            Connection connection = Db.getConnection();
//
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT bonded_points FROM products WHERE products.id =?");
//            preparedStatement.setInt(1, saleDataFormat.getProductId());
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            float product_points = 0;
//            if (preparedStatement.executeUpdate() == 0) return false;
//
//
//            while (resultSet.next()) {
//                product_points = saleDataFormat.getQuantity() * resultSet.getFloat("bonded_points");
//            }
//
//            preparedStatement = connection.prepareStatement("INSERT INTO Points(customer_id,number_of_points,`source`) VALUES (?,?,'product')");
//            preparedStatement.setInt(1, saleDataFormat.getCustomerId());
//            preparedStatement.setFloat(2, product_points);
//
//            if (preparedStatement.executeUpdate() == 0) return false;
//
//            preparedStatement  = connection.prepareStatement("UPDATE Points_winning SET no_points = no_points+? WHERE customer_id = ?");
//            preparedStatement.setFloat(1,product_points);
//            preparedStatement.setInt(2,saleDataFormat.getCustomerId());
//
//            if (preparedStatement.executeUpdate() < 1){
//                preparedStatement  = connection.prepareStatement("INSERT INTO Points_winning(customer_id,no_points) values (?,?)");
//                preparedStatement.setInt(1,saleDataFormat.getCustomerId());
//                preparedStatement.setFloat(2,product_points);
//            }
//
//            return true;
//        }
//        catch (SQLException e){
//            System.out.println(e.getMessage());
//            return false;
//        }
//    }
//
//    public String customer() {
//        try {
//            ResultSet product = Db.getStatement().executeQuery("SELECT * FROM products");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return "Home";
//    }
//}
