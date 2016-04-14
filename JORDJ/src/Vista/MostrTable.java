/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridLayout;
import java.util.*;
import BaseDatos.BaseDatos;
import Estructuras.Regata;
import java.awt.Color;
import javax.swing.table.*;
import java.awt.Component;
import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;

import java.text.SimpleDateFormat;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.Font;
import java.sql.Time;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javax.swing.table.*;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.*;
import java.awt.Component;
import java.awt.Color;
import javax.swing.*;
import javax.swing.plaf.UIResource;
import javax.swing.border.Border;
import javax.swing.table.*;
import javax.swing.SwingUtilities;

/**
 *
 * @author Miquel Ferriol
 */
public class MostrTable extends JFrame{
    private Timer displayTimer;        
    private String[] COLUMNA = {"id", "Class","Grp", "Race", "ScheduledDate", "RealDate", "Entries", "Area", "Committee", "RaceStatus", "Signall", "Time","ScheduledTime", "StartingTime", "BoatsStarted", "PreparatorySignal", "OCS_DSQ", "AP", "GR", "FinishTime", "RaceTime" ,  "BoatsFinished", "LastSignal", "LastSignalTime", "Results", "Course", "Distance1stLeg", "Bearing1stLeg", "LegChanges", "WindDir","WindSpeed"};
    private String [] titulos ={"Id", "Class", "Group","Race",  "Scheduled Date", "Real Date", "Entries", "Area", "Committee", "RACE STATUS", "Signal", "Time","Scheduled Time", "Starting Time", "Boats Started", "Preparatory Signal", "Nr.OCS/DSQ", "AP", "GR", "Finish Time", "Race Time" ,  "Boats Finished", "Last Signal", "Last Signal Time", "Results", "Course", "Distance 1stLeg", "Bearing1stLeg", "LegChanges","Wind Dir.","Wind Speed"};
    private DefaultTableModel modelo;
    private BaseDatos BD;
    private String IP;
    
    public class TableRenderer extends DefaultTableCellRenderer { 
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean   isSelected, boolean hasFocus, int row, int column) 
    { 
        //System.out.println("COLUMN: " + column);
        if(column == 10 || column == 15){
            try{
                String s =  modelo.getValueAt(row, column).toString();
                if(s.equals("OTHER")){
                    s = "UNIFORM";
                }
                ImageIcon icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                JLabel lbl = new JLabel();
                lbl.setHorizontalAlignment(JLabel.CENTER);
                lbl.setVerticalAlignment(JLabel.CENTER);
                lbl.setIcon(icon);
                lbl.setIcon(icon);
                return lbl;
            }
            catch(Exception e){
                JLabel lbl = new JLabel();
                return lbl;
            }
        }
        else if(column == 9){
            try{
                String s =  modelo.getValueAt(row, column).toString();
                JLabel lbl = new JLabel();
                ImageIcon icon; 
                switch (s) {
                    case "SCHEDULED":
                        s = "SCHEDULED";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "POSTPONDMENT":
                        s = "AP";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "ON SEQUENCE":
                        s = "ORANGE";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "FINISHED":
                        s = "BLUE";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "ABANDON":
                        s = "NOVEMBER";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "SAILING":
                        s = "SAILING";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "ON TIME":
                        s = "ONTIME";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    default:
                        lbl.setText(s);
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + "WHITE" + ".JPG"));
                        break;
                }
                lbl.setHorizontalAlignment(JLabel.CENTER);
                lbl.setVerticalAlignment(JLabel.CENTER);
                lbl.setIcon(icon);
                lbl.setHorizontalTextPosition(JLabel.CENTER);
                lbl.setVerticalTextPosition(JLabel.CENTER);
                lbl.setIcon(icon);
                return lbl;
            }
            
            catch(Exception e){
                JLabel lbl = new JLabel();
                return lbl;
            }
        }
        else if(column == 24){
            try{
                String s =  modelo.getValueAt(row, column).toString();
                JLabel lbl = new JLabel();
                ImageIcon icon; 
                switch (s) {
                    case "PUBLISHED":
                        s = "LIMA";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "RECEIVED":
                        s = "GREEN";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "REVIEWING":
                        s = "YELLOW";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "INCIDENCE":
                        s = "RED";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    default:
                        lbl.setText(s);
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + "WHITE" + ".JPG"));
                        break;
                }
                lbl.setHorizontalAlignment(JLabel.CENTER);
                lbl.setVerticalAlignment(JLabel.CENTER);
                lbl.setIcon(icon);
                lbl.setHorizontalTextPosition(JLabel.CENTER);
                lbl.setVerticalTextPosition(JLabel.CENTER);
                lbl.setIcon(icon);
                return lbl;
            }
            catch(Exception e){
                JLabel lbl = new JLabel();
                //System.out.println(e.getMessage());
                return lbl;
            }
        }
        else if(column == 22){
            try{
                String s =  modelo.getValueAt(row, column).toString();
                JLabel lbl = new JLabel();
                ImageIcon icon; 
                switch (s) {
                    case "FINISH CLOSED":
                        s = "FINISH CLOSED";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "N+A":
                        s = "N+A";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "AP+A":
                        s = "AP+A";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "LAST BOAT":
                        s = "BLUE";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "ONB PUBLICATION":
                        s = "LIMA";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    default:
                        lbl.setText(s);
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + "WHITE" + ".JPG"));
                        break;
                }
                lbl.setHorizontalAlignment(JLabel.CENTER);
                lbl.setVerticalAlignment(JLabel.CENTER);
                lbl.setIcon(icon);
                lbl.setHorizontalTextPosition(JLabel.CENTER);
                lbl.setVerticalTextPosition(JLabel.CENTER);
                lbl.setIcon(icon);
                return lbl;
            }
            catch(Exception e){
                JLabel lbl = new JLabel();
                //System.out.println(e.getMessage());
                return lbl;
            }
        }
        else if(column == 28){
            try{
                String s =  modelo.getValueAt(row, column).toString();
                JLabel lbl = new JLabel();
                ImageIcon icon; 
                switch (s) {
                    case "Starboard":
                        s = "G";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "Port":
                        s = "R";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "Increased":
                        s = "+";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "Decreased":
                        s = "-";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "Stb. Incr.":
                        s = "G+";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "Prt. Incr.":
                        s = "R+";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "Stb. Decr.":
                        s = "G-";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "Prt. Decr.":
                        s = "R-";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    default:
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + "WHITE" + ".JPG"));
                        break;
                }
                lbl.setHorizontalAlignment(JLabel.CENTER);
                lbl.setVerticalAlignment(JLabel.CENTER);
                lbl.setIcon(icon);
                lbl.setHorizontalTextPosition(JLabel.CENTER);
                lbl.setVerticalTextPosition(JLabel.CENTER);
                lbl.setIcon(icon);
                return lbl;
            }
            catch(Exception e){
                JLabel lbl = new JLabel();
                //System.out.println("COLUMN 28 " + e.getMessage());
                return lbl;
            }
        } 
        //
        else if(column == 2){
            try{
                String s =  modelo.getValueAt(row, column).toString();
                JLabel lbl = new JLabel();
                ImageIcon icon; 
                switch (s) {
                    case "Yellow Q-series":
                        s = "Q+Y";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "Blue Q-series":
                        s = "Q+B";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "Red Q-series":
                        s = "Q+R";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "Yellow F-series":
                        s = "F+Y";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "Blue F-series":
                        s = "F+B";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        //"Yellow Q-series", "Blue Q-series","Red Q-series","Yellow F-series","Blue F-series","Red F-series","Gold","Silver","Bronze","Fleet","Medal Race"
                        break;
                    case "Red F-series":
                        s = "F+R";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "Gold":
                        s = "GOLD";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "Silver":
                        s = "SILVER";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "Bronze":
                        s = "BRONZE";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "Fleet":
                        s = "FLEET";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    case "Medal Race":
                        s = "MR";
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + s + ".JPG"));
                        break;
                    default:
                        lbl.setText(s);
                        icon = new ImageIcon(getClass().getResource("Imagenes/" + "WHITE" + ".JPG"));
                        break;
                }
                lbl.setHorizontalAlignment(JLabel.CENTER);
                lbl.setVerticalAlignment(JLabel.CENTER);
                lbl.setIcon(icon);
                lbl.setHorizontalTextPosition(JLabel.CENTER);
                lbl.setVerticalTextPosition(JLabel.CENTER);
                lbl.setIcon(icon);
                return lbl;
            }
            catch(Exception e){
                JLabel lbl = new JLabel();
                //System.out.println(e.getMessage());
                return lbl;
            }
        }
        
        else if (column == 7){
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
            c.setFont(new Font("Arial", Font.LAYOUT_NO_LIMIT_CONTEXT ,15));
            try{
                Color col;
                String s = modelo.getValueAt(row, column).toString();
                switch(s){
                    case "Päo Açucar":
                        col = Color.red;
                        break;
                    case "Escola Naval":
                        col = Color.green;
                        break;
                    case "Ponte":
                        col = Color.yellow;
                        break;
                    case "Copacabana":
                        col = Color.orange;
                        break;
                    case "Niteroi":
                        col = Color.cyan;
                        break;    
                    case "Pai":
                        col = Color.pink;
                        break;    
                    case "Aeroport":
                        col = Color.gray;
                        break;
                    default:
                        col = Color.WHITE;
                }
                c.setBackground(col);
                return c;
            }
            catch(Exception e){
                return c;
            }
        }
        /*else if(column == 29){
            //System.out.println("KAPPA");
            try{
                
            int g = Integer.parseInt(modelo.getValueAt(row, 30).toString());
            System.out.println("Graus: " + g );
            int s = Integer.parseInt(modelo.getValueAt(row, 31).toString());
            JLabel lbl = new JLabel();
            ImageIcon icon = new ImageIcon(getClass().getResource("Imagenes/1.jpg"));
            int w = icon.getIconWidth();
            int h = icon.getIconHeight();
            int type = BufferedImage.OPAQUE;  // other options, see api
            BufferedImage image = new BufferedImage(h, w, type);
            Graphics2D g2 = image.createGraphics();
            g2.rotate(Math.toRadians(g), w, h);
            g2.drawImage(icon.getImage(), 0,0,Color.WHITE, lbl);
            
            g2.dispose();
            icon = new ImageIcon(image);
            lbl.setIcon(icon);
            lbl.setHorizontalAlignment(JLabel.CENTER);
            lbl.setVerticalAlignment(JLabel.CENTER);
            return lbl;
            }
            catch(Exception e){
                JLabel lbl = new JLabel();
                return lbl;
            }
        }*/
        
        else {
            //System.out.println("WTF ESTA PASSANT " + column);
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
            c.setFont(new Font("Arial", Font.LAYOUT_NO_LIMIT_CONTEXT ,15));
            c.setBackground(Color.white);
            return c;
        }
    } 
        
    }
    
    public class HeaderRenderer extends DefaultTableCellRenderer { 
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean   isSelected, boolean hasFocus, int row, int column) 
    { 
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
            c.setFont(new Font("Arial", Font.LAYOUT_NO_LIMIT_CONTEXT ,15));
            try{
                Color col;
                switch(column){
                    case 1:
                        col = Color.red;
                        break;
                    case 2:
                        col = Color.green;
                        break;
                    case 3:
                        col = Color.yellow;
                        break;
                    case 4:
                        col = Color.orange;
                        break;
                    case 5:
                        col = Color.cyan;
                        break;    
                    case 6:
                        col = Color.pink;
                        break;    
                    case 7:
                        col = Color.gray;
                        break;
                    default:
                        col = Color.WHITE;
                }
                c.setBackground(col);
                return c;
            }
            catch(Exception e){
                return c;
            }
        }       
    }    
    
    public MostrTable(BaseDatos BD) {
        super("");
        this.BD = BD;
        setIconImage(new ImageIcon(getClass().getResource("../Vista/Imagenes/+.jpg")).getImage());
        DataTable();
        JTable table = new JTable(modelo){
          @Override
          protected JTableHeader createDefaultTableHeader() {
              return new GroupableTableHeader(columnModel);
          }
        };
        TableRenderer r = new TableRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);

        //add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        setExtendedState( getExtendedState()|JFrame.MAXIMIZED_BOTH );

        ActionListener listener = (ActionEvent event) -> {
            printTable();
            displayTimer.restart();
        };

        table.getDefaultEditor(String.class).addCellEditorListener(
            new CellEditorListener() {
                @Override
                public void editingCanceled(ChangeEvent e) {
                    //System.out.println("editingCanceled");
                }

                @Override
                public void editingStopped(ChangeEvent e) {
                    int column = table.getSelectedColumn();
                    int row = table.getSelectedRow();
                    lastCol = column;
                    lastRow = row;
                    if(row != -1 && column != -1){
                        Object data = modelo.getValueAt(row, column);
                        
                        if(correctValue(column, data.toString())){
                                BD.Update(row+1, COLUMNA[column], data);
                            }
                        else if (!"".equals(data.toString())){
                            modelo.setValueAt("", row, column);
                        }

                        if(column == 13 || column == 19){
                                finishTime(row,column);
                        }
                    }
                }
            }
        );
        
        displayTimer = new Timer(1000, listener);
        displayTimer.start();

        TableColumnAdjuster tca = new TableColumnAdjuster(table);
        tca.adjustColumns();

        table.setRowHeight(51);

        table.getTableHeader().setFont(new Font("Arial", Font.BOLD ,15));
        
        initHeader(table);
    
        add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

        pack();
        setVisible(true);
    }
    int lRow;
    boolean cont = false;
    int lastCol = -1;
    int lastRow = -1;
    
    
    private void initHeader(JTable table){
        TableColumnModel cm = table.getColumnModel();
        ColumnGroup g_StartsAbandoned = new ColumnGroup("Starts Abandoned");
        g_StartsAbandoned.add(cm.getColumn(17));
        g_StartsAbandoned.add(cm.getColumn(18));


        ColumnGroup g_Races = new ColumnGroup("RACES");
        g_Races.add(cm.getColumn(1));
        g_Races.add(cm.getColumn(2));
        g_Races.add(cm.getColumn(3));
        g_Races.add(cm.getColumn(4));
        g_Races.add(cm.getColumn(5));
        g_Races.add(cm.getColumn(6));
        g_Races.add(cm.getColumn(7));
        g_Races.add(cm.getColumn(8));

        ColumnGroup g_AshoreSignal = new ColumnGroup("Ashore Signals");
        g_AshoreSignal.add(cm.getColumn(10));
        g_AshoreSignal.add(cm.getColumn(11));

        ColumnGroup g_Start = new ColumnGroup("START");
        g_Start.add(cm.getColumn(12));
        g_Start.add(cm.getColumn(13));
        g_Start.add(cm.getColumn(14));
        g_Start.add(cm.getColumn(15));
        g_Start.add(cm.getColumn(16));
        g_Start.add(g_AshoreSignal);
        g_Start.add(g_StartsAbandoned);

        ColumnGroup g_Finish = new ColumnGroup("FINISH");
        g_Finish.add(cm.getColumn(19));
        g_Finish.add(cm.getColumn(20));
        g_Finish.add(cm.getColumn(21));

        ColumnGroup g_DayEnd = new ColumnGroup("DAY END");
        g_DayEnd.add(cm.getColumn(22));
        g_DayEnd.add(cm.getColumn(23));
        g_DayEnd.add(cm.getColumn(24));

        ColumnGroup g_Course = new ColumnGroup("COURSE");
        g_Course.add(cm.getColumn(25));
        g_Course.add(cm.getColumn(26));
        g_Course.add(cm.getColumn(27));
        g_Course.add(cm.getColumn(28));
        g_Course.add(cm.getColumn(29));
        g_Course.add(cm.getColumn(30));

        GroupableTableHeader header = (GroupableTableHeader)table.getTableHeader();
        header.addColumnGroup(g_Races);
        header.addColumnGroup(g_Start);
        header.addColumnGroup(g_AshoreSignal);
        header.addColumnGroup(g_Finish);
        header.addColumnGroup(g_DayEnd);
        header.addColumnGroup(g_Course);
        
        header.setFont(new Font("Arial", Font.BOLD ,15));
    }
    
    private void finishTime(int row, int column){
        System.out.println("FINISH TIME");
        if((modelo.getValueAt(row, 13) != "") && (modelo.getValueAt(row, 19) != "") ){
            SimpleDateFormat timerformat = new SimpleDateFormat("HH:mm:ss");
            try{
                    Date date1 = timerformat.parse(modelo.getValueAt(row, 13).toString());
                    System.out.println("DATA: " + modelo.getValueAt(row, 19).toString());
                    Date date2 = timerformat.parse(modelo.getValueAt(row, 19).toString());
                    Date date = new Date();
                    long millis = date2.getTime()-date1.getTime();
                    String hms = String.format("%02d:%02d:%02d", 
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) -  
                    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), // The change is in this line
                    TimeUnit.MILLISECONDS.toSeconds(millis) - 
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                    //System.out.println(modelo.getValueAt(row, 19).toString());
                        modelo.setValueAt(hms, row, 20);
                        BD.Update(row+1, COLUMNA[20], hms);
            } catch (Exception es) {
                System.out.println(es.getMessage());
                es.printStackTrace();
            }
        }
        else{
            modelo.setValueAt("", row, 20);
            BD.Update(row+1, COLUMNA[20], "");
        }
    }
    
    private boolean correctValue(int c, String val){
        if(!val.equals("")){
            switch(c){               
                case 4:
                case 5:
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Date date = formatter.parse(val);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                case 11:
                case 12:
                case 23:
                    SimpleDateFormat timerformat = new SimpleDateFormat("HH:mm");
                        try {
                            Date date = timerformat.parse(val);
                            return true;
                        } catch (Exception e) {
                            return false;
                        }
                case 13:
                case 19:
                case 20:
                    SimpleDateFormat timerformat1 = new SimpleDateFormat("HH:mm:ss");
                        try {
                            timerformat1.parse(val).getTime();
                            return true;
                        } catch (Exception e) {
                            return false;
                        }
                default:
                return true;
            }
        }
        else return true;
    }
    
    
    private void printTable(){
        BD.initBD();
        for(int i = 0; i < BD.getBD().size(); ++i ){
            Regata r = BD.getBD().get(i);
            modelo.setValueAt(r.getId(),i,0);
            modelo.setValueAt(r.getClas(),i,1);
            modelo.setValueAt(r.getGroup(),i,2);
            modelo.setValueAt(r.getRace(),i,3);
            modelo.setValueAt(r.getScheduledDate(),i,4);
            modelo.setValueAt(r.getRealDate(),i,5);
            modelo.setValueAt(r.getEntries(),i,6);
            modelo.setValueAt(r.getArea(),i,7);
            modelo.setValueAt(r.getCommittee(),i,8);
            modelo.setValueAt(r.getRaceStatus(),i,9);
            modelo.setValueAt(r.getSignal(),i,10);
            modelo.setValueAt(r.getTime(),i,11);
            modelo.setValueAt(r.getScheduledTime(),i,12);
            modelo.setValueAt(r.getStartingTime(),i,13);
            modelo.setValueAt(r.getBoatsStarted(),i,14);
            modelo.setValueAt(r.getPreparatorySignal(),i,15);
            modelo.setValueAt(r.getOCS_DSQ(),i,16);
            modelo.setValueAt(r.getAP(),i,17);
            modelo.setValueAt(r.getGR(),i,18);
            modelo.setValueAt(r.getFinishTime(),i,19);
            modelo.setValueAt(r.getRaceTime(),i,20);
            modelo.setValueAt(r.getBoatsFinished(),i,21);
            modelo.setValueAt(r.getLastSignal(), i, 22);
            modelo.setValueAt(r.getLastSignalTime(), i, 23);
            modelo.setValueAt(r.getResults(), i, 24);
            modelo.setValueAt(r.getCourse(),i,25);
            modelo.setValueAt(r.getDistance1stLeg(),i,26);
            modelo.setValueAt(r.getBearing1stLeg(),i,27);
            modelo.setValueAt(r.getLegChanges(),i,28);
            modelo.setValueAt(r.getWindDir(),i,29);
            modelo.setValueAt(r.getWindSpeed(),i,30);
        }
        CheckGrid();
    }
    
    private void DataTable(){
        
        modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
        modelo.setDataVector(new Object[][]{}, titulos);
        String [] fila = new String[titulos.length];
        BD.initBD();
        int size = BD.getBD().size();
        
        for(int i = 0; i < size; ++i){
            modelo.addRow(fila);
        }
        
        printTable();
    }
    
    private void CheckGrid(){
        int rows = modelo.getRowCount();
        int col = modelo.getColumnCount();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                Object ob = modelo.getValueAt(i, j);
                if (ob  == null ) {
                    if(j == 12 && i == 0) System.out.println("BORRAMOS" + (ob  == null));
                }
                else if(ob.toString().equals("-1") || ob.toString().equals("-1.0") || ob.toString().isEmpty()) modelo.setValueAt("", i, j);
                
            }
        }
    }
}