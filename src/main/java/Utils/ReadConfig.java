package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.util.Properties;

public class ReadConfig {
    public static Properties pro;
    public ReadConfig(){
        File src = new File("./configurations/Config.properties");
        try {
            FileInputStream fis = new FileInputStream(src);
            pro = new Properties();
            pro.load(fis);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  String getBaseUrl(){
        String url = pro.getProperty("baseUrl");
        return url;
    }
    public  String getExpectedResourceTitle(){
        String expectedResourceTitle = pro.getProperty("ResourceTitle");
        return expectedResourceTitle;
    }
    public String getExpectedWhitePapersTitle(){
        String expectedWhitePapersTitle = pro.getProperty("WhitePapersTitle");
        return expectedWhitePapersTitle;
    }
    public String getFirstName(){
        String firstName = pro.getProperty("firstName");
        return firstName;
    }
    public String getLastName(){
        String lastName = pro.getProperty("lastName");
        return lastName;
    }
    public String getEmail(){
        String email = pro.getProperty("email");
        return email;
    }
    public String getCompany(){
        String company = pro.getProperty("company");
        return company;
    }
    public String getIndustry(){
        String industry = pro.getProperty("industry");
        return industry;
    }


}
