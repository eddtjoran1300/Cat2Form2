package form2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class form2 extends JFrame implements ActionListener {
    private JTextField idField, nameField, addressField, contactField;
    private JRadioButton maleRadio, femaleRadio;
    private JButton exitButton, registerButton;
    private JTextArea displayArea;

    public form2() {
        setTitle("Registration");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField();
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField();
        JLabel contactLabel = new JLabel("Contact:");
        contactField = new JTextField();

        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(addressLabel);
        formPanel.add(addressField);
        formPanel.add(contactLabel);
        formPanel.add(contactField);
        formPanel.add(maleRadio);
        formPanel.add(femaleRadio);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        registerButton = new JButton("Register");
        registerButton.addActionListener(this);
        buttonPanel.add(exitButton);
        buttonPanel.add(registerButton);

        displayArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(displayArea);

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            System.exit(0);
        } else if (e.getSource() == registerButton) {
            String id = idField.getText();
            String name = nameField.getText();
            String address = addressField.getText();
            String contact = contactField.getText();
            String gender = maleRadio.isSelected() ? "Male" : "Female";

            String data = "ID: " + id + "\nName: " + name + "\nAddress: " + address + "\nContact: " + contact + "\nGender: " + gender + "\n\n";
            displayArea.append(data);

            saveToDatabase(id, name, address, contact, gender);
        }
    }

    private void saveToDatabase(String id, String name, String address, String contact, String gender) {
        try (FileWriter writer = new FileWriter("database.txt", true)) {
            writer.write("ID: " + id + "\nName: " + name + "\nAddress: " + address + "\nContact: " + contact + "\nGender: " + gender + "\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new form2();
    }
}
