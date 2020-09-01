package lesson3;

import java.util.HashMap;
import java.util.HashSet;

public class Phonebook {
    private final HashMap<String, HashSet<String>> phonebook;

    public Phonebook() {
        this.phonebook = new HashMap<>();
    }

    public void add(String lastname, String phone) {
        HashSet<String> phones;
        if(this.phonebook.containsKey(lastname)) {
            phones = this.phonebook.get(lastname);
        } else {
            phones = new HashSet<>();
        }
        phones.add(phone);
        this.phonebook.put(lastname, phones);
    }

    public void get(String lastname) {
        if(this.phonebook.containsKey(lastname)) {
            System.out.println(this.phonebook.get(lastname));
        } else {
            System.out.printf("В справочнике нет фамилии - %s!%n", lastname);
        }

    }
}
