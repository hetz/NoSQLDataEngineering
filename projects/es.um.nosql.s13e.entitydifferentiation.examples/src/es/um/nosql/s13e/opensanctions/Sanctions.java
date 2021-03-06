package es.um.nosql.s13e.opensanctions;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import javax.validation.constraints.NotNull;
import java.util.List;

import es.um.nosql.s13e.opensanctions.Nationality;
import es.um.nosql.s13e.opensanctions.Birth_place;
import es.um.nosql.s13e.opensanctions.Identifier;
import es.um.nosql.s13e.opensanctions.Birth_date;
import es.um.nosql.s13e.opensanctions.Alias;
import es.um.nosql.s13e.opensanctions.Address;

@Entity(value = "sanctions", noClassnameStored = true)
public class Sanctions
{
  @Id
  @NotNull(message = "_id can't be null")
  private String _id;
  public String get_id() {return this._id;}
  public void set_id(String _id) {this._id = _id;}
  
  @Embedded
  private List<Address> addresses;
  public List<Address> getAddresses() {return this.addresses;}
  public void setAddresses(List<Address> addresses) {this.addresses = addresses;}
  
  @Embedded
  private List<Alias> aliases;
  public List<Alias> getAliases() {return this.aliases;}
  public void setAliases(List<Alias> aliases) {this.aliases = aliases;}
  
  @Embedded
  private List<Birth_date> birth_dates;
  public List<Birth_date> getBirth_dates() {return this.birth_dates;}
  public void setBirth_dates(List<Birth_date> birth_dates) {this.birth_dates = birth_dates;}
  
  @Embedded
  private List<Birth_place> birth_places;
  public List<Birth_place> getBirth_places() {return this.birth_places;}
  public void setBirth_places(List<Birth_place> birth_places) {this.birth_places = birth_places;}
  
  @Property
  private String father_name;
  public String getFather_name() {return this.father_name;}
  public void setFather_name(String father_name) {this.father_name = father_name;}
  
  @Property
  private String first_name;
  public String getFirst_name() {return this.first_name;}
  public void setFirst_name(String first_name) {this.first_name = first_name;}
  
  @Property
  private String function;
  public String getFunction() {return this.function;}
  public void setFunction(String function) {this.function = function;}
  
  @Property
  private String gender;
  public String getGender() {return this.gender;}
  public void setGender(String gender) {this.gender = gender;}
  
  @Embedded
  private List<Identifier> identifiers;
  public List<Identifier> getIdentifiers() {return this.identifiers;}
  public void setIdentifiers(List<Identifier> identifiers) {this.identifiers = identifiers;}
  
  @Property
  private String last_name;
  public String getLast_name() {return this.last_name;}
  public void setLast_name(String last_name) {this.last_name = last_name;}
  
  @Property
  private String listed_at;
  public String getListed_at() {return this.listed_at;}
  public void setListed_at(String listed_at) {this.listed_at = listed_at;}
  
  @Property
  private String name;
  public String getName() {return this.name;}
  public void setName(String name) {this.name = name;}
  
  @Embedded
  private List<Nationality> nationalities;
  public List<Nationality> getNationalities() {return this.nationalities;}
  public void setNationalities(List<Nationality> nationalities) {this.nationalities = nationalities;}
  
  @Property
  private String program;
  public String getProgram() {return this.program;}
  public void setProgram(String program) {this.program = program;}
  
  @Property
  private String second_name;
  public String getSecond_name() {return this.second_name;}
  public void setSecond_name(String second_name) {this.second_name = second_name;}
  
  @Property
  @NotNull(message = "source can't be null")
  private String source;
  public String getSource() {return this.source;}
  public void setSource(String source) {this.source = source;}
  
  @Property
  private String summary;
  public String getSummary() {return this.summary;}
  public void setSummary(String summary) {this.summary = summary;}
  
  @Property
  private String third_name;
  public String getThird_name() {return this.third_name;}
  public void setThird_name(String third_name) {this.third_name = third_name;}
  
  @Property
  @NotNull(message = "timestamp can't be null")
  private String timestamp;
  public String getTimestamp() {return this.timestamp;}
  public void setTimestamp(String timestamp) {this.timestamp = timestamp;}
  
  @Property
  private String title;
  public String getTitle() {return this.title;}
  public void setTitle(String title) {this.title = title;}
  
  @Property
  private String type;
  public String getType() {return this.type;}
  public void setType(String type) {this.type = type;}
  
  @Property
  private String updated_at;
  public String getUpdated_at() {return this.updated_at;}
  public void setUpdated_at(String updated_at) {this.updated_at = updated_at;}
  
  @Property
  private String url;
  public String getUrl() {return this.url;}
  public void setUrl(String url) {this.url = url;}
}
