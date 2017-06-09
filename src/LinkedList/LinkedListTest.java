package LinkedList;

import java.util.LinkedList;

/**
 * Created by dy on 2017/6/7.
 */
public class LinkedListTest{

    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        for(int i = 0 ; i < linkedList.size() ; i++) {
            System.out.println(linkedList.get(i));
        }
    }
}
