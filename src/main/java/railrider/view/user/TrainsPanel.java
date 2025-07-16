/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package railrider.view.user;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import static railrider.RailRider.mainFrame;
import railrider.standards.DatabaseHandler;
import railrider.view.HomePanel;

/**
 *
 * @author MSI
 */
public class TrainsPanel extends javax.swing.JPanel {
  private String source;
    private String destination;
    private String date;
    private JPanel panel;

    public TrainsPanel(String s, String d, String datee) {
        initComponents();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.source = s;
        this.destination = d;
        this.date = datee;
        showTrains();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(panel);
        scrollPane.getViewport().setPreferredSize(new Dimension(858, 5688));
        add(scrollPane);
    }

    public void showTrains() {
    try {
        DatabaseHandler db = new DatabaseHandler();
        
        // Get the trains not in the cancelled dates for the given date
        String sql = "SELECT * FROM trains WHERE dep_station = ? AND arr_station = ? AND ? NOT IN (cancelled_dates)";
        String[] para = {source, destination, date};
        ResultSet rs = db.executePrepare(sql, para);
        
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(858, 5688));
        panel.setBackground(new Color(255, 255, 255));
        
        while (rs.next()) {
            int trainId = rs.getInt("train_id");
            int trainNumber = rs.getInt("train_number");
            String trainName = rs.getString("train_name");
            String depStation = rs.getString("dep_station");
            String arrStation = rs.getString("arr_station");
            String depTime = rs.getString("dep_time");
            String arrTime = rs.getString("arr_time");
            int totalSeats = rs.getInt("total_seats");
            int fair = rs.getInt("fair");
           

            // Check seat availability for the given date
            String seatSql = "SELECT * FROM seats WHERE train_id = ? AND date = ?";
            String[] seatPara = {String.valueOf(trainId), date};
            ResultSet seatRs = db.executePrepare(seatSql, seatPara);

            // If no seats found for the given train and date, assume all seats are available
            int availableSeats = totalSeats;
            if (seatRs.next()) {
                availableSeats = seatRs.getInt("available_seats");
            } else {
                // If no entry found for the given date, assume all seats are available
                availableSeats = totalSeats;
            }

            JPanel trainPanel = new JPanel();
            trainPanel.setLayout(new BoxLayout(trainPanel, BoxLayout.Y_AXIS));
            trainPanel.setBorder(BorderFactory.createEtchedBorder());
            trainPanel.setPreferredSize(new Dimension(850, 170));
            trainPanel.setMaximumSize(new Dimension(850, 170));
            trainPanel.setMinimumSize(new Dimension(850, 170));
            trainPanel.setBackground(new Color(250, 214, 165));

            JLabel nameLabel = new JLabel("                          Train Name:" + trainName);
            nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
            trainPanel.add(nameLabel);
            
            JLabel noLabel = new JLabel("                               Train Number:" + trainNumber);
            nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
            trainPanel.add(noLabel);

            JLabel depArrLabel = new JLabel("                          Departure: " + depStation + "  -  Arrival: " + arrStation);
            depArrLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            trainPanel.add(depArrLabel);

            JLabel timeLabel = new JLabel("                          Departure Time: " + depTime + "   -  Arrival Time: " + arrTime);
            timeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            trainPanel.add(timeLabel);

            JLabel seatsLabel = new JLabel("                          Total Seats: " + totalSeats);
            seatsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            trainPanel.add(seatsLabel);

            JLabel availableSeatsLabel = new JLabel("                         Available Seats: " + availableSeats);
            availableSeatsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            trainPanel.add(availableSeatsLabel);

            JLabel fairLabel = new JLabel("                         Fair: ₹" + fair);
            fairLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            trainPanel.add(fairLabel);

            JLabel bookLabel = new JLabel("                         Book Now");
            bookLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            bookLabel.setForeground(Color.BLUE);
            bookLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            trainPanel.add(bookLabel);

            bookLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Action to book the train
                    
     HomePanel hm = new HomePanel(trainId,date);
    mainFrame.setContentPane(hm);
    mainFrame.pack();
    mainFrame.setVisible(true);
                    
                }
            });

            panel.add(trainPanel);
        }

    } catch (SQLException e) {
        e.printStackTrace();
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

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(858, 568));
        setMinimumSize(new java.awt.Dimension(858, 568));
        setPreferredSize(new java.awt.Dimension(858, 568));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 858, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 568, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
