package SpringMVC.blog4gs.model.content;

/**
 * Created by Administrator on 2017/5/29.
 */
public class UpdateArticleInBean {
    private int articleId;
    private String headInfo;
    private String content;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getHeadInfo() {
        return headInfo;
    }

    public void setHeadInfo(String headInfo) {
        this.headInfo = headInfo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "UpdateArticleInBean{" +
                "articleId=" + articleId +
                ", headInfo='" + headInfo + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
