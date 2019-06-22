package com.nuesoft.dao;

import com.nuesoft.po.BookInfo;
import com.nuesoft.po.BookInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookInfoMapper {
    int deleteByPrimaryKey(Integer bookId);

    int insert(BookInfo record);

    int insertSelective(BookInfo record);

    List<BookInfo> selectByExample(BookInfoExample example);

    BookInfo selectByPrimaryKey(Integer bookId);

    int updateByPrimaryKeySelective(BookInfo record);

    int updateByPrimaryKey(BookInfo record);

    //查询所有书籍信息（包含书籍类型名称）
    List<BookInfo> selectAllBookInfos(@Param("booktype") Integer booktype,@Param("bookname") String bookname,@Param("isborrow") Integer isborrow);
    //批量删除
    int deleteByIds(String[] ids);
    //根据id查询一条数据包含类型名称
    BookInfo  selectOneBook(int bookId);
}