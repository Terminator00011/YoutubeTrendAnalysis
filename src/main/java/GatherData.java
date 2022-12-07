/**
 * Sample Java code for youtube.search.list
 * See instructions for running these code samples locally:
 * https://developers.google.com/explorer-help/code-samples#java
 */

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.client.json.JsonParser;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class GatherData {
    private static final String CLIENT_SECRETS= "client_secret_55019393466-iogee5ge0ibruuq9l279dipjn2scu3q7.apps.googleusercontent.com.json";
    private static final Collection<String> SCOPES =
        Arrays.asList("https://www.googleapis.com/auth/youtube.force-ssl");

    private static final String APPLICATION_NAME = "API code samples";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    //private static final JsonGenerator JSON_GENERATOR = JsonGenerator

    /**
     * Create an authorized Credential object.
     *
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize(final NetHttpTransport httpTransport) throws IOException {
        // Load client secrets.
        InputStream in = GatherData.class.getResourceAsStream(CLIENT_SECRETS);
        GoogleClientSecrets clientSecrets =
          GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
            new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
            .build();
        Credential credential =
            new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
        return credential;
    }

    /**
     * Build and return an authorized API client service.
     *
     * @return an authorized API client service
     * @throws GeneralSecurityException, IOException
     */
    public static YouTube getService() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Credential credential = authorize(httpTransport);
        return new YouTube.Builder(httpTransport, JSON_FACTORY, credential)
            .setApplicationName(APPLICATION_NAME)
            .build();
    }
    
    public static Node populateNode(JsonParser parser, JsonParser statReader) throws GeneralSecurityException, IOException, GoogleJsonResponseException
    {
        BigInteger viewCount = null;
        BigInteger likeCount = null;
        BigInteger dislikeCount = null;


        while(parser.getText() != "videoId")
            parser.nextToken();

        parser.nextToken();
        String tempVideoID = parser.getText();
         
        while(parser.getText() != "channelId")
            parser.nextToken();
        parser.nextToken();
        String tempChannelId = parser.getText();

        while(parser.getText() != "channelTitle")
            parser.nextToken();
        parser.nextToken();
        String tempChannelTitle = parser.getText();
        
        while(parser.getText() != "publishedAt")
            parser.nextToken();
        parser.nextToken();
        String tempPublishDate = parser.getText();

        while(parser.getText() != "title")
            parser.nextToken();
        parser.nextToken();
        String tempTitle = parser.getText();

        
        while(statReader.getText() != "statistics")
        {
            statReader.nextToken();
        }
        statReader.nextToken();
        statReader.nextToken();

        statReader.nextToken();

        statReader.nextToken();

        statReader.nextToken();

        statReader.nextToken();

        if(statReader.getText() == "likeCount")
        {
            statReader.nextToken();
            likeCount = new BigInteger(statReader.getText());
        }
        statReader.nextToken();
        if(statReader.getText() == "viewCount")
        {
            statReader.nextToken();
            viewCount = new BigInteger(statReader.getText());
        }
    

        return new Node(tempChannelId, tempChannelTitle, tempTitle, tempPublishDate, viewCount, tempVideoID, likeCount, dislikeCount);
    }
    
    /**
     * Call function to create API service object. Define and
     * execute API request. Print API response.
     *
     * @throws GeneralSecurityException, IOException, GoogleJsonResponseException
     */
    public static void createJson(String videoOne, String fileName)
        throws GeneralSecurityException, IOException, GoogleJsonResponseException {

        YouTube youtubeService = getService();
        // Define and execute the API request
        YouTube.Search.List request = youtubeService.search()
            .list("snippet");

        SearchListResponse response = request.setMaxResults(500L)
            .setQ(videoOne)
            .execute();

        List <SearchResult> list =  response.getItems();
        int count = 0; 
        while(response.getNextPageToken() != null)
        {
            String tempToken = response.getNextPageToken(); 
            if(list.size() >= 6250)
                break;
            else if(count >= 10)
            {
                response.clear();
                youtubeService = getService();
                count = 0;
            }
        
            request = youtubeService.search().list("snippet").setPageToken(tempToken);
            response = request.setMaxResults(500L).setQ(videoOne).execute();

            list.addAll(response.getItems());
            count++;
        }

        OutputStream out = new FileOutputStream(fileName + ".json");
        JsonGenerator generator = JSON_FACTORY.createJsonGenerator(out, StandardCharsets.UTF_8);

        generator.enablePrettyPrint();
        generator.writeStartObject();
        
        generator.writeFieldName("data");
        generator.serialize(list);

        generator.writeEndObject();

        generator.flush();
    }
}