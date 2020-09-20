package lesson8;


public class Main {
    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add("Toyota");
        doubleLinkedList.add("Honda");
        doubleLinkedList.add("Subaru");
        doubleLinkedList.add("Mazda");

        System.out.println(doubleLinkedList);

        doubleLinkedList.remove("Toyota");
        System.out.println(doubleLinkedList);
    }
}
