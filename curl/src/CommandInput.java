import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandInput {
    public static void main(String[] args) {

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        System.out.println("キーボードから入力してください");

        String str = null;
        List<String> command = new ArrayList<String>();
        try {
            str = br.readLine();
            String[] tempArray = str.split(" ");
            command.addAll(Arrays.asList(tempArray));
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("入力されたコマンドは「" + str + "」です");
        if(!command.get(0).equals("curl"))
        {
            System.out.println("コマンドが入力されていません");
        }
        else {
            System.out.println("コマンドが入力されました");
            command.remove("curl");
            String url = "";
            for (int i = 0;i < command.size();i++){
                if(command.get(i).startsWith("http")){
                    url = command.get(i);
                    command.remove(url);
                }
            }
            if(url.equals("")){
                System.out.println("URLが入力されていないので読み込めません");
            }
            else {
                CommandRun commandRun = new CommandRun();
                commandRun.url = url;
                for(int i = 0;i < command.size();i++){
                    commandRun.commandList.add(command.get(i));
                }
                System.out.println("command = " +command.size() + ":commandList = " + commandRun.commandList.size());
                commandRun.run();
            }

        }

    }
}
