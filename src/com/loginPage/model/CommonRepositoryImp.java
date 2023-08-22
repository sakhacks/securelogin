package com.loginPage.model;

import com.loginPage.bean.User;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import org.apache.log4j.Logger;
import org.bson.Document;
import java.util.Date;



public class CommonRepositoryImp implements ICommonRepository{

    private static final Logger logger = Logger.getLogger(CommonRepositoryImp.class.getName());

    @Override
    public String checkUser(String email, String password) {

        logger.info("Method checkUser is called.");

        MongoClient con = DbConnection.connection();
        MongoDatabase database = con.getDatabase("LoginDetails");
        MongoCollection<Document> collection= database.getCollection("Details");

        Document user1 = collection.find(Filters.eq("emailid", email)).first();

        //unblocking the user if he is blocked for more than 24 hours....
        if((user1 != null) && user1.getDate("lastFailedAttempt")!=null)
        {
            logger.info("Unblocking blocked User after 24 hours section executed.");
            Date currentDateTime = new Date();
            Date blockedDateTime = user1.getDate("lastFailedAttempt");
            long updatedDate = blockedDateTime.getTime() + (24 * 60 * 60 * 1000);
            Date blockedDateTimePlus24Hour= new Date(updatedDate);
            int stats = currentDateTime.compareTo(blockedDateTimePlus24Hour);
            System.out.println(blockedDateTimePlus24Hour +" = "+currentDateTime);

            if(stats >= 0)
            {
                collection.updateOne(Filters.eq("emailid", email), Updates.combine(
                        Updates.set("failedAttempts", 0),
                        Updates.set("lastFailedAttempt", null)
                ));
                logger.info("Setting failedAttempts:0 & lastFailedAttempts:null");
            }

        }

        //Now again querying user for the updated status or details if unblocked...
        Document user = collection.find(Filters.eq("emailid", email)).first();

        //4 cases : Blocked, Present, Invalid and Not Present...
        if((user != null) && user.getString("password").equals(password) && user.getInteger("failedAttempts") >=3)
        {
            logger.info("Return status for checkUser: Block (already Blocked)");
            con.close();
            return "Block";
        }
        else if ((user != null) && user.getString("password").equals(password) && user.getInteger("failedAttempts")<3) {

            logger.info("Return status for checkUser: Present.");
            collection.updateOne(Filters.eq("emailid", email), Updates.combine(
                    Updates.set("failedAttempts", 0),
                    Updates.set("lastFailedAttempt", null)
            ));
            con.close();
            logger.info("Setting failed attempts:0 & lastFailedAttempt:null.");
            return "Present";

        } else if((user != null) && !user.getString("password").equals(password)) {

            int failedAttempts = user.getInteger("failedAttempts", 0) + 1;
            Document updateDocument = new Document("$set", new Document("failedAttempts", failedAttempts));

            if (failedAttempts == 3) {
                logger.info("Failed attempts:3 Updating the blocking time in database.");
                updateDocument.append("$currentDate", new Document("lastFailedAttempt", true));
            }

            collection.updateOne(Filters.eq("emailid", email), updateDocument);

            con.close();

            if (failedAttempts >= 3) {
                logger.info("Return status for checkUser: Block (Blocking User after 3rd failed attempt)");
                return "Block";

            } else {
                logger.info("Return status for checkUser: Invalid & failedAttempts: "+failedAttempts);
                return "Invalid"+failedAttempts;

            }
        }
         else{
                con.close();
                logger.info("Return status for checkUser: NotPresent");
                return "NotPresent";

         }



    }

    @Override
    public boolean createUser(String userName, String email, String password) {

        logger.info("Method createUser is called.");
        String status = checkUser(email,password);

        if (status.equals("NotPresent")||status.contains("Invalid")) {

            MongoClient con = DbConnection.connection();
            MongoDatabase database = con.getDatabase("LoginDetails");
            MongoCollection<Document> collection= database.getCollection("Details");

            Document doc =new Document("name",userName);
            doc.append("emailid",email);
            doc.append("password",password);
            doc.append("failedAttempts", 0);
            doc.append("lastFailedAttempt", null);
            InsertOneResult s = collection.insertOne(doc);

            if(s.wasAcknowledged())
            {
                logger.info("User Data entered successfully for createUser.");
                con.close();
                return true;
            }

        }
        logger.warn("User already exists.");
        return false;

    }

    @Override
    public User getUserDetails(String email, String password) {

        logger.info("Method getUserDetails is called.");
        MongoClient con = DbConnection.connection();
        MongoDatabase database = con.getDatabase("LoginDetails");
        MongoCollection<Document> collection= database.getCollection("Details");

        Document userFromDB = collection.find(Filters.eq("emailid", email)).first();

        if(userFromDB!=null)
        {
            User user = new User();
            user.setUserName((String) userFromDB.get("name"));
            user.setEmailId((String) userFromDB.get("emailid"));
            logger.info("Details to display: Name:"+user.getUserName()+"Email:"+user.getEmailId());
            con.close();
            return user;

        }
        return null;

    }
}
