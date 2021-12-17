import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import ru.sfedu.dataProvider.DataProviderCSV;
import ru.sfedu.dataProvider.DataProviderMySQL;
import ru.sfedu.dataProvider.DataProviderXML;
import ru.sfedu.model.Status;

public class JDBCTest extends TestClass{
    private static final Logger log = LogManager.getLogger(DataProviderCSV.class.getName());
    DataProviderMySQL dp = new DataProviderMySQL();

    @Test
    public void profileTest(){
        dp.insertProfile(profile1);
        Assert.assertEquals(dp.editProfile(profile1.getUserId(), profile2), Status.SUCCESS);
        Assert.assertEquals(dp.editProfile(90, profile1), Status.FAULT);
        Assert.assertEquals(dp.deleteProfileById(12), Status.FAULT);
        Assert.assertEquals(dp.deleteProfileById(profile1.getUserId()), Status.SUCCESS);
    }
}
