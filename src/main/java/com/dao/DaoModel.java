package com.dao;

import com.database.DbConnection;
import com.model.Model;
import com.util.HashingFunction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoModel {
    static Connection connection;
    static int isLoggedIn;
    int noOfRecords;

    public void registerUser(Model model) {
        String sql = "insert into usertable (userName, nic, mobileNumber, gender, password,tempPass) values (?, ?, ?, ?, ?,?)";
        try {
            connection = DbConnection.getConnection();
            //hashing the password
            String userPasssword = model.getPassword();
            String hashedPassword = HashingFunction.convertByteToString(HashingFunction.createHash(userPasssword));

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, model.getUserName());
            preparedStatement.setString(2, model.getNic());
            preparedStatement.setString(3, model.getMobileNumber());
            preparedStatement.setString(4, model.getGender());
            preparedStatement.setString(5, hashedPassword);
            preparedStatement.setInt(6,model.getTempPass());
            preparedStatement.executeUpdate();
            System.out.println("Data has been send to db");
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Data hasn't been sent to db");
        }
    }

    public Model loginUser(Model model) throws SQLException, ClassNotFoundException {
        String sql = "select * from usertable where userName = ? and password = ?";
        Model model1 = new Model();
        try {

            connection = DbConnection.getConnection();
            HashingFunction hashPassword = new HashingFunction();

            //hashing the password
            String userPassword = model.getPassword();
            String hashedPassword = HashingFunction.convertByteToString(HashingFunction.createHash(userPassword));

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, model.getUserName());
            preparedStatement.setString(2, hashedPassword);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
               // System.out.println("User can login");
                int id = resultSet.getInt("id");
                int tempId = resultSet.getInt("tempPass");
                model.setId(id);
                model.setTempPass(tempId);


            } else {
                System.out.println("User cannot login");
                model.setId(0);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Data hasn't been delivered to db to check login");
        }
        return model;
    }

    public List<Model> getUserId(Model model) throws SQLException, ClassNotFoundException {
        String sql = "select * from usertable where userName = ?";
        List<Model> userId = new ArrayList<>();
        try {

            connection = DbConnection.getConnection();
            //HashingFunction hashPassword = new HashingFunction();

            //hashing the password
            //String userPassword = model.getPassword();
            //String hashedPassword = hashPassword.convertByteToString(hashPassword.createHash(userPassword));

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, model.getUserName());
            //preparedStatement.setString(2, hashedPassword);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                userId.add(new Model(id));

            } else {
                System.out.println("User cannot login");
                isLoggedIn = 0;
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Data hasn't been delivered to db to check login");
        }
        return userId;
    }

    public List<Model> displayTable() throws SQLException, ClassNotFoundException {
        List<Model> listOfAcc = new ArrayList<>();
        String sql = "select * from usertable";


        try {
            connection = DbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("userName");
                String nic = resultSet.getString("nic");
                String mobileNumber = (resultSet.getString("mobileNumber"));
                String gender = resultSet.getString("gender");
                listOfAcc.add(new Model(id, username, nic, mobileNumber, gender));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listOfAcc;
    }

    public Model editAccountDetails(Model model) {
        String sql = "select * from usertable where  id = ?";

        try {
            connection = DbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //preparedStatement.setString(1, model.getUserName());
            preparedStatement.setInt(1, model.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("userName");
                String nic = resultSet.getString("nic");
                String mobileNumber = resultSet.getString("mobileNumber");
                String gender = resultSet.getString("gender");

                model = new Model(id, username, nic, mobileNumber, gender);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    public void updateAccount(Model model) {
        String sql = "update usertable set userName = ?, nic = ?,  mobileNumber = ?, gender = ? where id = ?";

        try {
            connection = DbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, model.getUserName());
            preparedStatement.setString(2, model.getNic());
            preparedStatement.setString(3, model.getMobileNumber());
            preparedStatement.setString(4, model.getGender());
            preparedStatement.setInt(5, model.getId());
            preparedStatement.executeUpdate();
            System.out.println("Data has been updated ");
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("data could not be updated");
        }
    }

    public void deleteAccount(Model model) {
        String sql = "delete from usertable where id = ?";
        try {
            connection = DbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, model.getId());
            preparedStatement.executeUpdate();
            System.out.println("account deleted");
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("could not be deleted");
        }
    }

    public List<Model> pagination(int pageid, int total, String sortColumn, int userid,String order) {
        String sql = "select  * from usertable where id != " + userid + " order by "+sortColumn+" "+order+" limit " + (pageid - 1) + ", " + total;
        List<Model> accounts = new ArrayList<Model>();

        try {
            connection = DbConnection.getConnection();
            PreparedStatement Statement = connection.prepareStatement(sql);
            ResultSet resultSet = Statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("userName");
                String nic = resultSet.getString("nic");
                String mobileNumber = (resultSet.getString("mobileNumber"));
                String gender = (resultSet.getString("gender"));
                accounts.add(new Model(id, username, nic, mobileNumber, gender));
            }
            connection.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public int NoOfRecords() {
        try {
            connection = DbConnection.getConnection();
            Statement preparedStatement = connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery("select count(*) from usertable");
            if (resultSet.next()) {
                this.noOfRecords = resultSet.getInt(1);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noOfRecords;
    }

    public List<Model> searchOption(String search, int id) {
        String sql = "select * from usertable where (userName  like  '" + search + "%' or mobileNumber like  '" + search + "%' or nic like '" + search + "%') and id != " + id + "";

        List<Model> searchedValues = new ArrayList<>();
        Model model = new Model();

        try {
            connection = DbConnection.getConnection();
            //Statement Statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
          /*  preparedStatement.setString(1, search);
            preparedStatement.setString(2, search);*/
            //ResultSet resultSet = Statement.executeQuery(sql);
            ResultSet resultSet1 = preparedStatement.executeQuery();
            while (resultSet1.next()) {
                int idFromSql = resultSet1.getInt("id");
                String username = resultSet1.getString("userName");
                String nic = resultSet1.getString("nic");
                String mobileNumber = (resultSet1.getString("mobileNumber"));
                String gender = (resultSet1.getString("gender"));
                searchedValues.add(new Model(idFromSql, username, nic, mobileNumber, gender));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchedValues;
    }

    public List downloadPDF() {
        String sql = "select * from usertable";
        Model objBean;
        List<Model> dataList = new ArrayList<Model>(0);

        try {
            connection = DbConnection.getConnection();
            //Statement Statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                objBean = new Model();
                int id = resultSet.getInt("id");
                String userName = (resultSet.getString("userName"));
                String nic = resultSet.getString("nic");
                String mobileNumber = (resultSet.getString("mobileNumber"));
                String gender = (resultSet.getString("gender"));

                dataList.add(new Model(id, userName, nic, mobileNumber, gender));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // System.out.println(dataList);
        return dataList;
    }

    public void updatePassword(Model model){
        String sql = "update usertable set password = ?, tempPass = ? where id = ?";

        try {
            connection = DbConnection.getConnection();
            //hashing the password
            String userPasssword = model.getPassword();
            String hashedPassword = HashingFunction.convertByteToString(HashingFunction.createHash(userPasssword));
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, hashedPassword);
            preparedStatement.setInt(2, model.getTempPass());
            preparedStatement.setInt(3, model.getId());
            preparedStatement.executeUpdate();
            System.out.println("password has been updated ");
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("password could not be updated");
        }
    }



}
