package mypackage;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private NotebookDatabase notebookDatabase;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {

        notebookDatabase = new NotebookDatabase();

        System.out.println("Choose option: ");
        System.out.println("1. Show notebook");
        System.out.println("2. Add new person");
        System.out.println("3. Add new mobile number");
        System.out.println("4. Delete person");
        System.out.println("5. Delete mobile number");
        System.out.println("6. Show phone number by surname");
        System.out.println("7. Show surname by phone number");
        System.out.println("8. Show mobile numbers");
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Your command: ");
            int command = scanner.nextInt();

            switch (command) {
                case 1: {
                    for (Person person : notebookDatabase.getNotebook()) {
                        System.out.println(person);
                    }
                    break;
                }
                case 2: {
                    System.out.print("First name: ");
                    String firstName = scanner.next();

                    System.out.print("Surname: ");
                    String surname = scanner.next();

                    System.out.print("Mobile number id: ");
                    int mobileNumberId = scanner.nextInt();

                    notebookDatabase.insertPerson(new Person(firstName, surname, mobileNumberId));

                    break;
                }
                case 3: {
                    System.out.print("Mobile number: ");
                    String mobileNumber = scanner.next();
                    notebookDatabase.insertMobileNumber(mobileNumber);

                    break;
                }
                case 4: {
                    System.out.print("Person id: ");
                    int personId = scanner.nextInt();
                    notebookDatabase.deletePerson(personId);
                    break;
                }
                case 5: {
                    System.out.print("Mobile number id: ");
                    int mobileNumberId = scanner.nextInt();
                    notebookDatabase.deleteMobileNumber(mobileNumberId);
                    break;
                }

                case 6: {
                    System.out.print("Surname: ");
                    String surname = scanner.next();

                    ArrayList<Person> people = notebookDatabase.showMobileNumberBySurname(surname);
                    if (people.size() > 1) {
                        System.out.println("Found several people with this surname.Do you want to specify a name? (y/n)");
                        String ans = scanner.next();
                        if (ans.equals("y")) {
                            System.out.print("First name: ");
                            String firstName = scanner.next();

                            for (Person person : people) {
                                if (person.getFirstName().equals(firstName)) {
                                    System.out.println(person);
                                }
                            }
                        }
                        else{
                            for (Person person : people) {
                                System.out.println(person);
                            }
                        }
                    } else {
                        for (Person person : people) {
                            System.out.println(person);
                        }
                    }
                    break;
                }

                case 7: {
                    System.out.print("Mobile number: ");
                    String mobileNumber = scanner.next();

                    ArrayList<String> surnames = notebookDatabase.showSurnameByMobileNumber(mobileNumber);
                    for (String surname : surnames) {
                        System.out.println("Surname: " + surname);
                    }
                    break;
                }

                case 8: {
                    for (MobileNumber mobileNumber : notebookDatabase.getMobileNumbers()) {
                        System.out.println(mobileNumber);
                    }
                    break;
                }

                default: {
                    System.out.println("Choose correct number!");
                    break;
                }
            }
        }
    }
}
