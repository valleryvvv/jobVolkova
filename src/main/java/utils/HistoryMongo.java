package utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import ru.sfedu.Constants;
import ru.sfedu.dataProvider.DataProviderCSV;
import ru.sfedu.model.HistoryContent;
import ru.sfedu.model.Status;

import java.io.IOException;
import java.security.Timestamp;
import java.util.Date;

import static ru.sfedu.Constants.*;
import static utils.ConfigurationUtil.getConfigurationEntry;

public class HistoryMongo {

    private static final Logger log = LogManager.getLogger(HistoryMongo.class.getName());

    public static void recordToMongo(HistoryContent historyContent) {
        try {
            MongoClient client = new MongoClient(new MongoClientURI(getConfigurationEntry(MONGO_URI)));
            MongoDatabase database = client.getDatabase(getConfigurationEntry(MONGO_DATABASE));
            MongoCollection<Document> collection = database.getCollection(getConfigurationEntry(MONGO_COLLECTION));
            Document document = new Document();
            document.put("id", historyContent.getId());
            document.put("className", historyContent.getClassName());
            document.put("createdDate", historyContent.getCreatedDate());
            document.put("actor", historyContent.getActor());
            document.put("methodName", historyContent.getMethodName());
            document.put("object", historyContent.getObject());
            document.put("status", historyContent.getStatus().toString());
            collection.insertOne(document);
        }catch (Exception e){
            log.error(e);
        }
    }

}
