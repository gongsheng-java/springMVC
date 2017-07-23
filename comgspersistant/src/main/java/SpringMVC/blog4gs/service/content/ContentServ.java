package SpringMVC.blog4gs.service.content;

import SpringMVC.blog4gs.dao.content.IContentDao;
import SpringMVC.blog4gs.model.RetInfo;
import SpringMVC.blog4gs.model.content.AddContentOutBean;
import SpringMVC.blog4gs.model.content.ContentOutBean;
import SpringMVC.blog4gs.model.content.UpdateArticleInBean;
import SpringMVC.blog4gs.model.login.SessionInfo;
import SpringMVC.blog4gs.model4db.ContentDB;
import SpringMVC.blog4gs.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/1.
 */
@Service
public class ContentServ {
    @Autowired
    private IContentDao contentDao;
    @Autowired
    private HttpServletRequest requestByAuto;
    public RetInfo addDefaultArticle(String classifyId){
        RetInfo retInfo = new RetInfo();
        System.out.println(classifyId);
        SessionInfo sessionInfo =(SessionInfo)requestByAuto.getSession().getAttribute("loginInfo");
        int i =-1;
        try {
            Integer emptyCount = contentDao.findEmptyArticle(sessionInfo.getUserId(), classifyId);  //此处为了避免查到null
            System.out.println("===emptyCount===" + emptyCount);
            AddContentOutBean addContentOutBean = new AddContentOutBean();
            if (emptyCount!=null&&emptyCount >0) {  //没有为空的标题和内容
                //TODO
                addContentOutBean.setId(emptyCount);
                retInfo =CommonUtil.setRetInfo("100001","请使用已存在空的内容",addContentOutBean);
                return retInfo;
            }
            ContentDB contentDB = new ContentDB();
            contentDB.setUserId(sessionInfo.getUserId());
            contentDB.setClassifyId(classifyId);
            i = contentDao.addDefaultArticle(contentDB);
            addContentOutBean.setId(contentDB.getId());
            retInfo =CommonUtil.setRetInfo("000000","添加默认空文章成功",addContentOutBean);
            //System.out.println(generatedKeydb.getId());
        }catch (Exception e){
            System.out.println(e);
            retInfo =CommonUtil.setRetInfo("999999","添加默认空文章异常");
            return retInfo;
        }
        return retInfo;
    }
    public RetInfo updateArticle(UpdateArticleInBean updateArticleInBean){ //前端需要使用缓存
        RetInfo retInfo = new RetInfo();
        try {
            int i =contentDao.updateArticle(updateArticleInBean);
            if(i>0){
                retInfo = CommonUtil.setRetInfo("000000","更新内容成功");
            }else{
                retInfo = CommonUtil.setRetInfo("999999","更新失败，应该回退到之前的光标位置，id不对");
            }
        }catch (Exception e){
            System.out.println(e);
            retInfo = CommonUtil.setRetInfo("999999","更新失败，应该回退到之前的光标位置");
        }
        return retInfo;
    }
    public RetInfo deleteArticle(String articleId){
        RetInfo retInfo = new RetInfo();
        SessionInfo sessionInfo =(SessionInfo)requestByAuto.getSession().getAttribute("loginInfo");
        try {
            int i =contentDao.deleteArticle(sessionInfo.getUserId(),articleId);
            if(i>0){
                retInfo = CommonUtil.setRetInfo("000000","删除内容成功");
            }else{
                retInfo = CommonUtil.setRetInfo("999999","删除失败");
            }
        }catch (Exception e){
            System.out.println(e);
            retInfo = CommonUtil.setRetInfo("999999","删除失败");
        }
        return retInfo;
    }
    public RetInfo findArtice(String classifyId){//classifyId为0则查询全部分类下的文章
        //RetInfo retInfo = new RetInfo();
        SessionInfo sessionInfo =(SessionInfo)requestByAuto.getSession().getAttribute("loginInfo");
        List<ContentOutBean> contentOutList = new LinkedList<ContentOutBean>();
        try {
            contentOutList =contentDao.findArticle(sessionInfo.getUserId(),classifyId);
            return CommonUtil.setRetInfo("000000","查询成功",contentOutList);
        }catch (Exception e){
            System.out.println(e);
            return CommonUtil.setRetInfo("999999","查询失败");
        }
    }
}
