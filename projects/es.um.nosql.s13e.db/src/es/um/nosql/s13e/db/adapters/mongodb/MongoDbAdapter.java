package es.um.nosql.s13e.db.adapters.mongodb;

public class MongoDbAdapter
{
	private static int MONGODB_DEFAULT_PORT = 27017;

	public static MongoDbClient getMongoDbClient(String ip)
	{
		return getMongoDbClient(ip, MONGODB_DEFAULT_PORT);
	}

	public static MongoDbClient getMongoDbClient(String ip, int port)
	{
		return new MongoDbClient(ip, port);
	}
}
