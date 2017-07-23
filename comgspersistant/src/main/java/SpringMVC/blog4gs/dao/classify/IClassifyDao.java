package SpringMVC.blog4gs.dao.classify;

import SpringMVC.blog4gs.model.classify.ClassifyBeanIn;
import SpringMVC.blog4gs.model.classify.ClassifyBeanOut;
import SpringMVC.blog4gs.model4db.Classifydb;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */
public interface IClassifyDao {
    public int addClass(Classifydb classifydb);
    public int removeClass(@Param(value="userId") String userId,@Param(value="classifyId") String classifyId);
    public List<ClassifyBeanOut> findClass(String userId);
    public int updateClass(@Param(value="userId") String userId,@Param(value="classifyId") String classifyId,@Param(value="classifyName") String classifyName);

}
