package es.um.nosql.s13e.test.morphia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;
import org.mongodb.morphia.VerboseJSR303ConstraintViolationException;
import org.mongodb.morphia.query.Query;

import es.um.nosql.s13e.db.adapters.mongodb.MongoDbAdapter;
import es.um.nosql.s13e.db.adapters.mongodb.MongoDbClient;
import es.um.nosql.s13e.everypolitician.Areas;
import es.um.nosql.s13e.everypolitician.Contact_detail;
import es.um.nosql.s13e.everypolitician.Events;
import es.um.nosql.s13e.everypolitician.Identifier;
import es.um.nosql.s13e.everypolitician.Image;
import es.um.nosql.s13e.everypolitician.Link;
import es.um.nosql.s13e.everypolitician.Memberships;
import es.um.nosql.s13e.everypolitician.Organizations;
import es.um.nosql.s13e.everypolitician.Other_name;
import es.um.nosql.s13e.everypolitician.Persons;
import es.um.nosql.s13e.everypolitician.Source;

public class EveryPoliticianTest
{
  private final static int N_AREAS = 29;
  private final static int N_ORGANIZATIONS = 11;
  private final static int N_EVENTS = 62;
  private final static int N_PERSONS = 1625;
  private final static int N_MEMBERSHIPS = 5149;

  private Morphia morphia;
  private MongoDbClient client;
  private Datastore datastore;
  private String dbName;
  private Validator validator;

  @Before
  public void setUp() throws Exception
  {
    dbName = "everypolitician";
    morphia = new Morphia();
    morphia = morphia.mapPackage("es.um.nosql.s13e." + dbName);
    new ValidationExtension(morphia);
    client = MongoDbAdapter.getMongoDbClient("localhost");
    datastore = morphia.createDatastore(client, dbName);
    datastore.ensureIndexes();
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @After
  public void tearDown() throws Exception
  {
    client.close();
  }

  @Test
  public void testCheckConsistency()
  {
    checkEveryPoliticianDb(datastore);
  }

  @Test
  public void testDuplicateDbAndCheck()
  {
    String newDbName = dbName + "_test_1";
    Datastore newDatastore = morphia.createDatastore(client,  newDbName);

    List<Areas> lAreas = new ArrayList<Areas>();
    lAreas.addAll(datastore.createQuery(Areas.class).asList());
    newDatastore.save(lAreas);
    lAreas.clear();

    List<Organizations> lOrganizations = new ArrayList<Organizations>();
    lOrganizations.addAll(datastore.createQuery(Organizations.class).asList());
    newDatastore.save(lOrganizations);
    lOrganizations.clear();

    List<Events> lEvents = new ArrayList<Events>();
    lEvents.addAll(datastore.createQuery(Events.class).asList());
    newDatastore.save(lEvents);
    lEvents.clear();

    List<Persons> lPersons = new ArrayList<Persons>();
    lPersons.addAll(datastore.createQuery(Persons.class).asList());
    newDatastore.save(lPersons);
    lPersons.clear();

    List<Memberships> lMemberships = new ArrayList<Memberships>();
    lMemberships.addAll(datastore.createQuery(Memberships.class).asList());
    newDatastore.save(lMemberships);
    lMemberships.clear();

    checkEveryPoliticianDb(newDatastore);
    newDatastore.getDB().dropDatabase();
  }

  @Test
  public void testAddErrorAndCheck()
  {
    String newDbName = dbName + "_test_2";
    Datastore newDatastore = morphia.createDatastore(client,  newDbName);

    Areas area1 = new Areas(); area1.set_id(new ObjectId().toString()); area1.setName("area_name"); // Type is missing, and it is defined as required on the schema.
    Areas area2 = new Areas(); area2.set_id(new ObjectId().toString()); area2.setType("area_type"); // Name is missing, and it is defined as required on the schema.

    assertEquals(1, validator.validate(area1).size());
    assertEquals(1, validator.validate(area2).size());

    assertThrows(VerboseJSR303ConstraintViolationException.class, () -> {newDatastore.save(area1);});
    assertThrows(VerboseJSR303ConstraintViolationException.class, () -> {newDatastore.save(area2);});

    Organizations org1 = new Organizations(); org1.set_id(new ObjectId().toString()); org1.setClassification("org_classification"); org1.setName("org_name");
    Organizations org2 = new Organizations(); org2.set_id(new ObjectId().toString()); org2.setClassification("org_classification");

    assertEquals(0, validator.validate(org1).size());
    assertEquals(1, validator.validate(org2).size());
    assertThrows(VerboseJSR303ConstraintViolationException.class, () -> {newDatastore.save(org2);});

    newDatastore.getDB().dropDatabase();
  }

  private void checkEveryPoliticianDb(Datastore theDatastore)
  {
    Query<Areas> qAreas = theDatastore.createQuery(Areas.class);
    assertEquals(N_AREAS, qAreas.count());
    testCollection(qAreas.asList(), Areas.class);

    // We actually detected here a bug. When an aggregate/reference cardinality is 0..1, it may actually be an array.
    Query<Organizations> qOrganizations = theDatastore.createQuery(Organizations.class);
    assertEquals(N_ORGANIZATIONS, qOrganizations.count());
    testCollection(qOrganizations.asList(), Organizations.class);

    for (Organizations organization : qOrganizations)
    {
      testCollection(organization.getOther_names(), Other_name.class);
      testCollection(organization.getIdentifiers(), Identifier.class);
      testCollection(organization.getLinks(), Link.class);
    }

    qOrganizations = null;

    Query<Events> qEvents = theDatastore.createQuery(Events.class);
    assertEquals(N_EVENTS, qEvents.count());
    testCollection(qEvents.asList(), Events.class);

    for (Events event : qEvents)
    {
      testCollection(event.getIdentifiers(), Identifier.class);

      // Dont have to check if the Organization is valid, since we just validated it on the previous step...
      if (event.getOrganization_id() != null)
        assertEquals(1, theDatastore.createQuery(Organizations.class).filter("_id =", event.getOrganization_id().get_id()).count());
    }

    qEvents = null;

    Query<Persons> qPersons = theDatastore.createQuery(Persons.class);
    assertEquals(N_PERSONS, qPersons.count());
    testCollection(qPersons.asList(), Persons.class);

    for (Persons person : qPersons)
    {
      testCollection(person.getOther_names(), Other_name.class);
      testCollection(person.getIdentifiers(), Identifier.class);
      testCollection(person.getLinks(), Link.class);
      testCollection(person.getContact_details(), Contact_detail.class);
      testCollection(person.getImages(), Image.class);
    }

    qPersons = null;

    Query<Memberships> qMemberships = theDatastore.createQuery(Memberships.class);
    assertEquals(N_MEMBERSHIPS, qMemberships.count());
    testCollection(qMemberships.asList(), Memberships.class);

    for (Memberships membership : qMemberships)
    {
      testCollection(membership.getSources(), Source.class);

      // Dont have to check if the references are valid themselves, since we just validated them on the previous method...
      if (membership.getArea_id() != null)
        assertEquals(1, theDatastore.createQuery(Areas.class).filter("_id =", membership.getArea_id().get_id()).count());

      // Dont panic it this runs for several seconds. It does about 5000 queries...
      if (membership.getPerson_id() != null)
        assertEquals(1, theDatastore.createQuery(Persons.class).filter("_id =", membership.getPerson_id().get_id()).count());

      if (membership.getOrganization_id() != null)
        assertEquals(1, theDatastore.createQuery(Organizations.class).filter("_id =", membership.getOrganization_id().get_id()).count());
    }

    qMemberships = null;
  }

  private <T> void testCollection(List<T> collection, Class<T> className)
  {
    if (collection == null || collection.size() == 0)
      return;

    for (T t : collection)
    {
      Set<ConstraintViolation<T>> violations = validator.validate(t);

      for (ConstraintViolation<T> cVio : violations)
        System.out.println(cVio.getMessage());

      assertEquals(0, violations.size());
    }
  }
}