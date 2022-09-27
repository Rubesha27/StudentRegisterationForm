import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StudentRegistration {
    public static void main(String[] args) {
        //FRAME SETTING
        JFrame frame = new JFrame();
        frame.setTitle("Student Registration Form");
        frame.setBounds(100, 60, 800, 620);

        //PANEL
        JPanel panel = new JPanel(null);

        //LABELS
        Font font1 = new Font("Arial", Font.BOLD, 17);
        JLabel title = new JLabel("STUDENT REGISTRATION FORM");
        title.setBounds(240,17,310,20);
        title.setFont(font1);

        Font font2 = new Font("Arial", Font. BOLD, 14);
        JLabel name = new JLabel("Name");
        name.setFont(font2);
        name.setBounds(10, 60, 55, 50);
        JTextField nameField = new JTextField();
        nameField.setBounds(150,73,200,20);
        JLabel rollNo = new JLabel("Roll No");
        rollNo.setBounds(10,103,55,50);
        rollNo.setFont(font2);
        JTextField rnField = new JTextField();
        rnField.setBounds(150,115,200,20);
        JLabel batch = new JLabel("Batch");
        batch.setFont(font2);
        batch.setBounds(10,150,55,50);
        JTextField batchField = new JTextField();
        batchField.setBounds(150,164,200,20);
        JLabel section = new JLabel("Section");
        section.setFont(font2);
        section.setBounds(10,199,55,50);
        JTextField secField = new JTextField();
        secField.setBounds(150,215,200,20);

        JLabel gender = new JLabel("Gender");
        gender.setFont(font2);
        gender.setBounds(10,240,55,50);
        JRadioButton gender1 = new JRadioButton("Male");
        gender1.setBackground(Color.PINK);
        gender1.setBounds(150,256,60,21);
        JRadioButton gender2 = new JRadioButton("Female");
        gender2.setBackground(Color.PINK);
        gender2.setBounds(280,256,69,20);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(gender1); genderGroup.add(gender2);

        JLabel qualification = new JLabel("Qualification");
        qualification.setFont(font2);
        qualification.setBounds(10,290,93,50);
        JCheckBox matric = new JCheckBox("Matric");
        matric.setBounds(150,305,67,20);
        matric.setBackground(Color.pink);
        JCheckBox inter = new JCheckBox("Intermediate");
        inter.setBackground(Color.PINK);
        inter.setBounds(250,305,98,20);
        JCheckBox graduate = new JCheckBox("Graduate");
        graduate.setBounds(150,350,90,20);
        graduate.setBackground(Color.pink);
        JCheckBox post = new JCheckBox("Post Graduate");
        post.setBackground(Color.PINK);
        post.setBounds(250,350,110,20);
        JLabel address = new JLabel("Address");
        address.setBounds(10,378,93,50);
        address.setFont(font2);
        JTextArea addressArea = new JTextArea();
        addressArea.setBounds(150,396,220,60);
        JLabel country = new JLabel("Country");
        country.setFont(font2);
        country.setBounds(10,473,80,40);
        String[] countriesArray = {"Pakistan", "India","China"};
        JComboBox countries = new JComboBox(countriesArray);
        countries.setBounds(150,485,210,20);

        JButton save = new JButton("Save");
        JButton print = new JButton("Print");
        save.setBounds(140,520,90,30);
        print.setBounds(280,520,90,30);

        //Adding events to buttons
        //Save button
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JSONObject jsonObject = new JSONObject();
                    String studName = nameField.getText();
                    jsonObject.put("Name",studName);
                    String rn = rnField.getText();
                    jsonObject.put("Roll no",rn);
                    String bch = batchField.getText();
                    jsonObject.put("Batch",bch);
                    String sec = secField.getText();
                    jsonObject.put("Section",sec);

                    String gn="";
                    if(gender1.isSelected()){
                        gn="Male";
                    } else if (gender2.isSelected()) {
                        gn="Female";
                    }
                    jsonObject.put("Gender", gn);
                    String quali ="";
                    if(matric.isSelected() && inter.isSelected() && graduate.isSelected()&&post.isSelected()){
                        quali ="Matric,Intermediate,Graduate,PostGraduate";
                    } else if ( inter.isSelected() && graduate.isSelected()&&post.isSelected()) {
                        quali="Intermediate,Graduate,PostGraduate";
                    }else if(matric.isSelected()  && graduate.isSelected()&&post.isSelected()){
                        quali ="Matric,Graduate,PostGraduate";
                    }else if(matric.isSelected() && inter.isSelected() &&post.isSelected()){
                        quali ="Matric,Intermediate,PostGraduate";
                    }else if(matric.isSelected() && inter.isSelected() && graduate.isSelected()){
                        quali ="Matric,Intermediate,Graduate";
                    }else if(matric.isSelected() && inter.isSelected()){
                        quali ="Matric,Intermediate";
                    } else if (graduate.isSelected()&&post.isSelected()) {
                        quali ="Graduate,PostGraduate";
                    }else if(matric.isSelected() && graduate.isSelected()){
                        quali="Matric,Graduate";
                    }else if (matric.isSelected() && post.isSelected()){
                        quali="Matric,PostGraduate";
                    } else if(graduate.isSelected() &&inter.isSelected() ){
                        quali="Inter,Graduate";
                    }else if(graduate.isSelected() &&matric.isSelected() ){
                        quali="Matric,Graduate";
                    }else if(matric.isSelected()){
                        quali="Matric";
                    } else if (inter.isSelected()) {
                        quali="inter";
                    } else if (graduate.isSelected()) {
                        quali="Graduate";
                    }else if(post.isSelected()){
                        quali="PostGraduate";
                    }
                    jsonObject.put("Qualification",quali);

                    String ad = addressArea.getText();
                    jsonObject.put("Address",ad);
                    jsonObject.put("Country",countries.getSelectedItem());

                    FileWriter file = new FileWriter("StudentRegister.json");
                    file.write(jsonObject.toJSONString());
                    file.close();
                    JOptionPane.showMessageDialog(frame,"File Saved");

                }catch (NumberFormatException formatException){
                    JOptionPane.showMessageDialog(frame,"ERROR");
                }catch (IOException ioException){
                    JOptionPane.showMessageDialog(frame,"File not found.");
                }
            }
        });

        //Print button
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    JFrame printFrame = new JFrame("Print");
                    printFrame.setBounds(100,80,400,400);
                    JPanel printPanel = new JPanel(null);
                    Object ob = new JSONParser().parse(new FileReader("StudentRegister.json"));
                    JSONObject js = (JSONObject) ob;
                    String name = (String) js.get("Name");
                    String rn = (String) js.get("Roll no");
                    String bt = (String) js.get("Batch");
                    String sec = (String) js.get("Section");
                    String gn = (String) js.get("Gender");
                    String q = (String) js.get("Qualification");
                    String ad = (String) js.get("Address");
                    String coun = (String) js.get("Country");

                    Font font2 = new Font("Times New Roman",Font.BOLD,18);
                    JLabel nameLabel = new JLabel(name);
                    nameLabel.setBounds(10, 30, 200, 50);
                    nameLabel.setFont(font2);
                    JLabel rollLabel = new JLabel(rn);
                    rollLabel.setBounds(10, 60, 200, 50);
                    rollLabel.setFont(font2);
                    JLabel btLabel = new JLabel(bt);
                    btLabel.setBounds(10, 90, 200, 50);
                    btLabel.setFont(font2);
                    JLabel secLabel = new JLabel(sec);
                    secLabel.setBounds(10, 120, 200, 50);
                    secLabel.setFont(font2);
                    JLabel gnLabel = new JLabel(gn);
                    gnLabel.setBounds(10, 150, 200, 50);
                    JLabel qLabel = new JLabel(q);
                    qLabel.setBounds(10, 180, 800, 50);
                    qLabel.setFont(font2);
                    JLabel addLabel = new JLabel(ad);
                    addLabel.setBounds(10, 210, 700, 50);
                    addLabel.setFont(font2);
                    JLabel counLabel = new JLabel(coun);
                    counLabel.setBounds(10, 260, 500, 50);
                    counLabel.setFont(font2);

                    printPanel.add(nameLabel);
                    printPanel.add(rollLabel);
                    printPanel.add(secLabel);
                    printPanel.add(gnLabel);
                    printPanel.add(btLabel);
                    printPanel.add(qLabel);
                    printPanel.add(addLabel);
                    printPanel.add(counLabel);
                    printFrame.add(printPanel);
                    printFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    printFrame.setVisible(true);
                } catch (IOException | ParseException exception){
                    JOptionPane.showMessageDialog(frame,"File not found.");
                }
            }
        });

        //ADDING COMPONENTS TO PANEL
        panel.add(title);
        panel.add(name);
        panel.add(nameField);
        panel.add(rollNo);
        panel.add(rnField);
        panel.add(batch);
        panel.add(batchField);
        panel.add(secField);
        panel.add(section);
        panel.add(gender);
        panel.add(gender1);
        panel.add(gender2);
        panel.add(qualification);
        panel.add(matric);
        panel.add(inter);
        panel.add(graduate);
        panel.add(post);
        panel.add(address);
        panel.add(addressArea);
        panel.add(country);
        panel.add(countries);
        panel.add(save);
        panel.add(print);

        frame.add(panel);
        //FRAME SETTINGS
        panel.setBackground(Color.PINK);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    }
}
