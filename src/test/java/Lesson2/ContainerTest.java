package Lesson2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContainerTest {

    @Test
    void GetCountOneContainer(){
        //given
        Container container = new Container();
        container.addElement(new Element(10));
        container.addElement(new Element(10));
        container.addElement(new Element(10));
        //when
        int result = container.getCount();
        //then
        Assertions.assertEquals(30, result);
    }

    @Test
    void GetCountAllContainer(){
        //given
        Container container1 = new Container();
        Container container2 = new Container();
        Container container3 = new Container();

        container1.addElement(new Element(10));
        container1.addElement(new Element(10));  //20

        container2.addElement(new Element(100));
        container2.addElement(container1);              //120

        container3.addElement(container1);
        container3.addElement(container2);              //140

        //when
        int result = container3.getCount();
        //then
        Assertions.assertEquals(140, result);
    }

    @Test
    void GetCountContainerRemoveElement(){
        //given
        Container container1 = new Container();
        Container container2 = new Container();
        Container container3 = new Container();

        container1.addElement(new Element(10));
        container1.addElement(new Element(10));  //20

        container2.addElement(new Element(100));
        container2.addElement(container1);
        container2.addElement(new Element(30));   //150

        container3.addElement(container1);
        container3.addElement(container2);              //170

        container2.removeElement(0);        //70


        //when
        int result = container3.getCount();
        //then
        Assertions.assertEquals(70, result);
    }
}