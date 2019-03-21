package app.db;

import app.Entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import static javax.swing.UIManager.getString;

/**
 * A Helper class for interacting with the Database using short-commands
 */
public abstract class DB {

    public static PreparedStatement prep(String SQLQuery) {
        return Database.getInstance().prepareStatement(SQLQuery);
    }

    public static User getMatchingUser(String username, String password) {
        User result = null;
        PreparedStatement ps = prep("SELECT * FROM users WHERE UserLogIn = ? AND UserPass = ?");
        try {
            ps.setString(1, username);
            ps.setString(2, password);
            result = (User) new ObjectMapper<>(User.class).mapOne(ps.executeQuery());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result; // return User;
    }

    public static double changeBalance(int clearingNo, int accNo, double amount) {
        double newBalance = 0;

        PreparedStatement ps = prep("SELECT balance FROM accounts WHERE AccNo = ? AND ClearingNo = ?");
        PreparedStatement ps2 = prep("UPDATE accounts SET balance = ? WHERE AccNo = ? AND ClearingNo = ?");

        try {
            ps.setInt(1, accNo);
            ps.setInt(2, clearingNo);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                newBalance = (result.getLong("balance") + amount);
                if (newBalance >= 0) {
                    ps2.setDouble(1, newBalance);
                    ps2.setInt(2, accNo);
                    ps2.setInt(3, clearingNo);
                    ps2.executeUpdate();
                } else {
                    newBalance = result.getLong("balance");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return newBalance;
    }

    public static ResultSet getUserAccounts(User user) throws SQLException {
        PreparedStatement ps = prep("SELECT AccNo FROM accounts WHERE AccOwner = ?");

        ps.setLong(1, user.getUserId());
        ResultSet result = ps.executeQuery();
        return result;
    }

    public static void changeAccName(int account, String newName) {
        PreparedStatement ps = prep("UPDATE accounts SET accname = ? WHERE accno = ?");
        try {
            ps.setString(1, newName);
            ps.setInt(2, account);
            ps.executeUpdate();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void newAccount(User user, String accName) {
        Random random = new Random();
        String id = String.format("%08d", random.nextInt(10000));
        System.out.println(id);

        PreparedStatement ps = prep("INSERT INTO accounts VALUES (?,?,?,?,?)");
        try{
            ps.setString(1, id);
            //3000 is always the clearing no for this bank
            ps.setInt(2, 3000);
            ps.setLong(3, user.getUserId());
            ps.setDouble(4, 0);
            ps.setString(5, accName);

            ps.executeUpdate();
        }catch(Exception e) {
            System.out.println(e);
        }
    }

    /*
        Example method with default parameters
    public static List<Transaction> getTransactions(int accountId){ return getTransactions(accountId, 0, 10); }
    public static List<Transaction> getTransactions(int accountId, int offset){ return getTransactions(accountId, offset, offset + 10); }
    public static List<Transaction> getTransactions(int accountId, int offset, int limit){
        List<Transaction> result = null;
        PreparedStatement ps = prep("bla bla from transactions WHERE account-id = "+accountId+" OFFSET "+offset+" LIMIT "+limit);
        try {
            result = (List<Transaction>)new ObjectMapper<>(Transaction.class).map(ps.executeQuery());
        } catch (Exception e) { e.printStackTrace(); }
        return result; // return User;
    }
    */


}