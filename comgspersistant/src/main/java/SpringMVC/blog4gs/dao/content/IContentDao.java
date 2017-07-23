package SpringMVC.blog4gs.dao.content;

import SpringMVC.blog4gs.model.RetInfo;
import SpringMVC.blog4gs.model.content.ContentOutBean;
import SpringMVC.blog4gs.model.content.UpdateArticleInBean;
import SpringMVC.blog4gs.model4db.ContentDB;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/6/1.
 */
public interface IContentDao {
    public int addDefaultArticle(ContentDB contentDB);
    public Integer findEmptyArticle(@Param(value = "userId") String userId,@Param(value = "classifyId")  String classifyId);
    public int updateArticle(UpdateArticleInBean updateArticleInBean);
    public int deleteArticle(@Param(value = "userId") String userId,@Param(value = "articleId") String articleId);
    public List<ContentOutBean> findArticle(@Param(value = "userId") String userId,@Param(value = "classifyId")  String classifyId);
}
