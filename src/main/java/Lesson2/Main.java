package Lesson2;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Container container1 = new Container();
        container1.addElement(new Element(10));
        container1.addElement(new Element(20));
        System.out.println("Container1 = " + container1.getCount());

        Container container2 = new Container();
        container2.addElement(new Element(30));
        container2.addElement(new Element(40));
        System.out.println("Container2 = " + container2.getCount());

        Container container3 = new Container();
        container3.addElement(new Element(50));
        container3.addElement(container2);
        System.out.println("Container3 = " + container3.getCount());

        Container container4 = new Container();
        container4.addElement(container1);
        container4.addElement(container2);
        container4.addElement(container3);
        System.out.println("Container4 = " + container4.getCount());
        container4.removeElement(0);
        System.out.println("Container4 - Container1= " + container4.getCount());








    }
}