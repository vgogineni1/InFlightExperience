
package inflightexperience.aa.com.inflightexperience.datamodels;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "__metadata",
    "CUSTOMERID",
    "FOOD",
    "DRINK"
})
public class Result {

    @JsonProperty("__metadata")
    @Valid
    private Metadata metadata;
    @JsonProperty("CUSTOMERID")
    private String cUSTOMERID;
    @JsonProperty("FOOD")
    private String fOOD;
    @JsonProperty("DRINK")
    private String dRINK;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("__metadata")
    public Metadata getMetadata() {
        return metadata;
    }

    @JsonProperty("__metadata")
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Result withMetadata(Metadata metadata) {
        this.metadata = metadata;
        return this;
    }

    @JsonProperty("CUSTOMERID")
    public String getCUSTOMERID() {
        return cUSTOMERID;
    }

    @JsonProperty("CUSTOMERID")
    public void setCUSTOMERID(String cUSTOMERID) {
        this.cUSTOMERID = cUSTOMERID;
    }

    public Result withCUSTOMERID(String cUSTOMERID) {
        this.cUSTOMERID = cUSTOMERID;
        return this;
    }

    @JsonProperty("FOOD")
    public String getFOOD() {
        return fOOD;
    }

    @JsonProperty("FOOD")
    public void setFOOD(String fOOD) {
        this.fOOD = fOOD;
    }

    public Result withFOOD(String fOOD) {
        this.fOOD = fOOD;
        return this;
    }

    @JsonProperty("DRINK")
    public String getDRINK() {
        return dRINK;
    }

    @JsonProperty("DRINK")
    public void setDRINK(String dRINK) {
        this.dRINK = dRINK;
    }

    public Result withDRINK(String dRINK) {
        this.dRINK = dRINK;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Result withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(metadata).append(cUSTOMERID).append(fOOD).append(dRINK).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Result) == false) {
            return false;
        }
        Result rhs = ((Result) other);
        return new EqualsBuilder().append(metadata, rhs.metadata).append(cUSTOMERID, rhs.cUSTOMERID).append(fOOD, rhs.fOOD).append(dRINK, rhs.dRINK).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
