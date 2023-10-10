import java.util.ArrayList;
import java.util.List;

public class CommandRun {
    public  String url;
    public List<String> commandList = new ArrayList<>();
    public  void run(){
        Curl curl = new Curl();
        CommandRun commandRun = new CommandRun();
        if(commandList.isEmpty())
        {
            curl.CurlHttp(url);
        }
        else if(commandList.contains("-o")){
            String fileName;
            fileName = commandRun.getFileName(commandList);
            curl.CurlOFile(fileName,url);
        }
        else if(commandList.contains("-v")){
            curl.CurlVHttp(url);
        }
        else if(commandList.contains("-d") && commandList.contains("-X") &&commandList.contains("POST"))
        {
            String value = "";
            for (String s : commandList) {
                if (s.startsWith("\"key=")) {
                    value = s.split("=")[1];
                }
            }
            value = value.substring(0,value.length()-1);
            curl.CurlXPostD(url,value);
        }
        else if(commandList.contains("-X") &&commandList.contains("POST"))
        {
            curl.CurlXPost(url);
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
