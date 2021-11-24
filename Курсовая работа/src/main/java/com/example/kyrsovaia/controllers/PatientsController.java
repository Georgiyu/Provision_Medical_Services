package com.example.kyrsovaia.controllers;

import com.example.kyrsovaia.models.Patients;
import com.example.kyrsovaia.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.Objects;

import static com.example.kyrsovaia.EmailService.SendingEmail;

@Controller
public class PatientsController {
    @Autowired
    private PatientRepository patientRepository;
    private String List;

    @GetMapping("/zapis")
    public String ZapisPage( Model model) {
        Iterable<Patients> patients = patientRepository.findAll();
        model.addAttribute("patients", patients);
        return "zapis";
    }
    @PostMapping("/zapis")
    public String add(@RequestParam String name, @RequestParam String email, @RequestParam String number, @RequestParam String datepicker) throws MessagingException, IOException {
        Patients patient = new Patients(name, number, email,datepicker);

        patientRepository.save(patient);
        SendingEmail(email, name);

        return "zapis";
    }
    @PostMapping("/auth")
    public String add(@RequestParam String username, @RequestParam String password, Model model) throws MessagingException, IOException {
        Iterable<Patients> patients = patientRepository.findAll();
        model.addAttribute("patients", patients);
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String login = "postgres";
            String passworddb = "root";

            Connection con = DriverManager.getConnection(url, login, passworddb);
            try {
                Statement stmt = con.createStatement();
                Statement stmt1 = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT username FROM data_doctors");
                ResultSet ps = stmt1.executeQuery("SELECT password FROM data_doctors");
                while (rs.next() && ps.next()) {

                    String name = rs.getString("username") ;

                    String pass =ps.getString("password") ;
                    if ((Objects.equals(name, username)) && (Objects.equals(pass, password))){

                        List = "data_patients";
                        break;
                         }
                    else {List = "auth";};
                }
                rs.close();
                stmt.close();
                ps.close();
                stmt1.close();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return List;
    }
}
