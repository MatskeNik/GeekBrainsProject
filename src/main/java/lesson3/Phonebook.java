package lesson3;

import java.util.HashMap;
import java.util.HashSet;

public class Phonebook {
    private final HashMap<String, HashSet<String>> phonebook = new HashMap<>();

    public void add(String lastname, String phone) {
        HashSet<String> phones;
        if(phonebook.containsKey(lastname)) {
            phones = phonebook.get(lastname);
        } else {
            phones = new HashSet<>();
        }
        phones.add(phone);
        phonebook.put(lastname, phones);
    }

    public HashSet<String> get(String lastname) {
        if(phonebook.containsKey(lastname)) {
            return phonebook.get(lastname);
        } else {
            return new HashSet<>();
        }
    }
}
