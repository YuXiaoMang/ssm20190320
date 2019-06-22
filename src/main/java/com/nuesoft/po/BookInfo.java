package com.nuesoft.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookInfo {
    private Integer bookId;

    private String bookCode;

    private String bookName;

    private Integer bookType;

    private String bookAuthor;

    private String publishPress;

    private Date publishDate;

    private Integer isBorrow;

    private String bookPath;

    private BookType bookTypes;

    public BookInfo() {
    }

    public BookInfo(Integer bookId, String bookCode, String bookName, Integer bookType, String bookAuthor, String publishPress, Date publishDate, Integer isBorrow, String bookPath, BookType bookTypes) {
        this.bookId = bookId;
        this.bookCode = bookCode;
        this.bookName = bookName;
        this.bookType = bookType;
        this.bookAuthor = bookAuthor;
        this.publishPress = publishPress;
        this.publishDate = publishDate;
        this.isBorrow = isBorrow;
        this.bookPath = bookPath;
        this.bookTypes = bookTypes;
    }


    @Override
    public String toString() {
        return "BookInfo{" +
                "bookId=" + bookId +
                ", bookCode='" + bookCode + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookType=" + bookType +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", publishPress='" + publishPress + '\'' +
                ", publishDate=" + publishDate +
                ", isBorrow=" + isBorrow +
                ", bookPath='" + bookPath + '\'' +
                ", bookTypes=" + bookTypes +
                '}';
    }
}