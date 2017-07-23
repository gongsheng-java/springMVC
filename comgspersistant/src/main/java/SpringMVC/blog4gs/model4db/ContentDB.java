package SpringMVC.blog4gs.model4db;

/**
 * Created by Administrator on 2017/5/28.
 */
public class ContentDB {
    private int id;
    private String userId;
    private String classifyId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ContentDB{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", classifyId='" + classifyId + '\'' +
                '}';
    }
}
