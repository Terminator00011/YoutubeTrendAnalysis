import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.jackson2.JacksonFactory;


public class main 
{
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    public static void main(String[] args) throws GeneralSecurityException, IOException, GoogleJsonResponseException, InterruptedException 
    {
        Scanner scan = new Scanner(System.in);
        boolean run = true;


        String videoOne = "";
        String fileNameOne = "output1.json";
        
        String videoTwo = "";
        String fileNameTwo = "output2.json";

        Map map = new Map();
        HashMap hashMap = new HashMap();
        HashMapOpen hashOpenLinear = new HashMapOpen(true);
        HashMapOpen hashOpenQuad = new HashMapOpen(false);

        String title = ("____    ____  ______    __    __  .___________. __    __  .______    _______     _______       ___   .___________.    ___              ___      .__   __.      ___       __      ____    ____  _______. __       _______." + "\n" +
                            "\\   \\  /   / /  __  \\  |  |  |  | |           ||  |  |  | |   _  \\  |   ____|   |       \\     /   \\  |           |   /   \\            /   \\     |  \\ |  |     /   \\     |  |     \\   \\  /   / /       ||  |     /       |" + "\n" +
                             " \\   \\/   / |  |  |  | |  |  |  | `---|  |----`|  |  |  | |  |_)  | |  |__      |  .--.  |   /  ^  \\ `---|  |----`  /  ^  \\          /  ^  \\    |   \\|  |    /  ^  \\    |  |      \\   \\/   / |   (----`|  |    |   (----`" + "\n" +
                                "    |  |    |  `--'  | |  `--'  |     |  |     |  `--'  | |  |_)  | |  |____    |  '--'  | /  _____  \\   |  |     /  _____  \\      /  _____  \\  |  |\\   |  /  _____  \\  |  `----.    |  | .----)   |   |  | .----)   |" + "\n" +
                                "    |__|     \\______/   \\______/      |__|      \\______/  |______/  |_______|   |_______/ /__/     \\__\\  |__|    /__/     \\__\\    /__/     \\__\\ |__| \\__| /__/     \\__\\ |_______|    |__| |_______/    |__| |_______/" + "\n");

        String menu = ("//*********************************************\\\\" + "\n" + 
                            "/* Choose a Number                            *\\" + "\n" +
                            "/*                                            *\\" + "\n" +
                            "/* 1. Print Map                               *\\" + "\n" + 
                            "/* 2. Print Hash map                          *\\" + "\n" +
                            "/* 3. Print Hash Map with linear addressing   *\\" + "\n" +
                            "/* 4. print Hash Map with Quadratic addressing*\\" + "\n" +
                            "/* 5. Gather Data                             *\\" + "\n" + 
                            "/* 6. Create Maps                             *\\" + "\n" +
                            "/* 7. Compare data                            *\\" + "\n" +
                            "/* 8. Print this menu again                   *\\" + "\n" +
                            "/* 9. Exit                                    *\\" + "\n" +
                            "\\\\*********************************************//" + "\n");
        System.out.println(title + "\n" +  menu);

        
        while(run)
        {
            int input = 6;
        
            if(scan.hasNextInt())
                input = scan.nextInt();
            

            switch(input)
            {
                case 1:
                    System.out.println("What do you want to values?: " + "\n" + "1. Publish Dates" + "\n" + "2. Views" + "\n" + "3. Like/Dislike count");

                    int choiceMap = 0; 
                    if(scan.hasNextInt())
                        choiceMap = scan.nextInt();
                        
                    map.inOrder(choiceMap);
                    System.out.println("\n");

                    break;
                case 2: 
                    System.out.println("What do you want to values?: " + "\n" + "1. Publish Dates" + "\n" + "2. Views" + "\n" + "3. Like/Dislike count");

                    int choiceHash = 0;
                    if(scan.hasNextInt())
                        choiceHash = scan.nextInt();

                    hashMap.printHashMap(choiceHash);
                    System.out.println("\n");

                    break;

                case 3:
                    System.out.println("What do you want to values?: " + "\n" + "1. Publish Dates" + "\n" + "2. Views" + "\n" + "3. Like/Dislike count");

                    int choiceHashOpen = 0;
                    if(scan.hasNextInt())
                        choiceHashOpen = scan.nextInt();


                    for(int i = 0; i < hashOpenLinear.size; i ++)
                    {
                        if(hashOpenLinear.hashMapOpen[i] != null)
                        {
                            if(choiceHashOpen == 1)
                                System.out.println(hashOpenLinear.hashMapOpen[i].getPublishDate());
                            else if(choiceHashOpen == 2)
                                System.out.println(hashOpenLinear.hashMapOpen[i].getViews().toString());
                            else if(choiceHashOpen == 3)
                                System.out.println(hashOpenLinear.hashMapOpen[i].getLikeCount().toString());
                        }
                        
                    }
                    
                    break;
                case 4:
                    System.out.println("What do you want to values?: " + "\n" + "1. Publish Dates" + "\n" + "2. Views" + "\n" + "3. Like/Dislike count");

                    int choiceHashQuad = 0;
                    if(scan.hasNextInt())
                        choiceHashQuad = scan.nextInt();


                    for(int i = 0; i < hashOpenQuad.size; i ++)
                    {
                        if(hashOpenQuad.hashMapOpen[i] != null)
                        {
                            if(choiceHashQuad == 1)
                                System.out.println(hashOpenQuad.hashMapOpen[i].getPublishDate());
                            else if(choiceHashQuad == 2)
                                System.out.println(hashOpenQuad.hashMapOpen[i].getViews().toString());
                            else if(choiceHashQuad == 3)
                                System.out.println(hashOpenQuad.hashMapOpen[i].getLikeCount().toString());
                        }
                        
                    }
                    break;
                case 5:
                    System.out.println("Enter the first type of video you want to search: ");
                    videoOne = scan.next();
                    System.out.println("Enter the name of the file you want to create: ");
                    fileNameOne = scan.next();
                    System.out.println("Enter the second video type you want to search: ");
                    videoTwo = scan.next();
                    System.out.println("Enter the name of the file you want to create: ");
                    fileNameTwo = scan.next();
                    
                
                    //GatherData.createJson(videoOne, fileNameOne);
                    //GatherData.createJson(videoTwo, fileNameTwo);
                    break;
                
                case 6:
                    InputStream inputStream = new FileInputStream(fileNameOne);
                    InputStream inputStream2 = new FileInputStream(fileNameTwo);

                    InputStream statsReader = new FileInputStream("statsOutput1.json");
                    InputStream statsReader2 = new FileInputStream("statsOutput2.json");

                    JsonParser parser = JSON_FACTORY.createJsonParser(inputStream, StandardCharsets.UTF_8);
                    JsonParser parser2 = JSON_FACTORY.createJsonParser(inputStream2, StandardCharsets.UTF_8);

                    JsonParser statReader = JSON_FACTORY.createJsonParser(statsReader, StandardCharsets.UTF_8);
                    JsonParser statReader2 = JSON_FACTORY.createJsonParser(statsReader2, StandardCharsets.UTF_8);

                    long mapFinalTime = 0; 
                    long hashMapFinalTime = 0;
                    long hashMapOpenFinalTime = 0;
                    long hashMapOpenQuadFinalTime = 0;

                    for(int i = 0; i < 6289; i++)
                    {
                        Node tempNode1 = GatherData.populateNode(parser, statReader);
                        Node tempNode2 = GatherData.populateNode(parser2, statReader2);

                        long startTime = System.nanoTime();
                        map.insert(tempNode1.getChannelID(),tempNode1.getChannelID(), tempNode1.getVideoTitle(), tempNode1.getPublishDate(), tempNode1.getViews(), tempNode1.getVideoID(), tempNode1.getLikeCount(), tempNode1.getDislikeCount());
                        long endTime = System.nanoTime();
                        mapFinalTime+=(endTime - startTime);
                        
                        long hashMapStartTime = System.nanoTime();
                        hashMap.insert(tempNode2.getChannelID(),tempNode2.getChannelID(), tempNode2.getVideoTitle(), tempNode2.getPublishDate(), tempNode2.getViews(), tempNode2.getVideoID(), tempNode2.getLikeCount(), tempNode2.getDislikeCount());
                        long hashMapEndTime = System.nanoTime();
                        hashMapFinalTime+=(hashMapEndTime - hashMapStartTime);
                        
                        long hashMapOpenStartTime = System.nanoTime();
                        hashOpenLinear.insert(tempNode1.getChannelID(),tempNode1.getChannelID(), tempNode1.getVideoTitle(), tempNode1.getPublishDate(), tempNode1.getViews(), tempNode1.getVideoID(), tempNode1.getLikeCount(), tempNode1.getDislikeCount());
                        long hashMapOpenEndTime = System.nanoTime();
                        hashMapOpenFinalTime+=(hashMapOpenEndTime - hashMapOpenStartTime);

                        long hashMapOpenQuadStartTime = System.nanoTime();
                        hashOpenQuad.insert(tempNode2.getChannelID(),tempNode2.getChannelID(), tempNode2.getVideoTitle(), tempNode2.getPublishDate(), tempNode2.getViews(), tempNode2.getVideoID(), tempNode2.getLikeCount(), tempNode2.getDislikeCount());
                        long hashMapOpenQuadEndTime = System.nanoTime();
                        hashMapOpenQuadFinalTime+=(hashMapOpenQuadEndTime - hashMapOpenQuadStartTime);
                    }

                    String fancyPrint = ("//*****************************************\\\\" + "\n" +
                                        "/*              In Milliseconds" + "\n" +
                                        "/* Map build time: " + TimeUnit.MILLISECONDS.convert(mapFinalTime, TimeUnit.NANOSECONDS) + "      " + "\n" + 
                                        "/* Hash Map build time: " + TimeUnit.MILLISECONDS.convert(hashMapFinalTime, TimeUnit.NANOSECONDS) + "\n" + 
                                        "/* Hash Map Linear addressing build time: " + TimeUnit.MILLISECONDS.convert(hashMapOpenFinalTime, TimeUnit.NANOSECONDS)+ "\n" +
                                        "/* Hash Map Quadratic addressing build time: " + TimeUnit.MILLISECONDS.convert(hashMapOpenQuadFinalTime, TimeUnit.NANOSECONDS) + "\n" +
                                        "\\\\*****************************************//" + "\n");

                    System.out.println(fancyPrint);

                    break;

                case 7:
                    System.out.println("What do you want to compare?: " + "\n" +
                                       "1. Map size" + "\n" +
                                       "2. View Count" + "\n" +
                                       "3. Maximum views" + "\n" +
                                       "4. Like Count" + "\n" +
                                       "5. Maximum likes" + "\n" + 
                                       "6. Ratio of likes/views" + "\n");


                    int choiceCompare = 0;
                    if(scan.hasNextInt())
                        choiceCompare = scan.nextInt();

                    map.likeTotal();
                    map.viewTotal();
                    hashMap.viewAndLikeTotalHM();
                    hashOpenQuad.viewAndLikeTotalHMO();
                    hashOpenLinear.viewAndLikeTotalHMO();

                    String fancyCompare ="";

                    if(choiceCompare == 1)
                    {
                        fancyCompare = ("//*****************************************\\\\" + "\n" +
                                        "/*              Map Size in Nodes" + "\n" +
                                        "/* Map size: " + map.getSize().toString() + "      " + "\n" + 
                                        "/* Hash Map size: " + hashMap.getTotalSize().toString() + "\n" + 
                                        "/* Hash Map Linear size: " + hashOpenLinear.getTotalSize() + "\n" +
                                        "/* Hash Map Quadratic size: " + hashOpenQuad.getTotalSize() + "\n" +
                                        "\\\\*****************************************//" + "\n");

                    }
                    else if(choiceCompare == 2)
                    {
                        fancyCompare = ("//*****************************************\\\\" + "\n" +
                                        "/*           Total amount of views in map" + "\n" +
                                        "/* Map views: " + map.getViewTotal().toString() + "      " + "\n" + 
                                        "/* Hash Map views: " + hashMap.getViewTotal().toString() + "\n" + 
                                        "/* Hash Map views: " + hashOpenLinear.getViewTotal().toString() + "\n" +
                                        "/* Hash Map views: " + hashOpenQuad.getViewTotal().toString() + "\n" +
                                        "\\\\*****************************************//" + "\n");
                    }
                    else if(choiceCompare == 3)
                    {
                        fancyCompare = ("//*****************************************\\\\" + "\n" +
                                        "/*           Maximum viewed video in map" + "\n" +
                                        "/* Map views: " + map.getMaxView().getViews().toString() + "      " + "\n" + 
                                        "/* Hash Map views: " + hashMap.getMaxView().getViews().toString() + "\n" + 
                                        "/* Hash Map views: " + hashOpenLinear.getMaxView().getViews().toString() + "\n" +
                                        "/* Hash Map views: " + hashOpenQuad.getMaxView().getViews().toString() + "\n" +
                                        "\\\\*****************************************//" + "\n");
                    }
                    else if(choiceCompare == 4)
                    {
                        fancyCompare = ("//*****************************************\\\\" + "\n" +
                                        "/*          Total amount of Likes in map" + "\n" +
                                        "/* Map views: " + map.getLikeTotal().toString()+ "      " + "\n" + 
                                        "/* Hash Map views: " + hashMap.getLikeTotal().toString(input) + "\n" + 
                                        "/* Hash Map views: " + hashOpenLinear.getLikeTotal().toString(input) + "\n" +
                                        "/* Hash Map views: " + hashOpenQuad.getLikeTotal().toString(input) + "\n" +
                                        "\\\\*****************************************//" + "\n");
                    }
                    else if(choiceCompare == 5)
                    {
                        fancyCompare = ("//*****************************************\\\\" + "\n" +
                                        "/*         Maximum liked video in map" + "\n" +
                                        "/* Map views: " + map.getMaxLike().getLikeCount().toString()+ "      " + "\n" + 
                                        "/* Hash Map views: " + hashMap.getMaxLike().getLikeCount().toString() + "\n" + 
                                        "/* Hash Map views: " + hashOpenLinear.getMaxLike().getLikeCount().toString() + "\n" +
                                        "/* Hash Map views: " + hashOpenQuad.getMaxLike().getLikeCount().toString() + "\n" +
                                        "\\\\*****************************************//" + "\n");
                    }
                    else if(choiceCompare == 6)
                    {

                        BigDecimal tempMapLike = new BigDecimal(map.getLikeTotal());
                        BigDecimal tempMapViews = new BigDecimal(map.getViewTotal());


                        BigDecimal tempHashMapLike = new BigDecimal(hashMap.getLikeTotal());
                        BigDecimal tempHashMapViews = new BigDecimal(hashMap.getViewTotal());

                        BigDecimal thmll = new BigDecimal(hashOpenLinear.getLikeTotal());
                        BigDecimal thmlv = new BigDecimal(hashOpenLinear.getViewTotal());

                        BigDecimal thmol = new BigDecimal(hashOpenQuad.getLikeTotal());
                        BigDecimal thmov = new BigDecimal(hashOpenQuad.getViewTotal());
                       
                        
                        fancyCompare = ("//*****************************************\\\\" + "\n" +
                                        "/*          Total amount of Likes in map" + "\n" +
                                        "/* Map views: " + tempMapLike.divide(tempMapViews, 4, RoundingMode.HALF_UP).toString() + "      " + "\n" + 
                                        "/* Hash Map views: " + tempHashMapLike.divide(tempHashMapViews, 4, RoundingMode.HALF_UP).toString() + "\n" + 
                                        "/* Hash Map views: " + thmll.divide(thmlv, 4, RoundingMode.HALF_UP) + "\n" +
                                        "/* Hash Map views: " + thmol.divide(thmov, 4, RoundingMode.HALF_UP) + "\n" +
                                        "\\\\*****************************************//" + "\n");
                        
                    }
                    //Ratio of likes/views 
                    System.out.print(fancyCompare);
                    System.out.println("\n");
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


  
                                                                                                                                                                                                                            