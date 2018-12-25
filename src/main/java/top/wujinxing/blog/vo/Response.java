package top.wujinxing.blog.vo;

/**
 * @author: wujinxing
 * @date: 2018/12/21 13:26
 * @description: 统一处理返回对象
 */
public class Response {

    private boolean success;    //处理是否成功
    private String message;     //处理的消息提示
    private Object body;    //相应处理的返回内容

    public boolean isSuccess() {
        return success;
    }

    public Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Response(boolean success, String message, Object body) {
        this.success = success;
        this.message = message;
        this.body = body;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
