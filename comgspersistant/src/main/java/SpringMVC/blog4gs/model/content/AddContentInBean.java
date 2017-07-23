package SpringMVC.blog4gs.model.content;

/**
 * Created by Administrator on 2017/6/2.
 */
public class AddContentInBean {
    private String classifyId;

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    @Override
    public String toString() {
        return "AddContentInBean{" +
                "classifyId='" + classifyId + '\'' +
                '}';
    }
}
