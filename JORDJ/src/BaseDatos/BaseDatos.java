/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import java.util.ArrayList;
import Estructuras.*;
import conSql.ConectaBD;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Miquel Ferriol
 */
public class BaseDatos {
    private ArrayList<Regata> BD;
    private ArrayList<Cjt_Bolla> BDB;
    private Connection cn;
    ConectaBD db;
    String ip;
    
    public BaseDatos(String ip){
        this.ip = ip;
        db = new ConectaBD();
    }
    
    public void initBD(){
        BD = new ArrayList<>();
        BDB = new ArrayList<>();
        //System.out.println("Conectados");
        String sql = "select * from racestatus";
        try {
            cn = db.getConnection(ip);
            PreparedStatement st1 = cn.prepareStatement(sql);
            ResultSet rs = st1.executeQuery();
            while (rs.next()) {
                Regata r = new Regata();
                r.setId(rs.getInt(1));
                r.setClas(rs.getString(2));
                r.setGroup(rs.getString(3));
                r.setRace(rs.getInt(4));
                r.setScheduledDate(rs.getString(5));
                r.setRealDate(rs.getString(6));
                r.setEntries(rs.getInt(7));
                r.setArea(rs.getString(8));
                r.setCommittee(rs.getString(9));
                r.setRaceStatus(rs.getString(10));
                r.setSignal(rs.getString(11));
                r.setTime(rs.getString(12));
                r.setScheduledTime(rs.getString(13));
                r.setStartingTime(rs.getString(14));
                r.setBoatsStarted(rs.getInt(15));
                r.setPreparatorySignal(rs.getString(16));
                r.setOCS_DSQ(rs.getInt(17));
                r.setAP(rs.getInt(18));
                r.setGR(rs.getInt(19));
                r.setFinishTime(rs.getString(20));
                r.setRaceTime(rs.getString(21));
                r.setBoatsFinished(rs.getInt(22));
                r.setLastSignal(rs.getString(23));
                r.setLastSignalTime(rs.getString(24));
                r.setResults(rs.getString(25));
                r.setCourse(rs.getString(26));
                r.setDistance1stLeg(Double.parseDouble(rs.getString(27)));
                r.setBearing1stLeg(rs.getInt(28));
                r.setLegChanges(rs.getString(29));
                r.setWindDir(rs.getInt(30));
                r.setWindSpeed(Double.parseDouble(rs.getString(31)));
                r.setWindDir25(rs.getInt(32));
                r.setWindSpeed25(Double.parseDouble(rs.getString(33)));
                r.setWindDir50(rs.getInt(34));
                r.setWindSpeed50(Double.parseDouble(rs.getString(35)));
                r.setWindDir75(rs.getInt(36));
                r.setWindSpeed75(Double.parseDouble(rs.getString(37)));
                r.setWindDir100(rs.getInt(38));
                r.setWindSpeed100(Double.parseDouble(rs.getString(39)));
                r.setVisible(rs.getBoolean(52));
                r.setVisible1(rs.getBoolean(53));
                Bolla b1 = new Bolla(rs.getString(40));
                Bolla b2 = new Bolla(rs.getString(41));
                Bolla b3 = new Bolla(rs.getString(42));
                Bolla b4 = new Bolla(rs.getString(43));
                Bolla b5 = new Bolla(rs.getString(44));
                Bolla b6 = new Bolla(rs.getString(45));
                Bolla b7 = new Bolla(rs.getString(46));
                Bolla b8 = new Bolla(rs.getString(47));
                Bolla b9 = new Bolla(rs.getString(48));
                Bolla b10 = new Bolla(rs.getString(49));
                Bolla b11 = new Bolla(rs.getString(50));
                Bolla b12 = new Bolla(rs.getString(51));
                Cjt_Bolla c = new Cjt_Bolla();
                c.add(b1);
                c.add(b2);
                c.add(b3);
                c.add(b4);
                c.add(b5);
                c.add(b6);
                c.add(b7);
                c.add(b8);
                c.add(b9);
                c.add(b10);
                c.add(b11);
                c.add(b12);
                BDB.add(c);
                BD.add(r);
            }
            cn.close();
            
            cn = db.getConnection(ip);
            String sql1 = "select * from areas";
            PreparedStatement st = cn.prepareStatement(sql1);
            ResultSet rs1 = st.executeQuery();
            int i = 0;
            while(rs1.next()){
                String s = rs1.getString(2);
                Color col;
                switch(s){
                    case "Yellow":
                        col = Color.YELLOW;
                        break;
                    case "Red":
                        col = Color.RED;
                        break;
                    case "Green":
                        col = Color.GREEN;
                        break;
                    case "Orange":
                        col = Color.ORANGE;
                        break;
                    case "Blue":
                        col = Color.BLUE;
                        break;
                    case "Pink":
                        col = Color.PINK;
                        break;
                    default:
                        col = Color.WHITE;
                }
                GlobalVariable.COLOR.set(GlobalVariable.AREA.indexOf(rs1.getString(1)),col); 
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public ArrayList<Regata> getBD(){
        return BD;
    }

    public ArrayList<Cjt_Bolla> getBDB() {
        return BDB;
    }
    
    public void Update(int id, String col, Object value){
        cn = db.getConnection(ip);
        try {
            //String sql = "UPDATE usuario SET P_Nombre='"+txtNombres.getText()+"',S_Nombre=' "+txtSgnNombre.getText()+"',P_Apellido='"+txtApellidos.getText()+"',S_Apellido=' "+ txtSgnApellido.getText()+"',Telefono='"+txtTelefono.getText()+"'WHERE N_Documento='"+txtnDocumento"';";
            String sql = "UPDATE racestatus " + "SET " + col + " = '" + value + "' WHERE id = " + id + ";";
            System.out.println(sql);
            PreparedStatement st = cn.prepareStatement(sql);
            st.executeUpdate();
            cn.close();
        } catch (SQLException e) {
            try {
            String sql = "UPDATE racestatus " + "SET " + col + " = -1 WHERE id = " + id + ";";
            System.out.println(sql);
            PreparedStatement st = cn.prepareStatement(sql);
            st.executeUpdate();
            cn.close();
            
            } catch (SQLException q) {
            
                System.out.println("Error: " + q.getMessage());
            }
            System.out.println("Error: " + e.getMessage());
        }
        
        
    }
    
    public void Insert() throws SQLException{
        cn = db.getConnection(ip);
        try {
            
        for(int i = 9; i <= 121; ++i){
            String sql = "INSERT INTO `racestatus` (`id`, `Class`, `Race`, `ScheduledDate`, `RealDate`, `Entries`, `Area`, `Committee`, `RaceStatus`, `Signall`, `Time`, `ScheduledTime`, `StartingTime`, `BoatsStarted`, `PreparatorySignal`, `OCS_DSQ`, `AP`, `GR`, `FinishTime`, `RaceTime`, `BoatsFinished`, `LastSignal`, `LastSignalTime`, `Results`, `Course`, `Distance1stLeg`, `Bearing1stLeg`, `LegChanges`, `WindDir`, `WindSpeed`, `WindDir25`, `WindSpeed25`, `WindDir50`, `WindSpeed50`, `WindDir75`, `WindSpeed75`, `WindDir100`, `WindSpeed100`) VALUES (" + i + ", NULL, '-1', NULL, NULL, '-1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '-1', NULL, '-1', '-1', '-1', NULL, NULL, '-1', NULL, NULL, NULL, NULL, '-1', '-1', NULL, '-1', '-1', '-1', '-1', '-1', '-1', '-1', '-1', '-1', '-1');";
            PreparedStatement st = cn.prepareStatement(sql);
            st.executeUpdate();
        }
        } catch (SQLException e) {
            
        }
            finally{
                cn.close();
            }
    }
    
    public void UpdateBT() throws SQLException{
        for(int i = 0; i <= 120; ++i){
        cn = db.getConnection(ip);
        try {
            //String sql = "UPDATE usuario SET P_Nombre='"+txtNombres.getText()+"',S_Nombre=' "+txtSgnNombre.getText()+"',P_Apellido='"+txtApellidos.getText()+"',S_Apellido=' "+ txtSgnApellido.getText()+"',Telefono='"+txtTelefono.getText()+"'WHERE N_Documento='"+txtnDocumento"';";
            
                String sql = "UPDATE racestatus SET Mark1 = 'RP-N-41-23.567-E-180-23.567',Mark2 = 'RP-N-41-23.567-E-180-23.567',Mark3 = 'RP-N-41-23.567-E-180-23.567',Mark4 = 'RP-N-41-23.567-E-180-23.567',Mark5 = 'RP-N-41-23.567-E-180-23.567',Mark6 = 'RP-N-41-23.567-E-180-23.567',Mark7 = 'RP-N-41-23.567-E-180-23.567',Mark8 = 'RP-N-41-23.567-E-180-23.567',Mark9 = 'RP-N-41-23.567-E-180-23.567',Mark10 = 'RP-N-41-23.567-E-180-23.567',Mark11 = 'RP-N-41-23.567-E-180-23.567',Mark12 = 'RP-N-41-23.567-E-180-23.567' WHERE id = " + i + ";";
                System.out.println(sql);
                PreparedStatement st = cn.prepareStatement(sql);
                st.executeUpdate();
        } catch (SQLException e) {
             e.printStackTrace();
        }
            finally{
                cn.close();
                
            }
        
            }
    }
    
        public void UpdateColors(String area, String color) throws SQLException{
        cn = db.getConnection(ip);
        System.out.println("AREA: " + area + " COLOR " + color);
        try {
            //String sql = "UPDATE usuario SET P_Nombre='"+txtNombres.getText()+"',S_Nombre=' "+txtSgnNombre.getText()+"',P_Apellido='"+txtApellidos.getText()+"',S_Apellido=' "+ txtSgnApellido.getText()+"',Telefono='"+txtTelefono.getText()+"'WHERE N_Documento='"+txtnDocumento"';";
            String sql = "UPDATE areas " + "SET " + "Color" + " = '" + color + "' WHERE area = '" + area + "';";
            System.out.println(sql);
            PreparedStatement st = cn.prepareStatement(sql);
            st.executeUpdate();
        } catch (SQLException e) {
            try {
            String sql = "UPDATE areas " + "SET " + "Color" + " = 'White' WHERE area = '" + area + "';";
            System.out.println(sql);
            PreparedStatement st = cn.prepareStatement(sql);
            st.executeUpdate();
            
            } catch (SQLException q) {
            
                System.out.println("Error: " + q.getMessage());
            }
            finally{
                cn.close();
            }
            System.out.println("Error: " + e.getMessage());
        }
        
        
    }
    
}
