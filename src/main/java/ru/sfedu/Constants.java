package ru.sfedu;

public class Constants {
    public static final String PACKAGE_PATH = "ru.sfedu";
    public static final String NAME_TABLE_PROFILE = "Profile";
    public static final String PROFILE_USER_ID = "userId";
    public static final String PROFILE_NAME = "name";
    public static final String PROFILE_LAST_NAME = "lastName";
    public static final String PROFILE_BIRTHDAY = "birthday";
    public static final String PROFILE_CITY = "city";
    public static final String PROFILE_RATING = "rating";
    public static final String PROFILE_EMPLOYER = "employer";

    public static final String CSV_PATH_PROFILE = "dataBase/csv/Profile.csv";
    public static final String CSV_PATH_FEEDBACK = "dataBase/csv/Feedback.csv";
    public static final String CSV_PATH_PROFILE_EMPLOYEE = "dataBase/csv/ProfileEmployee.csv";
    public static final String CSV_PATH_PROFILE_EMPLOYER = "dataBase/csv/ProfileEmployer.csv";
    public static final String CSV_PATH_VACANCY = "dataBase/csv/Vacancy.csv";

    public static final String XML_PATH_PROFILE = "dataBase/xml/Profile.xml";
    public static final String XML_PATH_FEEDBACK = "dataBase/xml/Feedback.xml";
    public static final String XML_PATH_PROFILE_EMPLOYEE = "dataBase/xml/ProfileEmployee.xml";
    public static final String XML_PATH_PROFILE_EMPLOYER = "dataBase/xml/ProfileEmployer.xml";
    public static final String XML_PATH_VACANCY = "dataBase/xml/Vacancy.xml";
    
    public static final String PATH_UNDEFINED = "";

    public static final String FEEDBACK_FEEDBACK_ID = "feedbackId";
    public static final String FEEDBACK_USER_ID_TO = "userIdTo";
    public static final String FEEDBACK_USER_ID_FROM = "userIdFrom";
    public static final String FEEDBACK_TEXT = "text";
    public static final String FEEDBACK_ESTIMATION = "estimation";
    public static final String FEEDBACK_DATE = "date";

    public static final String MONGO_URI = "mongoURI";
    public static final String MONGO_DATABASE = "mongoDatabase";
    public static final String MONGO_COLLECTION = "mongoCollection";

    public static final String MYSQL_URL = "mysqlURL";
    public static final String MYSQL_USERNAME = "mysqlUsername";
    public static final String MYSQL_PASSWORD = "mysqlPassword";

    public static final String CREATE_DATABASE = "CREATE DATABASE JOB";

    public static final String CREATE_TABLE_PROFILE = "CREATE TABLE Profile(" +
            "userId int," +
            "name varchar(255)," +
            "lastName varchar(255)," +
            "birthday varchar(255)," +
            "city varchar(255)," +
            "rating int," +
            "employer boolean)";

    public static final String CREATE_TABLE_PROFILE_EMPLOYER = "CREATE TABLE ProfileEmployer(" +
            "userId int," +
            "name varchar(255)," +
            "lastName varchar(255)," +
            "birthday varchar(255)," +
            "city varchar(255)," +
            "rating int," +
            "employer boolean," +
            "nameCompany varchar(255))";

    public static final String CREATE_TABLE_PROFILE_EMPLOYEE = "CREATE TABLE ProfileEmployee(" +
            "userId int," +
            "name varchar(255)," +
            "lastName varchar(255)," +
            "birthday varchar(255)," +
            "city varchar(255)," +
            "rating int," +
            "employer boolean," +
            "workExperience varchar(255)," +
            "education varchar(255)," +
            "resume varchar(255)," +
            "areaWork varchar(255)," +
            "post varchar(255)," +
            "workRemotely boolean)";

    public static final String CREATE_TABLE_FEEDBACK = "CREATE TABLE Feedback(" +
            "feedbackId int," +
            "userIdTo int," +
            "userIdFrom int," +
            "text varchar(255)," +
            "estimation int," +
            "date varchar(255))";

    public static final String CREATE_TABLE_VACANCY = "CREATE TABLE Vacancy(" +
            "vacancyId int," +
            "idProfileEmployer int," +
            "areaWork varchar(255)," +
            "titleWork varchar(255)," +
            "description varchar(255)," +
            "city varchar(255)," +
            "remotely boolean," +
            "deadline varchar(255)," +
            "price float" +
            ")";

    public static final String SELECT_PROFILE_QUERY = "select * from profile;";
    public static final String SELECT_FEEDBACK_QUERY = "select * from feedback;";
    public static final String SELECT_PROFILE_EMPLOYER_QUERY = "select * from profileEmployer;";
    public static final String SELECT_PROFILE_EMPLOYEE_QUERY = "select * from profileEmployee;";
    public static final String SELECT_VACANCY_QUERY = "select * from vacancy;";


    public static final String DEFAULT_ACTOR = "system";
}
