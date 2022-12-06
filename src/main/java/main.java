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

        Scanner scan = new Scanner(System.in);
        boolean run = true;


        String videoOne = "";
        String fileNameOne = "output1.json";
        
        String videoTwo = "";
        String fileNameTwo = "output2.json";

        Map map = new Map();
        HashMap hashMap = new HashMap();
        HashMapOpen hashOpen = new HashMapOpen(false);

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
                            "/* 3. Fill JSON                          *\\" + "\n" + 
                            "/* 4. Create Maps                          *\\" + "\n" +
                            "/* 5. Compare data                        *\\" + "\n" +
                            "/* 6. Print this menu again               *\\" + "\n" +
                            "/* 7. Exit                                *\\" + "\n" +
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
                    System.out.println("What do you want to compare?: " + "\n" + "1. Publish Dates" + "\n" + "2. Views" + "\n" + "3. Like/Dislike count");

                    int choiceMap = 0; 
                    if(scan.hasNextInt())
                        choiceMap = scan.nextInt();
                        
                    map.inOrder(choiceMap);

                    break;
                case 2: 
                    System.out.println("What do you want to compare?: " + "\n" + "1. Publish Dates" + "\n" + "2. Views" + "\n" + "3. Like/Dislike count");

                    int choiceHash = 0;
                    if(scan.hasNextInt())
                        choiceHash = scan.nextInt();

                    hashMap.printHashMap(choiceHash);

                    break;

                case 3:
                /*
                    System.out.println("Enter the first type of video you want to search: ");
                    videoOne = scan.next();
                    System.out.println("Enter the name of the file you want to create: ");
                    fileNameOne = scan.next();
                    System.out.println("Enter the second video type you want to search: ");
                    videoTwo = scan.next();
                    System.out.println("Enter the name of the file you want to create: ");
                    fileNameTwo = scan.next();
                    */
                
                    

                    GatherData.createJson(videoOne, fileNameOne);
                    GatherData.createJson(videoTwo, fileNameTwo);
                    break;
                case 4:
                    InputStream inputStream = new FileInputStream(fileNameOne);
                    InputStream inputStream2 = new FileInputStream(fileNameTwo);

                    JsonParser parser = JSON_FACTORY.createJsonParser(inputStream, StandardCharsets.UTF_8);
                    JsonParser parser2 = JSON_FACTORY.createJsonParser(inputStream2, StandardCharsets.UTF_8);

                    //TODO: Getting error right at 100

                    for(int i = 0; i < 1252; i++)
                    {
                        Node tempNode = GatherData.populateNode(parser, statsRequest);
                        //System.out.println(i);
                        if(i == 100)
                            System.out.print("debug");
                        map.insert(tempNode.getChannelID(),tempNode.getChannelID(), tempNode.getVideoTitle(), tempNode.getPublishDate(), tempNode.getViews(), tempNode.getVideoID(), tempNode.getLikeCount(), tempNode.getDislikeCount());
                        System.out.println(i);
                    }
                    
                    /*
                    for(int i = 0; i < 23; i++)
                    {
                        Node tempNode = GatherData.populateNode(parser2, statsRequest);
                        //System.out.println(i);
                        hashOpen.insert(tempNode.getChannelID(),tempNode.getChannelID(), tempNode.getVideoTitle(), tempNode.getPublishDate(), tempNode.getViews(), tempNode.getVideoID(), tempNode.getLikeCount(), tempNode.getDislikeCount());
                    }
                    */
                

                    break;
                case 5:
                    break;
                case 6: 
                    System.out.println(title + "\n" +  menu);
                    break;
                case 7:
                    System.out.println("Exiting");
                    run = false; 
                    break;
            }

        }
        
        scan.close();
    }
}


  
                                                                                                                                                                                                                            