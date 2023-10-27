package org.example.gui;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.example.passgen.FileGenerator;
import org.example.passgen.PasswordGenerator;

public class Window extends Thread {

    public Window() {

        Desktop desktop = Desktop.getDesktop();

        String hello = "добро пожаловать в PassGenX! Эта программа написана для создания паролей!";

        JButton create = new JButton("генерация (нажми)");
        JButton authors = new JButton("разработчик");
        JFrame window = new JFrame();
        JPanel panel = new JPanel();
        JLabel label = new JLabel("PassGenX");
        JLabel label2 = new JLabel("version 1.2.0");

        create.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        try {
                            PasswordGenerator password = new PasswordGenerator(8);

                            String pass = password.Generate();

                            int yesno = JOptionPane.showConfirmDialog(null, "хотите ли вы ввести логин? (программа создаст более надежный пароль)");

                            if (yesno == JOptionPane.YES_OPTION) {
                                String login = JOptionPane.showInputDialog(null, "введите логин", "ваше имя или логин");

                                if (login.length() > 10 || login.length() < 4) {
                                    JOptionPane.showMessageDialog(null, "ваш логин не должен содержать менее 4 и более 10 символов!");
                                    return;
                                }
                                else {
                                    pass = login + "_" + pass;
                                }
                            }

                            FileGenerator file = new FileGenerator(pass+".txt");
                            boolean isCreate = file.CreateFile(pass);

                            create.setText(pass);

                            if (isCreate == false) {
                                JOptionPane
                                        .showMessageDialog(null, "при генерации пароля появилась ошибка, попробуйте еще раз");
                            }

                            JOptionPane
                                    .showMessageDialog(null, "пароль: " + pass + "\n" + "так же ваш пароль записан в файле " + pass + ".txt");

                            create.setText("генерация (нажми)");
                        }
                        catch (Exception err) {
                            JOptionPane
                                    .showMessageDialog(null, "напишите корректное количество символов");
                        }
                        finally {
                            System.out.println("попытка создания пароля");
                        }
                    }
                }
        );

        create.setFont(new Font("Arial", Font.BOLD, 30));
        create.setSize(100, 100);
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

        label2.setFont(new Font("Arial", Font.BOLD, 30));
        label2.setBounds(200, 200, 200, 30);
        label2.setForeground(Color.WHITE);

        panel.setBackground(Color.ORANGE);
        panel.setPreferredSize(new Dimension(230, 400));
        panel.add(label);
        panel.add(label2);
        panel.add(authors);

        authors.setFont(new Font("Arial", Font.BOLD, 20));
        authors.setBackground(Color.yellow);
        authors.setForeground(Color.orange);

        authors.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            desktop.browse(new URI("https://github.com/KirillPoddubny"));
                            File file = new File("info.txt");

                            FileWriter filewriter = new FileWriter(file);
                            BufferedWriter writer = new BufferedWriter(filewriter);
                            writer.write("авторы: \nнаписание программы - Поддубный Кирилл \nоформление проекта - Паленко Роман");
                            writer.close();
                        }
                        catch (Exception error) {
                            JOptionPane.showMessageDialog(null, "не удалось создать файл");
                        }
                    }
                }
        );

        JOptionPane.showMessageDialog(null, hello);
    }
}
