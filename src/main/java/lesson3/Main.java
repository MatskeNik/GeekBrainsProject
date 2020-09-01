package lesson3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        //Task1
        String[] words = {"один", "два", "три", "четыре","пять", "один", "пять", "семь", "шесть", "десять", "десять", "один", "семь", "шесть", "два", "девять", "восемь", "девять"};
        countUniqueWords(words);

        //Task2
        Phonebook phonebook = new Phonebook();
        phonebook.add("Пушкин", "89991234567");
        phonebook.add("Лермонтов", "81111111111");
        phonebook.add("Пушкин", "82222222222");
        phonebook.add("Пушкин", "83333333333");
        phonebook.add("Одоевский", "87777777777");
        phonebook.add("Чехов", "85555555555");
        phonebook.get("Пушкин");
        phonebook.get("Достоевский");

        phonebook.get("Чехов");
    }

    static void countUniqueWords(String[] words) {
        System.out.println(new HashSet<>(Arrays.asList(words)));
        HashMap<String, Integer> wordsMap = new HashMap<>();
        for (String word : words) {
            int count;
            if(wordsMap.containsKey(word)) {
                count = wordsMap.get(word)+1;
            } else {
                count = 1;
            }
            wordsMap.put(word, count);
        }
        System.out.println(wordsMap);
    }
}
