import java.util.ArrayList;
import java.util.List;

public class CommandRun {
    public  String url;
    public List<String> commandList = new ArrayList<>();
    public  void run(){
        Curl curl = new Curl();
        CommandRun commandRun = new CommandRun();
        if(commandList.size() == 0)
        {
            curl.CurlHttp();
        }
        else if(commandList.contains("-o")){
            String fileName = "";
            fileName = commandRun.getFileName(commandList);
            curl.CurlOFile(fileName);
        }
    }

    public  String getFileName(List<String> command)
    {
        String fileName = "temp";
        for (int i = 0;i < command.size();i++){
            if(!command.get(i).contains("-") && !command.get(i).equals("POST") && !command.get(i).contains("key")){
                fileName = command.get(i);
            }
        }
        return fileName;
    }
}
