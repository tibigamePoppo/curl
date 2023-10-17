import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * コマンドの入力に関するクラス
 */
public class CommandInput {
    public static void main(String[] args) {

        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        System.out.println("キーボードから入力してください");

        String _input;
        List<String> command = new ArrayList<>();
        try {
            _input = bufferedReader.readLine();
            String[] tempArray = _input.split(" ");
            command.addAll(Arrays.asList(tempArray));
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!command.contains("curl"))
        {
            System.out.println("有効なコマンドが入力されていません");
        }
        else {  //コマンドが入力されているかの確認
            command.remove("curl");
            String url = "";
            for (int i = 0;i < command.size();i++){
                if(command.get(i).startsWith("http")){
                    url = command.get(i);
                    command.remove(url);
                }
            }
            if(url.isEmpty()){  //URLが入力されているかの確認
                System.out.println("URLが入力されていないので読み込めません");
            }
            else {
                CommandRun commandRun = new CommandRun();
                commandRun.url = url;                               //URLを設定
                commandRun.commandList.addAll(command);
                commandRun.run();
            }

        }

    }
}
