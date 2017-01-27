package com.hotels.web;

import com.gettyimages.ApiClient;
import com.gettyimages.SdkException;
import com.gettyimages.search.ImagesSearch;

public class CreativeSearch {
    private static String apiKey = "k4jpacxe6scf7xwc57rv65yq";
    private static String apiSecret = "QZPjCCtfTvPHfQgAHn2mStD6AqKPbmK5V6535xv7RUWZG";

    public static void main(String[] args) {
        ApiClient client = ApiClient.GetApiClientWithClientCredentials(apiKey, apiSecret);
        String searchTerm = "New York";

        try {
            ImagesSearch search = client.search()
                    .images()
                    .creative()
                    .withPhrase(searchTerm);

            String result = search.executeAsync();
            System.out.print(result);

        } catch (SdkException e) {
            System.out.println("Exception occurred while searching for creative images: " + e.getLocalizedMessage());
            System.exit(-1);
        }
    }
}
