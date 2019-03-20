package app.db;

import app.Entities.User;
import app.Entities.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public static void changeBalance(int id, String accType, int transactionType, double amount) {
        double newBalance;

        PreparedStatement ps = prep("SELECT balance FROM accounts WHERE accOwner = ? AND accType = ?");
        PreparedStatement ps2 = prep("UPDATE accounts SET balance = 'newBalance' WHERE accOwner = ? AND accType = ?");

        try {
            ps.setInt(1, id);
            ps.setString(2, accType);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                ps2.setInt(1, id);
                ps2.setString(2, accType);
                switch (transactionType) {
                    case 1: //withdrawal
                        newBalance = (result.getLong("balance") - amount);
                        ps2.executeUpdate();
                        break;
                    case 2: //deposit
                        newBalance = (result.getLong("balance") + amount);
                        ps2.executeUpdate();
                        break;
                    default:
                        System.out.println("oops!");
                        break;
                }
            }
        } catch (Exception e) {
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