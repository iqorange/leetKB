package sol5;

import java.util.HashMap;
import java.util.Map;

public class DBPool {
    private Map<DBClient, Boolean> map;
    public DBPool(){
        map = new HashMap<>();
    }

    public DBClient getClient(){
        for (Map.Entry<DBClient, Boolean> entry: map.entrySet()){
            if (!entry.getValue()){
                map.put(entry.getKey(), true);
                return entry.getKey();
            }
        }
        DBClient dbClient = new DBClient();
        map.put(dbClient, true);
        return dbClient;
    }

    public void closeClient(DBClient dbClient){
        map.put(dbClient, false);
    }
}
