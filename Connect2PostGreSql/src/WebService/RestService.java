package WebService;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class RestService {

    private final long id;
    private final String content;

    public RestService(long id, String content) {
        this.id = id;
        this.content = content;
    }
    
    @WebMethod
    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}