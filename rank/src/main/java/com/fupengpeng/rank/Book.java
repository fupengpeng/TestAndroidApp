package com.fupengpeng.rank;

import java.util.GregorianCalendar;

/**
 * Created by fp on 2017/6/13.
 */

public class Book  implements Comparable {
    public int id;// 编号
    public String name;// 名称
    public double price; // 价格
    private String author;// 作者
    public GregorianCalendar calendar;// 出版日期

    public Book() {
        this(0, "X", 0.0, new GregorianCalendar(), "");
    }

    public Book(int id, String name, double price, GregorianCalendar calender,
                String author) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.calendar = calender;
        this.author = author;
    }

    @Override
    public int compareTo(Object obj) {
        Book b = (Book) obj;
        return this.id - b.id; // 按书的id比较大小，用于默认排序
    }
}
