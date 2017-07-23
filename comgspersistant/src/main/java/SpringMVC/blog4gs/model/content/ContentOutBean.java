package SpringMVC.blog4gs.model.content;

/**
 * Created by Administrator on 2017/6/2.
 */
public class ContentOutBean {
    private int classifyId;
    private String headStr;
    private String articleId;
    private String content;
    private int favCount;
    private int downloadCount;
    private int notifyLevel;

    public String getHeadStr() {
        return headStr;
    }

    public void setHeadStr(String headStr) {
        this.headStr = headStr;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFavCount() {
        return favCount;
    }

    public void setFavCount(int favCount) {
        this.favCount = favCount;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public int getNotifyLevel() {
        return notifyLevel;
    }

    public void setNotifyLevel(int notifyLevel) {
        this.notifyLevel = notifyLevel;
    }


    public int getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(int classifyId) {
        this.classifyId = classifyId;
    }

    @Override
    public String toString() {
        return "ContentOutBean{" +
                ", classifyId=" + classifyId +
                ", headStr='" + headStr + '\'' +
                ", articleId='" + articleId + '\'' +
                ", content='" + content + '\'' +
                ", favCount=" + favCount +
                ", downloadCount=" + downloadCount +
                ", notifyLevel=" + notifyLevel +
                '}';
    }
}
