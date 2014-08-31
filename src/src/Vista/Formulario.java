/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import com.toedter.calendar.JCalendar;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.util.HashMap;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author rodro
 */
public class Formulario extends JPanel {

    private HashMap<String, JComponent> campos;
    private Integer currentHeight;
    public Formulario() {

        campos = new HashMap(); 
        setLayout(null);
        setVisible(true);
        setSize(500, 700);
        JScrollPane scroll = new JScrollPane(this);
        currentHeight = 20;
    } 

    public void addField(String fieldName, String fieldType)   {
        JComponent componente;
        JLabel label = new JLabel();
        label.setText(fieldName);
        label.setSize(fieldName.length()*10, 30);
        Dimension componentSize = new Dimension(180,30);
        switch (fieldType) {

            case "textarea":
                componente = new JTextArea();
                componentSize.setSize(180, 200);
                break;
            case "combo":
                componente = new JComboBox();
                break;
            case "checkbox":
                componente = new JCheckBox();
                break;
            case "Date":
                componente = new JCalendar();
                break;
            case "text":
            default:
                componente = new JTextField();
            break;
        }
      
        componente.setSize(componentSize);
        campos.put(fieldName, componente);
        
        Integer currentSize = campos.values().size();
        label.setLocation(10, currentHeight);
        componente.setLocation(150, currentHeight);
        currentHeight += componentSize.height;
        
      //  System.out.println(componente.getClass());
        add(label);
        add(componente);
    }
    @Deprecated
    private void format(){
        int size = campos.values().size();
      //  SpringUtilities.makeGrid(this,size,2,0,0,6,6);
    }
    
    public JComponent getComponentByName(String name){
        
        if(campos.containsKey(name)){
        
            return campos.get(name);
        }
        return null;
    }
}
