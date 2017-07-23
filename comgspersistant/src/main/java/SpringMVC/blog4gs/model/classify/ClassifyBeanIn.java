package SpringMVC.blog4gs.model.classify;

/**
 * Created by Administrator on 2017/5/17.
 */
public class ClassifyBeanIn {
    private String classifyName;

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    @Override
    public String toString() {
        return "ClassifyBeanIn{" +
                ", classifyName='" + classifyName + '\'' +
                '}';
    }
}
