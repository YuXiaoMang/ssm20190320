package com.nuesoft.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nuesoft.po.BookInfo;
import com.nuesoft.po.BookType;
import com.nuesoft.po.BookTypeExample;
import com.nuesoft.service.BookInfoService;
import com.nuesoft.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.List;

@Controller
public class BookInfoController implements ServletContextAware {
    private ServletContext servletContext;
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext=servletContext;
    }
    @Autowired
    private BookInfoService bookInfoService;
    @Autowired
    private BookTypeService bookTypeService;
    @RequestMapping("/toDetails.do")
    public String toDetails(int bookId ,ModelMap map){
        BookInfo bookInfo = bookInfoService.selectOneBook(bookId);
        map.put("bookInfo",bookInfo);
        return "details";
    }

    @RequestMapping("/toupdatepage.do")
    public String toupdatepage(int bookId, ModelMap map){

        List<BookType> bookTypes = bookTypeService.selectByExample(new BookTypeExample());
        map.put("bookTypes",bookTypes);
        BookInfo bookInfo = bookInfoService.selectByPrimaryKey(bookId);
        map.put("bookInfo",bookInfo);

        return "update";
    }
    @RequestMapping("/updateBook.do")
    public String updateById(BookInfo bookInfo){
        bookInfoService.updateByPrimaryKeySelective(bookInfo);
        return "forward:index.do";
    }


    @RequestMapping("/deleteBookByIds.do")
    public String deleteByIds(@RequestParam("bookid") String[] ids){

        bookInfoService.deleteByIds(ids);
        return "forward:index.do";
    }

    @RequestMapping("/deleteById.do")
    public String deleteById(int bookId){

        bookInfoService.deleteByPrimaryKey(bookId);

        return "forward:index.do";
    }

    @RequestMapping("/addBook.do")
    public String inserBookInfo(BookInfo bookInfo){
        bookInfo.setIsBorrow(0);
        int rows = bookInfoService.insertSelective(bookInfo);
        if (rows>0){
            return "forward:index.do";
        }else {
            return "add";
        }
    }

    @ResponseBody
    @RequestMapping("/upload.do")
    public String upload(@RequestParam("fileImage") CommonsMultipartFile fileImage){

        //获取上传文件的路径
        String path = servletContext.getRealPath("/resource/upload/");

        //获取文件名称
        String filename = fileImage.getOriginalFilename();
        //根据文件路径和名称创建一个文件
        File file = new File(path, filename);
        //写文件
        try {
            fileImage.getFileItem().write(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String repath="resource/upload/"+filename;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("repath",repath);
        String jsonString = jsonObject.toJSONString();
        return jsonString;
    }

    @RequestMapping("/add.do")
    public String toAdd(ModelMap map){
        List<BookType> bookTypes = bookTypeService.selectByExample(new BookTypeExample());
        map.put("bookTypes",bookTypes);
        return "add";
    }

    @RequestMapping("/index.do")
    public String selectAllBookInfos(
            @RequestParam(value="bookTypeid",defaultValue = "0",required = false) String booktype,
            @RequestParam(value="bookname",defaultValue = "",required = false) String bookname,
            @RequestParam(value="borrow",defaultValue = "-1",required = false) String isborrow,
            @RequestParam(value="now",defaultValue = "1",required = false) int now,
            ModelMap map){
        List<BookType> bookTypes = bookTypeService.selectByExample(new BookTypeExample());
        map.put("bookTypes",bookTypes);
        map.put("bookTypeid",booktype);
        map.put("bookname",bookname);
        map.put("borrow",isborrow);
        //System.out.println(booktype);
        PageHelper.startPage(now,3);
        List<BookInfo> bookInfos = bookInfoService.selectAllBookInfos(Integer.parseInt(booktype), bookname, Integer.parseInt(isborrow));
        PageInfo<BookInfo> pageInfo = new PageInfo<>(bookInfos);
        map.put("pageInfo",pageInfo);
        return "index";
    }

    @RequestMapping("/test.do")
    public String test(){
        return "index";
    }


}
