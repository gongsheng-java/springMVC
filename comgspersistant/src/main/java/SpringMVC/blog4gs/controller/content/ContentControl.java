package SpringMVC.blog4gs.controller.content;

import SpringMVC.blog4gs.model.RetInfo;
import SpringMVC.blog4gs.model.content.AddContentInBean;
import SpringMVC.blog4gs.model.content.UpdateArticleInBean;
import SpringMVC.blog4gs.service.content.ContentServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/6/1.
 */


@Controller
@RequestMapping(value = "/gs")
public class ContentControl {
    @Autowired
    private HttpServletRequest requestByAuto;
    @Resource
    private ContentServ contentServ;
    @RequestMapping(value = "/addArticle",method = RequestMethod.POST)
    @ResponseBody
    public RetInfo addDefaultArticle(@RequestBody AddContentInBean addContentInBean){
        RetInfo retInfo = new RetInfo();
        retInfo = contentServ.addDefaultArticle(addContentInBean.getClassifyId());
        return retInfo;
    }
    @RequestMapping(value = "/updateArticle",method = RequestMethod.POST)
    @ResponseBody
    public RetInfo updatetArticle(@RequestBody UpdateArticleInBean updateArticleInBean){
        RetInfo retInfo = new RetInfo();
        retInfo = contentServ.updateArticle(updateArticleInBean);
        return retInfo;
    }
    @RequestMapping(value = "/deleteArticle",method = RequestMethod.DELETE,params = {"articleId"})
    @ResponseBody
    public RetInfo deleteArticle(){
        RetInfo retInfo = new RetInfo();
        String articleId = requestByAuto.getParameter("articleId");
        retInfo = contentServ.deleteArticle(articleId);
        return retInfo;
    }
    @RequestMapping(value = "/findArticle",method = RequestMethod.GET,params = {"classifyId"})
    @ResponseBody
    public RetInfo findArticle(){
        RetInfo retInfo = new RetInfo();
        String classifyId = requestByAuto.getParameter("classifyId");
        retInfo =contentServ.findArtice(classifyId);
        return retInfo;
    }

}
