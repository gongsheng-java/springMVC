package SpringMVC.blog4gs.model.content;

/**
 * Created by Administrator on 2017/5/28.
 */
public class AddContentOutBean {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AddContentOutBean{" +
                "id=" + id +
                '}';
    }
}
