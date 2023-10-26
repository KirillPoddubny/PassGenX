package org.example.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.example.passgen.FileGenerator;
import org.example.passgen.PasswordGenerator;

public class Window {

    public Window() {

        String hello = "добро пожаловать в PassGenX! Эта программа написана для создания паролей!";

        JFrame window = new JFrame();
        JPanel panel = new JPanel();
        JLabel label = new JLabel("PassGenX");
        JButton create = new JButton("генерация (нажми)");

        create.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        try {

                            PasswordGenerator password = new PasswordGenerator(15);

                            String pass = password.Generate();

                            FileGenerator file = new FileGenerator(pass+".txt");
                            boolean isCreate = file.CreateFile(pass);

                            if (isCreate == false) {
                                JOptionPane
                                        .showMessageDialog(null, "при генерации пароля появилась ошибка, попробуйте еще раз");
                            }

                            JOptionPane
                                    .showMessageDialog(null, "пароль: " + pass + "\n" + "так же ваш пароль записан в файле " + pass + ".txt");
                        }
                        catch (Exception err) {
                            JOptionPane
                                    .showMessageDialog(null, "напишите корректное количество символов");
                        }
                        finally {
                            System.out.println("попытка генерации пароля и создания файла");
                        }
                    }
                }
        );

       create.setFont(new Font("Arial", Font.BOLD, 30));
       create.setBackground(Color.WHITE);

        window.setVisible(true);
        window.setTitle("PassGenX");
        window.setResizable(false);
        window.add(label);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(panel, BorderLayout.WEST);
        window.getContentPane().add(create);
        window.setSize(700, 400);

        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setBounds(200, 200, 200, 30);
        label.setForeground(Color.WHITE);

        panel.setBackground(Color.ORANGE);
        panel.setPreferredSize(new Dimension(200, 400));
        panel.add(label);
        panel.add(label);

        JOptionPane.showMessageDialog(null, hello);
    }
}
