package SpringMVC.blog4gs.controller.classify;

import SpringMVC.blog4gs.model.RetInfo;
import SpringMVC.blog4gs.model.classify.ClassifyBeanIn;
import SpringMVC.blog4gs.service.classify.ClassifyServ;
import SpringMVC.blog4gs.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Hashtable;

/**
 * Created by Administrator on 2017/5/15.
 */
@Controller
@RequestMapping(value = "/gs")
public class ClassifyControl {
    @Resource
    private ClassifyServ classifyServ;
    @Autowired
    private HttpServletRequest requestByAuto;
    @RequestMapping(value = "/addclass",method = RequestMethod.POST)
    @ResponseBody
    public RetInfo addClass(@RequestBody ClassifyBeanIn classifyBeanIn){
        System.out.println("hello");
        RetInfo retInfo = new RetInfo();
        retInfo =classifyServ.addClass(classifyBeanIn);
        return retInfo;
    }
    @RequestMapping(value = "/removeclass",method=RequestMethod.POST,params = {"classifyId"})
    @ResponseBody
    public RetInfo removeClass(){
        String classifyId = requestByAuto.getParameter("classifyId");
        RetInfo retInfo = new RetInfo();
        int i= classifyServ.removeClass(classifyId);
        if(i>0){
            retInfo = CommonUtil.setRetInfo("000000","删除成功啦");
        }else {
            retInfo = CommonUtil.setRetInfo("999999","删除失败啦");
        }
        return retInfo;
    }

    @RequestMapping(value = "/findclass",method =RequestMethod.GET )
    @ResponseBody
    public RetInfo findClass(){
        RetInfo retInfo = classifyServ.findClass();
        return retInfo;
    }
    @RequestMapping(value = "/updateclass",method=RequestMethod.POST,params = {"classifyId","classifyName"})
    @ResponseBody
    public RetInfo updateClass(){
        RetInfo  retInfo = new RetInfo();
        String classifyId = requestByAuto.getParameter("classifyId");
        String classifyName = requestByAuto.getParameter("classifyName");
        System.out.println(classifyId+"-"+classifyName);
        int i = classifyServ.updateClass(classifyId,classifyName);
        if(i>0){
            retInfo = CommonUtil.setRetInfo("000000","修改成功啦");
        }else {
            retInfo = CommonUtil.setRetInfo("999999","修改失败啦");
        }
        return retInfo;
    }
}
