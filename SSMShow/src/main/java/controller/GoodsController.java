package controller;

import com.nf.Entities.GoodsDetails;
import com.nf.commons.MyUtils.Standard;
import com.nf.impl.GoodsDetailsImpl;
import com.nf.service.IGoodsDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Service
@RequestMapping(path = "/goodsController")
public class GoodsController {

    /*
    * 1、自动封装对象，
    * 2、或者获取对象
    * */

    @Autowired
    IGoodsDetails goodsService;


    /*
    * 更新
    * */
    @ResponseBody
    @RequestMapping( path = "/update",method = RequestMethod.POST)
    public Object update(@RequestBody GoodsDetails goods){
        int result= goodsService.update(goods);
        if(result>0){
            return goodsService.update(goods);
        }
        return "更新失败。";
    }

    /*
        查询全部商品和类型
    */
    @ResponseBody
    @RequestMapping(path = "/getAllDataCount")
    public int getAllDataCount(){
        return goodsService.selectAllGoodsAndType().size();
    }

    /*
    * 模糊查询总数
    * */
    @ResponseBody
    @RequestMapping(path = "/mohuGetAllGoodsCount")
    public int mohuGetAllGoodsCount(@RequestBody List<Object>mohuListLimit){

        String startprice= "0.0";
        String endprice="99999999.0";

        System.out.println("mohuListLimit:"+mohuListLimit);

        if(!(mohuListLimit.get(2)==""||mohuListLimit.get(2).equals("")||mohuListLimit.get(2)=="0")){
            startprice= (String) mohuListLimit.get(2);
        }
        if(!(mohuListLimit.get(3)==""||mohuListLimit.get(3).equals("")||mohuListLimit.get(3)=="0")){
            endprice= (String) mohuListLimit.get(3);
        }

        System.out.println(mohuListLimit);
        Map<String,Object> objectMap=new HashMap<>();

        objectMap.put("gname",mohuListLimit.get(0));
        objectMap.put("tname",mohuListLimit.get(1));
        objectMap.put("startprice",startprice);
        objectMap.put("endprice",endprice);
        objectMap.put("pageno",0);
        objectMap.put("size",9999999);

        return goodsService.selectAllGoodsAndTypeByLimit(objectMap).size();
    }

    /*
     * 模糊查询总数
     * */
    @ResponseBody
    @RequestMapping(path = "/mohuGetAllGoodsByPage")
    public List<GoodsDetails> mohuGetAllGoodsByPage(@RequestBody List<Object>objlist){

        String startprice= "0.0";
        String endprice="99999999.0";


        if(!(objlist.get(2)==""||objlist.get(2).equals("")||objlist.get(2)=="0")){
            startprice= (String) objlist.get(2);
        }
        if(!(objlist.get(3)==""||objlist.get(3).equals("")||objlist.get(3)=="0")){
            endprice= (String) objlist.get(3);
        }

        Map<String,Object> objectMap=new HashMap<>();

        int page= (int) objlist.get(4);
        int size= (int) objlist.get(5);
        int pageno=(page-1)*size;
        objectMap.put("gname",objlist.get(0));
        objectMap.put("tname",objlist.get(1));
        objectMap.put("startprice",startprice);
        objectMap.put("endprice",endprice);
        objectMap.put("pageno",pageno);
        objectMap.put("size",size);

        return goodsService.selectAllGoodsAndTypeByLimit(objectMap);
    }

    /*
    * 模糊查询+分页
    * */
    @ResponseBody
    @RequestMapping(path = "/mohuSelectWithLimit")
    public List<GoodsDetails> mohuSelectWithLimit(@RequestBody List<Object> objlist){
        System.out.println(objlist);
        Map<String,Object> map=new HashMap<>();
        map.put("startprice",0);
        map.put("endprice",99999990);
        int page= (int) objlist.get(0);
        int size= (int) objlist.get(1);
        int pageno=(page-1)*size;
        map.put("pageno",pageno);
        map.put("size",size);

        System.out.println("=========================");
        System.out.println(goodsService.selectAllGoodsAndTypeByLimit(map));

        return goodsService.selectAllGoodsAndTypeByLimit(map);
    }

    /*
    * 新增
    * */
    @ResponseBody
    @RequestMapping(path = "/insert")
    public Object insert(@RequestBody GoodsDetails goods){
        int result= goodsService.insert(goods);
        if(result>0){
            // return goodsService.selectAllGoodsAndType();
            return "添加成功。";
        }
        return null;
    }

    /*
    * 删除
    * */
    @ResponseBody
    @RequestMapping(path = "/delete",method = RequestMethod.POST)
    public Object delete(@RequestBody Integer gid){
        int result= goodsService.delete(gid);
        if(result>0){
           // return goodsService.selectAllGoodsAndType();
            return "删除成功。";
        }
        return null;
    }

    /*
    * 批量删除
    * */
    @ResponseBody
    @RequestMapping(path = "/deleteAll",method = RequestMethod.POST)
    public Object deleteAll(@RequestBody List<Integer> items){
        System.out.println(items);
        int result=goodsService.deleteAll(items);
        if(result>0){
            return goodsService.selectAllGoodsAndType();
        }
        return "批量删除失败。";
    }

    /*
    * 文件上传
    *
    * */
    @RequestMapping(value = "/fileSave",method = RequestMethod.POST)
    @ResponseBody
    public Standard fileSave(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception{
        //文件存放的位置
        String path=request.getServletContext().getRealPath("/image");
        File fi=new File(path);
        if(!fi.exists()){
            fi.mkdir();
        }
        File tempFile=new File(path, file.getOriginalFilename());
        file.transferTo(tempFile);
        System.out.println(tempFile.getName());
        Standard standard=new Standard();
        standard.put("msg","上传成功!");
        standard.put("data",tempFile.getName());

        return standard;
    }
}