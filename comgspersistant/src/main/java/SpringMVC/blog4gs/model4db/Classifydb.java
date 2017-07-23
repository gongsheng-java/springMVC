package SpringMVC.blog4gs.model4db;

/**
 * Created by Administrator on 2017/5/17.
 */
public class Classifydb {
    private String userId;
    private String classifyName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    @Override
    public String toString() {
        return "Classifydb{" +
                "userId='" + userId + '\'' +
                ", classifyName='" + classifyName + '\'' +
                '}';
    }
}
