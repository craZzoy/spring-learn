package com.geekbang.mangodb.sample;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author : 郑玮泽
 * @Date : 16:00 2021/3/29
 */
public class MangoDBJDBC {

    public static void main(String[] args) {
        //连接数据库
        final MongoClient client = new MongoClient("localhost", 27017);
        final MongoDatabase testDb = client.getDatabase("test");

        //创建集合
        //testDb.createCollection("mycol");

        //插入文档
        final MongoCollection<Document> collection = testDb.getCollection("mycol");
        Document document = new Document("title", "MongoDB")
                .append("description", "database")
                .append("like", 100)
                .append("by", "Fly");
        List<Document> documents = new ArrayList<>();
        documents.add(document);
        //collection.insertMany(documents);


        //检索所有文档
        printDocuments(collection);


        //更新文档
        collection.updateMany(Filters.eq("like", 100), new Document("$set", new Document("like", 200)));
        printDocuments(collection);

        //删除文档
        collection.deleteOne(Filters.eq("like", 200));
        printDocuments(collection);

        collection.deleteMany(Filters.eq("like", 200));


        //获取集合数据



    }

    private static void printDocuments(MongoCollection<Document> collection) {
        final FindIterable<Document> documents1 = collection.find();
        final MongoCursor<Document> iterator = documents1.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("\n");
    }

}
