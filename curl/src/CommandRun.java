import java.util.ArrayList;
import java.util.List;

public class CommandRun {
    public  String url;
    public List<String> commandList = new ArrayList<>();
    public  void run(){
        Curl curl = new Curl();
        if(commandList.size() == 0)
        {
            curl.CurlHttp();
        }
    }
}
