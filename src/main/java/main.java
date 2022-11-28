import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Scanner;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;

public class main 
{
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    public static void main(String[] args) throws GeneralSecurityException, IOException, GoogleJsonResponseException 
    {
        YouTube youtubeService = GatherData.getService();

        YouTube.Videos.List statsRequest = youtubeService.videos().list("statistics");
        
        InputStream inputStream = new FileInputStream("output.json");

        JsonParser parser = JSON_FACTORY.createJsonParser(inputStream, StandardCharsets.UTF_8);
    
        Node node = GatherData.populateNode(parser, statsRequest);

        Scanner scan = new Scanner(System.in);
        boolean run = true;

        String title = ("____    ____  ______    __    __  .___________. __    __  .______    _______     _______       ___   .___________.    ___              ___      .__   __.      ___       __      ____    ____  _______. __       _______." + "\n" +
                            "\\   \\  /   / /  __  \\  |  |  |  | |           ||  |  |  | |   _  \\  |   ____|   |       \\     /   \\  |           |   /   \\            /   \\     |  \\ |  |     /   \\     |  |     \\   \\  /   / /       ||  |     /       |" + "\n" +
                             " \\   \\/   / |  |  |  | |  |  |  | `---|  |----`|  |  |  | |  |_)  | |  |__      |  .--.  |   /  ^  \\ `---|  |----`  /  ^  \\          /  ^  \\    |   \\|  |    /  ^  \\    |  |      \\   \\/   / |   (----`|  |    |   (----`" + "\n" +
                                "    |  |    |  `--'  | |  `--'  |     |  |     |  `--'  | |  |_)  | |  |____    |  '--'  | /  _____  \\   |  |     /  _____  \\      /  _____  \\  |  |\\   |  /  _____  \\  |  `----.    |  | .----)   |   |  | .----)   |" + "\n" +
                                "    |__|     \\______/   \\______/      |__|      \\______/  |______/  |_______|   |_______/ /__/     \\__\\  |__|    /__/     \\__\\    /__/     \\__\\ |__| \\__| /__/     \\__\\ |_______|    |__| |_______/    |__| |_______/" + "\n");

        String menu = ("//*****************************************\\\\" + "\n" + 
                            "/* Choose a Number                        *\\" + "\n" +
                            "/*                                        *\\" + "\n" +
                            "/* 1. Print Map                           *\\" + "\n" + 
                            "/* 2. Print Hash map                      *\\" + "\n" +
                            "/* 3. Create Map                          *\\" + "\n" + 
                            "/* 4. Create Hash map                     *\\" + "\n" +
                            "/* 5. Choose Video Type 1                 *\\" + "\n" +
                            "/* 6. Choose Video Type 2                 *\\" + "\n" + 
                            "/* 7. Compare Data types                  *\\" + "\n" +
                            "/* 8. Print this menu again               *\\" + "\n" +
                            "/* 9. Exit                                *\\" + "\n" +
                            "\\\\*****************************************//");
        System.out.println(title + "\n" +  menu);

        
        while(run)
        {
            int input = 0; 
            if(scan.hasNextInt())
                input = scan.nextInt();
            switch(input)
            {
                case 1:
                    GatherData.createJson();
                    break;
                case 2: 
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8: 
                    System.out.println(title + "\n" +  menu);
                    break;
                case 9:
                    System.out.println("Exiting");
                    run = false; 
                    break;
            }

        }
        
        scan.close();
    }
}


  
                                                                                                                                                                                                                            