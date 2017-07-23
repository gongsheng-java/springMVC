package SpringMVC.blog4gs.model.classify;

/**
 * Created by Administrator on 2017/5/21.
 */
public class ClassifyBeanOut {
    private int id;
    private String createTime;
    private String classifyName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    @Override
    public String toString() {
        return "ClassifyBeanOut{" +
                "id=" + id +
                ", createTime='" + createTime + '\'' +
                ", classifyName='" + classifyName + '\'' +
                '}';
    }
}
