package ru.avalov.javapp.j110.onedirlist;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        OneDirList<String> lst = new OneDirList<>();

        lst.addToHead("str123");
        lst.addToTail("456");
        lst.addToTail("789");
        lst.addToTail("7");
        lst.addToTail("8888");

        for (String s : lst) {
            System.out.println(s);
        }
        System.out.println();

        for (String s : lst.iterateSince("7")) {
            System.out.println(s);
        }


    }
}