package com.rockville.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rockville.auth.model.dto.UserRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        List<UserRequest> records = new ArrayList<>();
//        try (Scanner scanner = new Scanner(new File("C:\\Users\\User\\Documents\\Rockville\\Agents\\batch-1.csv"))) {
//            while (scanner.hasNextLine()) {
//                records.add(getRecordFromLine(scanner.nextLine()));
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        records.forEach(userRequest -> {
//            try {
//                userRequest.setUsername(
//                        userRequest.getFirstName().toLowerCase().charAt(0) + "." +  userRequest.getLastName().toLowerCase()
//                );
//                userRequest.setPassword(
//                        String.valueOf(new Random().nextInt(8999) + 1000)
//                );
//                System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(userRequest));
//            } catch (JsonProcessingException e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }
//
//    public UserRequest getRecordFromLine(String line) {
//        UserRequest userRequest = UserRequest.builder().build();
//        try (Scanner rowScanner = new Scanner(line)) {
//            rowScanner.useDelimiter(",");
//            int index = 0;
//            while (rowScanner.hasNext()) {
//                String s = rowScanner.next();
//                switch (index) {
//                    case 0:
//                        userRequest.setFirstName(s);
//                        break;
//                    case 1:
//                        userRequest.setLastName(s);
//                        break;
//                    case 2:
//                        userRequest.setContactNumber(
//                                s.length() == 10 ? "0" + s : s
//                        );
//                        break;
//                    case 3:
//                        userRequest.setEmail(s);
//                }
//                index++;
//            }
//        }
//        return userRequest;
//    }
}
