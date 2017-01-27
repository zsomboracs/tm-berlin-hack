
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
    "contextLink",
    "height",
    "width",
    "byteSize",
    "thumbnailLink",
    "thumbnailHeight",
    "thumbnailWidth"
})
public class Image {

    @JsonProperty("contextLink")
    private String contextLink;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("width")
    private Integer width;
    @JsonProperty("byteSize")
    private Integer byteSize;
    @JsonProperty("thumbnailLink")
    private String thumbnailLink;
    @JsonProperty("thumbnailHeight")
    private Integer thumbnailHeight;
    @JsonProperty("thumbnailWidth")
    private Integer thumbnailWidth;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("contextLink")
    public String getContextLink() {
        return contextLink;
    }

    @JsonProperty("contextLink")
    public void setContextLink(String contextLink) {
        this.contextLink = contextLink;
    }

    @JsonProperty("height")
    public Integer getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(Integer height) {
        this.height = height;
    }

    @JsonProperty("width")
    public Integer getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(Integer width) {
        this.width = width;
    }

    @JsonProperty("byteSize")
    public Integer getByteSize() {
        return byteSize;
    }

    @JsonProperty("byteSize")
    public void setByteSize(Integer byteSize) {
        this.byteSize = byteSize;
    }

    @JsonProperty("thumbnailLink")
    public String getThumbnailLink() {
        return thumbnailLink;
    }

    @JsonProperty("thumbnailLink")
    public void setThumbnailLink(String thumbnailLink) {
        this.thumbnailLink = thumbnailLink;
    }

    @JsonProperty("thumbnailHeight")
    public Integer getThumbnailHeight() {
        return thumbnailHeight;
    }

    @JsonProperty("thumbnailHeight")
    public void setThumbnailHeight(Integer thumbnailHeight) {
        this.thumbnailHeight = thumbnailHeight;
    }

    @JsonProperty("thumbnailWidth")
    public Integer getThumbnailWidth() {
        return thumbnailWidth;
    }

    @JsonProperty("thumbnailWidth")
    public void setThumbnailWidth(Integer thumbnailWidth) {
        this.thumbnailWidth = thumbnailWidth;
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
