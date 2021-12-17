import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import ru.sfedu.dataProvider.DataProviderCSV;
import ru.sfedu.model.*;

import java.util.List;

public class ProfileTest {

    private static final Logger log = LogManager.getLogger(DataProviderCSV.class.getName());
    DataProviderCSV dpc = new DataProviderCSV();

    @Test
    public void testProfileSelect(){
//        List<Vacancy> profiles = dpc.selectVacancy();
//        for (Vacancy e : profiles) {
//            log.info(e.getVacancyId());
//        }
//        List<Feedback> feedbacks = dpc.selectFeedback();
//        for (Feedback e : feedbacks){
//            log.info(e.getText());
//        }
    }

    @Test
    public void testProfileGetById(){
//        Profile profile = dpc.profileGetById(1);
        //Assert.assertEquals("Moscow", profile.getCity());
        Vacancy feedback = dpc.vacancyGetById(1);
        log.info(feedback.getAreaWork());
    }

    @Test
    public void testProfileInsert() {
        Profile profile = new Profile(2,"Lala","La","12.12.2000","c",99,false);
        dpc.insertProfile(profile);
        testProfileSelect();
      //  dpc.deleteProfileById(2);

    }

    @Test
    public void testProfileDelete() {
        Profile profile = new Profile(2,"Lala","La","12.12.2000","c",99,false);
        dpc.insertProfile(profile);
        testProfileSelect();
        dpc.deleteProfileById(2);
        testProfileSelect();
    }

    @Test
    public void testProfileUpdate() {
        Profile profile = new Profile(2,"Lala","La","12.12.2000","c",99,false);
        dpc.insertProfile(profile);
        testProfileSelect();
        Profile profile2 = new Profile(2,"Lala","La","12.12.2000","city",99,false);
        dpc.editProfile(2, profile2);
        testProfileSelect();
        dpc.deleteProfileById(2);
    }
}
