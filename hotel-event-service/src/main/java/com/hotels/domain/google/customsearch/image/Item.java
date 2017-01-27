
package com.hotels.domain.google.customsearch.image;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "kind",
    "title",
    "htmlTitle",
    "link",
    "displayLink",
    "snippet",
    "htmlSnippet",
    "mime",
    "image"
})
public class Item {

    @JsonProperty("kind")
    private String kind;
    @JsonProperty("title")
    private String title;
    @JsonProperty("htmlTitle")
    private String htmlTitle;
    @JsonProperty("link")
    private String link;
    @JsonProperty("displayLink")
    private String displayLink;
    @JsonProperty("snippet")
    private String snippet;
    @JsonProperty("htmlSnippet")
    private String htmlSnippet;
    @JsonProperty("mime")
    private String mime;
    @JsonProperty("image")
    private Image image;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("kind")
    public String getKind() {
        return kind;
    }

    @JsonProperty("kind")
    public void setKind(String kind) {
        this.kind = kind;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("htmlTitle")
    public String getHtmlTitle() {
        return htmlTitle;
    }

    @JsonProperty("htmlTitle")
    public void setHtmlTitle(String htmlTitle) {
        this.htmlTitle = htmlTitle;
    }

    @JsonProperty("link")
    public String getLink() {
        return link;
    }

    @JsonProperty("link")
    public void setLink(String link) {
        this.link = link;
    }

    @JsonProperty("displayLink")
    public String getDisplayLink() {
        return displayLink;
    }

    @JsonProperty("displayLink")
    public void setDisplayLink(String displayLink) {
        this.displayLink = displayLink;
    }

    @JsonProperty("snippet")
    public String getSnippet() {
        return snippet;
    }

    @JsonProperty("snippet")
    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    @JsonProperty("htmlSnippet")
    public String getHtmlSnippet() {
        return htmlSnippet;
    }

    @JsonProperty("htmlSnippet")
    public void setHtmlSnippet(String htmlSnippet) {
        this.htmlSnippet = htmlSnippet;
    }

    @JsonProperty("mime")
    public String getMime() {
        return mime;
    }

    @JsonProperty("mime")
    public void setMime(String mime) {
        this.mime = mime;
    }

    @JsonProperty("image")
    public Image getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(Image image) {
        this.image = image;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
