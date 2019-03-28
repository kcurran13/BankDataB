package app.db;


import app.Entities.Transaction;
import app.Entities.User;
import app.transaction.TransactionController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;

import java.sql.*;
import java.util.List;
import java.util.Random;

/**
 * A Helper class for interacting with the Database using short-commands
 */
public abstract class DB {
    private static double newBalance;

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
        return result;
    }

    public static double changeBalance(String fromAccNo, double amount, String receiverAcc, Timestamp date) {
        PreparedStatement ps = prep("SELECT balance FROM accounts WHERE AccNo = ?");
        PreparedStatement ps2 = prep("UPDATE accounts SET balance = ? WHERE AccNo = ?");

        try {
            ps.setString(1, fromAccNo);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                newBalance = (result.getLong("balance") + amount);
                if (newBalance >= 0) {
                    ps2.setDouble(1, newBalance);
                    ps2.setString(2, fromAccNo);
                    ps2.executeUpdate();
                } else {
                    newBalance = result.getLong("balance");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        createTransaction(fromAccNo, date, amount, receiverAcc, newBalance);
        return newBalance;
    }

    public static void planTransaction(String fromAccNo, double amount, String receiverAcc, Timestamp date) {
        PreparedStatement ps = prep("INSERT INTO plannedTransactions (Receiver, AccNo, TransAmount, Date) VALUES (?,?,?,?)");
        try {
            ps.setString(1, receiverAcc);
            ps.setString(2, fromAccNo);
            ps.setDouble(3, amount);
            ps.setTimestamp(4, date);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void checkForPlannedTransactions(){
        List<Transaction> plannedTransactions  = null;
        PreparedStatement ps = prep("SELECT AccNo, Receiver, TransAmount, Date FROM plannedTransactions WHERE date <= CURDATE()");
        Timestamp date = new Timestamp(System.currentTimeMillis());

            try {
                plannedTransactions = (List<Transaction>) (List<?>) new ObjectMapper<>(Transaction.class).map(ps.executeQuery());
            } catch (SQLException e) {
                System.out.println(e);
            }

            if (plannedTransactions != null){
                for (Transaction t: plannedTransactions){
                    changeBalance(t.getAccNo(), (t.getAmount()*-1), t.getReceiver(), date);
                    changeBalance(t.getReceiver(), t.getAmount(), t.getAccNo(), date);
                    deletePlannedTransaction(t);
                }
            }
    }

    public static void deletePlannedTransaction(Transaction t) {
        PreparedStatement ps = prep("DELETE FROM plannedTransactions WHERE Receiver = ? AND date = ?");

        try {
            ps.setString(1, t.getReceiver());
            ps.setTimestamp(2, t.getDate());

            ps.executeUpdate();
        } catch(Exception e) {
            System.out.println(e);
        }

    }

    public static void createTransaction(String accNo, Timestamp date, double amount, String receiver, double newBalance) {
        PreparedStatement ps = prep("INSERT INTO transactions (accno, date, transamount, receiver, balance) VALUES (?,?,?,?,?)");

        try {
            ps.setString(1, accNo);
            ps.setTimestamp(2, date);
            ps.setDouble(3, amount);
            ps.setString(4, receiver);
            ps.setString(5, String.valueOf(newBalance));

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static ObservableList<String> getUserAccounts(User user) throws SQLException {
        PreparedStatement ps = prep("SELECT accno, accname FROM accounts WHERE AccOwner = ?");
        ObservableList<String> data = FXCollections.observableArrayList();

        ps.setLong(1, user.getUserId());
        try {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String number = rs.getString(1);
                String name = rs.getString(2);
                data.add(number + "-" + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void changeAccName(String account, String newName) {
        PreparedStatement ps = prep("UPDATE accounts SET accname = ? WHERE accno = ?");
        try {
            ps.setString(1, newName);
            ps.setString(2, account);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void newAccount(User user, String accName) {
        Random random = new Random();
        String id = String.format("%08d", random.nextInt(99999999));

        PreparedStatement ps = prep("INSERT INTO accounts VALUES (?,?,?,?,?)");
        try {
            ps.setString(1, id);
            //3000 is always the clearing no for this bank
            ps.setInt(2, 3000);
            ps.setLong(3, user.getUserId());
            ps.setDouble(4, 0);
            ps.setString(5, accName);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void removeAcc(String accNo) {
        PreparedStatement ps = prep("DELETE FROM accounts WHERE accno = ?");

        try {
            ps.setString(1, accNo);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static List<Transaction> getTransactions(String accountId) {
        return getTransactions(accountId, 10, 0);
    }

    public static List<Transaction> getTransactions(String accountId, int offset) {
        return getTransactions(accountId, offset + 10, offset);
    }

    public static List<Transaction> getTransactions(String accountId, int limit, int offset) {
        List<Transaction> result = null;
        PreparedStatement ps = prep("SELECT date, transamount, receiver, balance FROM transactions WHERE accno = ? LIMIT ? OFFSET ?");

        try {
            ps.setString(1, accountId);
            ps.setInt(2, limit);
            ps.setInt(3, offset);

            result = (List<Transaction>) (List<?>) new ObjectMapper<>(Transaction.class).map(ps.executeQuery());

        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}