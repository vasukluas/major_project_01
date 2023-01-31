package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;

import static org.example.DatabaseConnection.getConnection;

public class Crawler {
    public HashSet<String>urlHash;
    public int max_depth=2;

    public static Connection connection=null;
    public Crawler(){
        urlHash=new HashSet<>();
        connection =DatabaseConnection.getConnection();
    }
    public void getPageTextAndLinks(String url,int depth){
     if(!urlHash.contains(url)){
         if(urlHash.add(url)){
             System.out.println(url);
         }
         try{
          Document document= Jsoup.connect(url).timeout(5000).get();
          String text=document.text().length()>1000?document.text().substring(0,999):document.text();
          String title=document.title();

             PreparedStatement preparedStatement=connection.prepareStatement("Insert into pages values(?,?,?)");

             preparedStatement.setString(1,title);
             preparedStatement.setString(2,url);
             preparedStatement.setString(3,text);
             preparedStatement.executeUpdate();

           depth++;
          if(depth==2){
              return;
          }
             Elements availableLinksOnPage=document.select("a[href]");
          for(Element cuurentLink:availableLinksOnPage){
              getPageTextAndLinks(cuurentLink.attr("abs:href"),depth);
          }
         }
         catch(IOException | SQLException ioException){
             ioException.printStackTrace();
         }
     }
    }
    public static void main(String[] args) {
       Crawler crawler= new Crawler();
        crawler.getPageTextAndLinks("https://www.javatpoint.com",0);
    }
}