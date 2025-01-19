package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Get {
        // Creates every array
        public ArrayList<ArrayList<String>> contact = new ArrayList<ArrayList<String>>();
        public ArrayList<String> firstName = new ArrayList<String>();
        public ArrayList<String> lastName = new ArrayList<String>();
        public ArrayList<String> number = new ArrayList<String>();
        public ArrayList<String> email = new ArrayList<String>();
        public ArrayList<String> birthday = new ArrayList<String>();

        public void contactUpdate() {
            contact.clear();
            loadContacts();
        }

        public void saveContacts() {
            try {
                saveListToFile(firstName, "save/firstname.txt");
                saveListToFile(lastName, "save/lastname.txt");
                saveListToFile(number, "save/number.txt");
                saveListToFile(email, "save/email.txt");
                saveListToFile(birthday, "save/birthday.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            contactUpdate();
        }
    
        private void saveListToFile(ArrayList<String> list, String fileName) throws IOException {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (String item : list) {
                    writer.write(item);
                    writer.newLine();
                }
            }
        }
    
        public void loadContacts() {
            try {
                firstName = loadListFromFile("save/firstname.txt");
                lastName = loadListFromFile("save/lastname.txt");
                number = loadListFromFile("save/number.txt");
                email = loadListFromFile("save/email.txt");
                birthday = loadListFromFile("save/birthday.txt");
                contact.add(firstName);
                contact.add(lastName);
                contact.add(number);
                contact.add(email);
                contact.add(birthday);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private ArrayList<String> loadListFromFile(String fileName) throws IOException {
            ArrayList<String> list = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    list.add(line);
                }
            }
            return list;
        }

        public void eraseContacts(boolean permission) {
            if (!permission) {return;}
            if (contact.get(0).size()<1) {System.out.println("You don't have any contacts."); return;}
            for (ArrayList<String> i : contact) {
                i.clear();
            }
            saveContacts();
        }
}
