package com.hotels.client;

import java.util.Optional;

import javax.inject.Inject;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Repository;

/**
 * Created by Peter_Kedvessy on 1/25/2017.
 */
@Repository
public class ResolveDestinationClient {

    @Inject
    private CloseableHttpClient httpClient;

    public Optional<String> getResolveDestinationBody(String destinationToResolve) {
        CloseableHttpResponse httpResponse = null;
        HttpGet getRequest = new HttpGet("https://www.hotels.com/homepage/resolve_destination.html?pos=HCOM_US&text=" + destinationToResolve);
        String responseBody = null;
        try {
            httpResponse = httpClient.execute(getRequest);
            String encoding = Optional.ofNullable(httpResponse.getEntity().getContentEncoding()).map(Header::getValue).orElse("UTF-8");
            responseBody = EntityUtils.toString(httpResponse.getEntity(), encoding);
        } catch (Exception ex) {
            //TODO log
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (Exception ex) {
                    //TODO log
                }
            }
        }
        return Optional.ofNullable(responseBody);
    }
}
