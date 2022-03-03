package models.contacts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "contactId",
        "firstName",
        "lastName",
        "jobTitle",
        "email",
        "phone",
        "address",
        "companies",
        "groups",
        "importSource",
        "appId",
        "status",
        "created",
        "updated",
        "modified"
})
@Getter
@Setter
public class ContactData {

    @JsonProperty("contactId")
    public String contactId;
    @JsonProperty("firstName")
    public String firstName;
    @JsonProperty("lastName")
    public String lastName;
    @JsonProperty("jobTitle")
    public String jobTitle;
    @JsonProperty("email")
    public List<Email> email = null;
    @JsonProperty("phone")
    public List<Phone> phone = null;
    @JsonProperty("address")
    public List<Address> address = null;
    @JsonProperty("companies")
    public List<String> companies = null;
    @JsonProperty("groups")
    public List<String> groups = null;
    @JsonProperty("importSource")
    public String importSource;
    @JsonProperty("appId")
    public String appId;
    @JsonProperty("status")
    public String status;
    @JsonProperty("created")
    public long created;
    @JsonProperty("updated")
    public long updated;
    @JsonProperty("modified")
    public long modified;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
}
