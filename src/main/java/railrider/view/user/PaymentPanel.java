/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package railrider.view.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;
import railrider.standards.DatabaseHandler;
import railrider.standards.SessionManager;

/**
 *
 * @author MSI
 */
public class PaymentPanel extends javax.swing.JPanel {

    /**
     * Creates new form PaymentPanel
     */
     private final int trainId;
    private final String journeyDate;
    private final String name;
    private final String age;
    private final String gender;
    private final DatabaseHandler dbHandler;
    
    public PaymentPanel(int train_id,String date,String name,String age,String gender) throws SQLException {
        initComponents();
        this.trainId = train_id;
        this.journeyDate = date;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.dbHandler = new DatabaseHandler();
    }

    private String generatePRN() {
         Random rand = new Random();
    int min = 100000000; // Minimum 10-digit number
    int max = 999999999; // Maximum 10-digit number
    int randomNumber = rand.nextInt((max - min) + 1) + min;
    return String.valueOf(randomNumber);
    }

    private boolean insertTicket(String transactionId, String prn) {
    try {
        int userId = SessionManager.getSno(); // Retrieve the user ID dynamically
        String sql = "INSERT INTO tickets (user_id, train_id, prn, trans_id, journey_date, pname, age, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String[] parameters = {String.valueOf(userId), String.valueOf(trainId), prn, transactionId, journeyDate, name, age, gender};
        dbHandler.executeInsertPrepare(sql, parameters);
        return true;
    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;
    }
}


    private boolean insertPayment(String transactionId) {
    try {
        String fareQuery = "SELECT fair FROM trains WHERE train_id = ?";
        String[] fareParams = {String.valueOf(trainId)};
        ResultSet fareResult = dbHandler.executePrepare(fareQuery, fareParams);
        
        if (fareResult.next()) {
            int fare = fareResult.getInt("fair");
            int userId = SessionManager.getSno(); // Assuming SessionManager has a method to get the user ID
            
            String paymentInsertQuery = "INSERT INTO payments (user_id, trans_id, amount, train_id) VALUES (?, ?, ?, ?)";
            String[] paymentInsertParams = {String.valueOf(userId), transactionId, String.valueOf(fare), String.valueOf(trainId)};
            dbHandler.executeInsertPrepare(paymentInsertQuery, paymentInsertParams);
            return true;
        } else {
            // Train not found or fare not available
            System.out.println("Train fare not found for train ID: " + trainId);
            return false;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;
    }
}


    private boolean updateSeats() {
    try {
        String checkExistingRecordQuery = "SELECT * FROM seats WHERE train_id = ? AND date = ?";
        String[] checkExistingRecordParams = {String.valueOf(trainId), journeyDate};
        ResultSet existingRecordResult = dbHandler.executePrepare(checkExistingRecordQuery, checkExistingRecordParams);
        
        if (!existingRecordResult.next()) {
            // If no record exists for the train and date, fetch total seats from trains table
            String fetchTotalSeatsQuery = "SELECT total_seats FROM trains WHERE train_id = ?";
            String[] fetchTotalSeatsParams = {String.valueOf(trainId)};
            ResultSet totalSeatsResult = dbHandler.executePrepare(fetchTotalSeatsQuery, fetchTotalSeatsParams);
            
            if (totalSeatsResult.next()) {
                int totalSeats = totalSeatsResult.getInt("total_seats");
                
                // Insert a new row into the seats table with the total seats of that train
                String insertNewRecordQuery = "INSERT INTO seats (train_id, date, available_seats) VALUES (?, ?, ?)";
                String[] insertNewRecordParams = {String.valueOf(trainId), journeyDate, String.valueOf(totalSeats)};
                dbHandler.executeInsertPrepare(insertNewRecordQuery, insertNewRecordParams);
            }
        }

        // Update available seats
        String updateSeatsQuery = "UPDATE seats SET available_seats = available_seats - 1 WHERE train_id = ? AND date = ?";
        String[] updateSeatsParams = {String.valueOf(trainId), journeyDate};
        dbHandler.executeInsertPrepare(updateSeatsQuery, updateSeatsParams);

        return true;
    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;
    }
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        upiNumber = new javax.swing.JTextField();
        otp = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(858, 568));
        setMinimumSize(new java.awt.Dimension(858, 568));
        setPreferredSize(new java.awt.Dimension(858, 568));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(24, 119, 242));
        jLabel1.setText("Payment Gateway");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Enter A UPI Id");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("OTP");

        jButton1.setBackground(new java.awt.Color(255, 51, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("PAY");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(205, 205, 205)
                                .addComponent(otp, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(121, 121, 121)
                                .addComponent(upiNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(358, 358, 358)
                        .addComponent(jButton1)))
                .addContainerGap(172, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabel1)
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(upiNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(otp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(151, 151, 151))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
          String upiId = upiNumber.getText();
        String enteredOtp = otp.getText();

        // Validate UPI ID and OTP here (not implemented)

        // If validation passes, proceed with payment
        if (true) { // Implement your validation logic
            String transactionId = UUID.randomUUID().toString(); // Generate transaction ID
            String prn = generatePRN(); // Generate PRN
            if (insertTicket(transactionId, prn) && insertPayment(transactionId) && updateSeats()) {
                // Payment successful
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField otp;
    private javax.swing.JTextField upiNumber;
    // End of variables declaration//GEN-END:variables
}
