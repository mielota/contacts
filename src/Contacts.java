// To read user's input
import java.util.Scanner;

// To store that user's contacts
import java.util.ArrayList;

public class Contacts {

    // Creates every array
    public static ArrayList<ArrayList<String>> contact = new ArrayList<ArrayList<String>>();
    public static ArrayList<String> firstName = new ArrayList<String>();
    public static ArrayList<String> lastName = new ArrayList<String>();
    public static ArrayList<String> number = new ArrayList<String>();
    public static ArrayList<String> email = new ArrayList<String>();
    public static ArrayList<String> birthday = new ArrayList<String>();

    // Some variables
    public static boolean run = true;

    // User's input
    public static Scanner scan = new Scanner(System.in);

    public static void contactIni() {
        // Makes a big ArrayList of ArrayList
        contact.add(firstName);
        contact.add(lastName);
        contact.add(number);
        contact.add(email);
        contact.add(birthday);
    }

    public static void loadDefaultContact() {
        // Create some fake, default, contacts.
        firstName.add("Mielota");
        firstName.add("Bro");
        firstName.add("Cristaline");
        firstName.add("Maxilase");

        lastName.add("Mi");
        lastName.add("Mi");
        lastName.add("Eau de source");
        lastName.add("Sanofi");

        number.add("07XXXXXXXX");
        number.add("06XXXXXXXX");
        number.add("09XXXXXXXX");
        number.add("09XXXXXXXX");

        email.add("cest@decoratif.com");
        email.add("tktp@cvbiensepasser.fr");
        email.add("moneau@cristaline.fr");
        email.add("mauxdegorge@alpha-amylase.com");


        birthday.add("18/03/2006");
        birthday.add("18/03/2006");
        birthday.add("15/??/????");
        birthday.add("AB/CD/EFGH");
    }

    public static String indentDisplay(String j) {
        // Function so that it's pretty when you read the command line

        int length=32;
        int flenght = length - j.length();

        for (int i=0;i<flenght;i++) {
            j=j+" ";
        }

        return j;
    }

    public static void displayPrep() {
        // Function so that it's pretty when you read the command line
        System.out.print("First name :                    ");
        System.out.print("Last name :                     ");
        System.out.print("Phone number :                  ");
        System.out.print("email :                         ");
        System.out.print("birthday :  ");
        System.out.println();
    }

    public static void displayContact() {
        // Shows every contacts
        displayPrep();
        int numberOfContact = contact.get(0).size();
        int i=0;
        
        System.out.println();
        while (i<numberOfContact) {
            for (int j=0;j<contact.size();j++) {
                System.out.print(indentDisplay(contact.get(j).get(i)));
            }
            i++;
            System.out.println();
        }
        System.out.println();
    }

    public static void displayOneContact(String target, int n) {
        // display every contact according to the String in the "target" parameter
        boolean flag = true;
        int c = 0;
        if (n==0||n==1) {
            target = toName(target);
        }
        for (int i=0;i<contact.get(n).size();i++) {
            if (contact.get(n).get(i).equals(target)) {
                if (flag) {
                    displayPrep();
                    flag = false;
                }
                for (int j=0;j<contact.size();j++) {
                    System.out.print(indentDisplay(contact.get(j).get(i)));
                }
                c++;
                System.out.println();
            }
        }
        System.out.println("Done :"+c+" contacts were found");
    }

    public static String loadCommandLine(String s) {
        // Takes user's input with a custom prompt
        System.out.print(">>> "+s+" ");
        String cmd = scan.nextLine();
        return cmd;
    }

    public static void notACommand(String cmd) {
        // Error message when the command doesn't exists
        System.out.println("The following command : \""+cmd+"\" doesn't exists");
        System.out.println("Please use the \"help\" command");
    }

    public static void command() {
        // Asks for command's execution after getting it from the user
        // Ignores the request if the command is blank
        String cmd = loadCommandLine("$").toLowerCase().trim();
        if (!cmd.isBlank()) {
            execCommand(cmd);
        }
    }

    public static String getInfo() {
        String info = loadCommandLine("?");
        if (info.isBlank()) {
            info = "-";
        }
        return info;
    }

    public static ArrayList<Integer> getContactPos(String target, int n) {
        // Return an Integer ArrayList of contact's actual position in the contact ArrayList
        ArrayList<Integer> pos = new ArrayList<Integer>();
        if (n==0||n==1) {
            target = toName(target);
        }
        for (int i=0;i<contact.get(n).size();i++) {
            if (contact.get(n).get(i).equals(target)) {
                pos.add(i);
            }
        }

        return pos;
    }

    public static ArrayList<Integer> getLivePos(String target, int n) {
        // Return an ArrayList of contact's actual position knowing that the contact ArrayList size is decreasing by one
        // Example : {4, 7, 8} from getContactPos becomes {4, 6, 6} ({4-0, 7-1, 6-2})
        ArrayList<Integer> pos = getContactPos(target, n);
        ArrayList<Integer> swap = new ArrayList<Integer>();
        for (int i=0;i<pos.size();i++) {
            swap.add(pos.get(i)-i);
        }
        return swap;
    }

    public static String toName(String info) {
        // transforms any String to a specific format.
        // Example : "honey/hOney/hONeY" becomes "Honey"
        info = info.trim();
        String swap = String.valueOf(info.charAt(0));
        info = info.substring(1).toLowerCase();
        swap = swap.toUpperCase();
        swap += info;
        return swap;
    }

    public static void clear() {
        // Clear the terminal under linux
        try {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("Error : Couldn't clear the terminal");
        }
    }
    
    public static void addContact() {
        // Adds a contact at the end of the list
        System.out.print("first name : ");
        firstName.add(toName(getInfo()));
        System.out.print("last name : ");
        lastName.add(toName(getInfo()));
        System.out.print("phone number :");
        number.add(getInfo());
        System.out.print("email : ");
        email.add(getInfo());
        System.out.print("birthday : ");
        birthday.add(getInfo());
    }

    public static void removeContact(String target, int n) {
        ArrayList<Integer> pos = getLivePos(target, n);
        int index = -1;
        for (int i=0;i<pos.size();i++) {
            index = pos.get(i);
            for (int j=0;j<contact.size();j++) {
                contact.get(j).remove(index);
            }
        }
        
    }

    public static void help() {
        // User's manual
        System.out.println("""

                Lists of basic commands :

                exit            Shuts the program down
                help            Display this message
                clear           Clear terminal
                show            Displays every contacts
                add             Add a new contact
                load-default    Add some defaults contact

                List of commands to properly manage your list of contacts

                find-firstname  show contacts that have the same firstname as specified in the next prompt
                find-lastname                                    lastname
                find-number                                      number
                find-email                                       email
                find-birthday                                    birthday

                del-firstname   deletes contacts that have the same firstname as specified in the next prompt
                del-lastname                                        lastname
                del-number                                          number
                del-email                                           email
                del-birthday                                        birthday

                edit-firstname   edit contacts that have the same firstname as specified in the next prompt
                edit-lastname                                     lastname
                edit-number                                       number
                edit-email                                        email
                edit-birthday                                     birthday
                                 (note that you can't edit multiple contacts at the same time)

                List of tips to read the prompt

                ">>> $"           means the program is waiting for a command
                ">>> ?"           means the previous command is asking for an INFO
                                  Example : after using find-firstname, you have to enter
                                  a firstname so that the program searches for it in your contacts

                Warning :         This program needs at least a window that can display at least 140 characters
                                  in a line so that the contacts are displayed properly
                """);
    }

    public static void execCommand(String cmd) {
        // Every command
        switch (cmd) {
            case "" :
                break;
            case "exit" :
                scan.close();
                run = false;
                break;
            case "help" :
                help();
                break;
            case "clear" :
                clear();
                break;
            case "show" :
                displayContact();
                break;
            case "add" :
                addContact();
                break;
            case "load-default" :
                loadDefaultContact();
                break;
            case "find-firstname" :
                displayOneContact(getInfo(), 0);
                break;
            case "find-lastname" :
                displayOneContact(getInfo(), 1);
                break;
            case "find-number" :
                displayOneContact(getInfo(), 2);
                break;
            case "find-email" :
                displayOneContact(getInfo(), 3);
                break;
            case "find-birthday" :
                displayOneContact(getInfo(), 4);
                break;
            case "del-firstname" :
                removeContact(getInfo(), 0);
                break;
            case "del-lastname" :
                removeContact(getInfo(), 1);
                break;
            case "del-number" :
                removeContact(getInfo(), 2);
                break;
            case "del-email" :
                removeContact(getInfo(), 3);
                break;
            case "del-birthday" :
                removeContact(getInfo(), 4);
                break;
            default :
                notACommand(cmd);
                break;
        }
    }

    public static void main(String[] args) {
        // Main function
        contactIni();
        while (run) {
            command();
        }   
    }
}
