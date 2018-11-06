package controller;

import com.nf.Entities.GoodsDetails;
import com.nf.Entities.GoodsType;
import com.nf.service.IGoodsDetails;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/excelController")
public class ExcelController {

    @Autowired
    IGoodsDetails goodsService;

    /**
     * 导出csv表格
     *
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/exploreCsv",method = RequestMethod.GET)
    @ResponseBody
    public void joinXml(HttpServletResponse response) throws IOException {

        Map<String,Object> objectMap=new HashMap<>();
        objectMap.put("tname","");
        objectMap.put("gname","");
        objectMap.put("tid","");
        objectMap.put("startprice",1);
        objectMap.put("endprice",999);
        objectMap.put("pageno",0);
        objectMap.put("size",99999);

        //先获得所有数据
        List<GoodsDetails> goodsList=goodsService.selectAllGoodsAndTypeByLimit(objectMap);

        response.setHeader("Content-Type","application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename="+new String("GoodsData".getBytes(),"UTF-8")+".csv");
        PrintWriter out=response.getWriter();
        //加上bom头,解决excel打开乱码问题
        byte[] bomStrByteArr = new byte[] { (byte) 0xef, (byte) 0xbb, (byte) 0xbf };
        String bomStr = new String(bomStrByteArr, "UTF-8");
        out.write(bomStr);
        StringBuffer str=new StringBuffer("");
        //数据的来源
        str.append("产品编号,产品名称,产品价格,图片地址,产品类型\r\n");
        for (GoodsDetails item:goodsList) {

            System.out.println(item);

            str.append(item.getGid()+","+item.getGname()+","+item.getGprice()+","+item.getGpicture()+","
                    +item.getGoodsType().getTname()+"\r\n");
        }
        response.getWriter().write(str.toString());
    }

    /**
     * 导出Excel表格
     *
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/exploreExcel")
    @SuppressWarnings("resource")
    public void excel(HttpServletResponse response) throws IOException {

        Map<String,Object> objectMap=new HashMap<>();
        objectMap.put("tname","");
        objectMap.put("gname","");
        objectMap.put("tid","");
        objectMap.put("startprice",1);
        objectMap.put("endprice",999);
        objectMap.put("pageno",0);
        objectMap.put("size",99999);

        //先获得所有数据
        List<GoodsDetails> goodsList=goodsService.selectAllGoodsAndTypeByLimit(objectMap);

        //设置标题
        String head = "产品信息";
        //设置表头行
        String[] headrow = {"产品编号", "产品名称", "产品价格","产品图片地址","产品类型"};

        if (null != goodsList && goodsList.size() > 0) {
            String fileName = "产品信息.xls";//定义导出头
            response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));    //设置文件头编码格式
            response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");//设置类型
            response.setHeader("Cache-Control", "no-cache");//设置头
            response.setDateHeader("Expires", 0);//设置日期头

            //创建工作簿HSSFWorkbook 对象
            HSSFWorkbook book = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = book.createSheet();
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            HSSFRow row = sheet.createRow(0);
            //由工作簿创建表HSSFSheet对象
            CellStyle cellStyle = book.createCellStyle();

            cellStyle.setDataFormat(book.createDataFormat().getFormat("yyyy-MM-dd"));

            //设置表头
            HSSFCell cell = row.createCell((short) 0);
            cell.setCellValue(head);
            row = sheet.createRow(1);
            for (int i = 0; i < headrow.length; i++) {
                cell = row.createCell((short) i);
                cell.setCellValue(headrow[i]);
            }

            for (int i = 0; i < goodsList.size(); i++) {
                //实体类对象
                GoodsDetails emp = goodsList.get(i);
                row = sheet.createRow((i + 2));
                row.createCell((short) 0).setCellValue(emp.getGid());
                row.createCell((short) 1).setCellValue(emp.getGname());
                row.createCell((short) 2).setCellValue(emp.getGprice());
                row.createCell((short) 3).setCellValue(emp.getGpicture());
                row.createCell((short) 4).setCellValue(emp.getGoodsType().getTname());
            }
            //写出流  刷新缓冲流  关闭流对象
            book.write(response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
    }


    @Resource
    IGoodsDetails igoodsDetails;

    /**
     * 导入ToExcel表格的数据
     *
     * @throws IOException
     */
    @RequestMapping(value = "/importExcels",method = RequestMethod.POST)
    @SuppressWarnings("resource")
    public String excels(MultipartFile files, HttpServletResponse response, HttpServletRequest request) throws IOException {

        //文件存放的位置
        String path=request.getSession().getServletContext().getRealPath("/files");
        //判断文件夹是否存在
        File file=new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        //保存文件
        File tempFile=new File(path, files.getOriginalFilename());
        files.transferTo(tempFile);//把文件从内存存到磁盘中
        System.out.println(path+"\\"+files.getOriginalFilename());

        //Excel导入数据
        InputStream is = new FileInputStream(path+"\\"+files.getOriginalFilename());
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        GoodsDetails emp = null;
        List<GoodsDetails> list = new ArrayList<GoodsDetails>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 0; rowNum < xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                    emp = new GoodsDetails();
                   // XSSFCell gid = xssfRow.getCell(0);
                    XSSFCell gname = xssfRow.getCell(1);
                    XSSFCell gprice = xssfRow.getCell(2);
                    XSSFCell gpicture = xssfRow.getCell(3);
                    XSSFCell typename = xssfRow.getCell(4);


                    System.out.println("------------------------");
                    System.out.println(gname+","+gprice+","+gpicture+","+typename);

                    int typeid=0;
                    if(typename.toString().equals("小吃")){
                        typeid=1;
                    }else if(typename.toString().equals("特产")){
                        typeid=2;
                    }else if(typename.toString().equals("饮料")){
                        typeid=3;
                    }


                    //monthly类型转换
                   // double monthly2=Double.parseDouble(monthly.toString());
                   //int monthly3=(int)monthly2;

                    //eid类型转换
                   // double eid2=Double.parseDouble(gid.toString());
                    //int eid3=(int)eid2;

                    //BigDecimal类型转换
                    Double gprice0=Double.parseDouble(String.valueOf(gprice));
                   // BigDecimal gprice1=BigDecimal.valueOf(gprice0);

                   // String gtypename=typename.toString();

                    //emp.setGid(eid3);
                    emp.setGname(getValue(gname));
                    emp.setGprice(gprice0);
                    emp.setGpicture(getValue(gpicture));
                    emp.setTid(typeid);
                    list.add(emp);
                }
            }
        }

        /*把导入的数据存放到数据库*/
        for (GoodsDetails goodsDetails:list){
            igoodsDetails.insert(goodsDetails);
        }

        System.out.println("这是导入的数据："+list);
        return "redirect:http://localhost:8080/";
    }

    @SuppressWarnings("static-access")
    private String getValue(XSSFCell xssfCell) {
        if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(xssfCell.getBooleanCellValue());
        } else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            return String.valueOf(xssfCell.getNumericCellValue());
        } else {
            // 返回字符串类型的值
            return String.valueOf(xssfCell.getStringCellValue());
        }
    }
}
