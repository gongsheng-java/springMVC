package SpringMVC.blog4gs.service.classify;

import SpringMVC.blog4gs.dao.classify.IClassifyDao;
import SpringMVC.blog4gs.model.RetInfo;
import SpringMVC.blog4gs.model.classify.ClassifyBeanIn;
import SpringMVC.blog4gs.model.classify.ClassifyBeanOut;
import SpringMVC.blog4gs.model.login.SessionInfo;
import SpringMVC.blog4gs.model4db.Classifydb;
import SpringMVC.blog4gs.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */
@Service
public class ClassifyServ {
    @Autowired
    private IClassifyDao classifyDao;
    @Autowired
    private HttpServletRequest requestByAuto;
    public RetInfo addClass(ClassifyBeanIn classifyBeanIn){
        RetInfo retInfo = new RetInfo();
        Classifydb classifydb = new Classifydb();
        //SessionInfo sessionInfo = CommonUtil.getSession();
        SessionInfo sessionInfo =(SessionInfo)requestByAuto.getSession().getAttribute("loginInfo");
        classifydb.setUserId(sessionInfo.getUserId()); // TODO
        classifydb.setClassifyName(classifyBeanIn.getClassifyName());
        try{
            int tmp =0;
            tmp = classifyDao.addClass(classifydb);
            if(tmp==1){
                retInfo.setRetCode("000000");
                retInfo.setRetMsg("添加分类成功");
                return retInfo;
            }else{
                retInfo.setRetCode("999999");
                retInfo.setRetMsg("添加分类失败");
                return retInfo;
            }
        }catch (Exception e){
            System.out.println(e);
        }
       // System.out.println(classifyDao.addClass(classifydb));
        return null;
    }
    public int removeClass(String classifyId){
        SessionInfo sessionInfo =(SessionInfo)requestByAuto.getSession().getAttribute("loginInfo");
        int i=0;
        try {
            i = classifyDao.removeClass(sessionInfo.getUserId(),classifyId); //TODO
            System.out.println(i);
        }catch (Exception e){
            System.out.println("删除失败啦"+e);
        }

        return i;
    }

    public RetInfo findClass(){
        RetInfo retInfo = new RetInfo();
        SessionInfo sessionInfo =(SessionInfo)requestByAuto.getSession().getAttribute("loginInfo");
        List<ClassifyBeanOut> classifyBeanOut =classifyDao.findClass(sessionInfo.getUserId());
        retInfo = CommonUtil.setRetInfo("000000","查询分类成功",classifyBeanOut);
        System.out.println("=======classifyBeanOut====="+classifyBeanOut);
        return retInfo;
    }

    public int updateClass(String classifyId,String classifyName){
        RetInfo retInfo = new RetInfo();
        SessionInfo sessionInfo =(SessionInfo)requestByAuto.getSession().getAttribute("loginInfo");
         int i =0;
         try {
             i=classifyDao.updateClass(sessionInfo.getUserId(),classifyId,classifyName);
             System.out.println("====修改行数==="+i);
         }catch (Exception e){
             System.out.println(e);
             System.out.println("错误啦");
         }
        return i;
    }

}
