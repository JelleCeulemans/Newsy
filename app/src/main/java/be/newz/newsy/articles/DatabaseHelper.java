package be.newz.newsy.articles;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import be.newz.newsy.articles.Article;
import be.newz.newsy.preferences.Preference;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 3;
    // Database Name
    private static final String DATABASE_NAME = "newsy";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_ARTICLE =
                "CREATE TABLE article (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT, " +
                "url TEXT, " +
                "published TEXT," +
                "source TEXT, " +
                "sourceUrl TEXT)";
        db.execSQL(CREATE_TABLE_ARTICLE);

        String CREATE_TABLE_PREFERENCES =
                "CREATE TABLE preference (" +
                        "country TEXT, " +
                        "language TEXT, "+
                        "position INTEGER)";
        db.execSQL(CREATE_TABLE_PREFERENCES);


        ContentValues values = new ContentValues();
        values.put("country", "be");
        values.put("language", "nl");
        values.put("position", 0);

        db.insert("preference", null, values);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS article");
        db.execSQL("DROP TABLE IF EXISTS preference");

        onCreate(db);
    }
    public List<Article> getArticles() {
        List<Article> list = new ArrayList<Article>();

        String selectQuery = "SELECT * FROM article";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Article article = new Article(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4), cursor.getString(5));
                list.add(article);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
    public boolean insertArticle(Article article) {
        boolean check = checkArticle(article);
        if (check == false) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("title", article.getTitle());
            values.put("url", article.getUrl());
            values.put("published", article.getPublished().toString());
            values.put("source", article.getSource());
            values.put("sourceUrl", article.getSourceUrl());

            long id = db.insert("article", null, values);
            db.close();
            return true;
        }
        return false;
    }

    public boolean checkArticle(Article article){
        List<Article> articles = getArticles();
        for (Article a: articles) {
            if (article.getUrl().equals(a.getUrl()) && article.getTitle().equals(a.getTitle()) && article.getSource().equals(a.getSource())) {
                return true;
            }
        }
        return false;
    }

    public void deleteArticle(Article article) {
        List<Article> list = new ArrayList<Article>();

        String selectQuery = "SELECT * FROM article";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Article a = new Article(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3), cursor.getString(4),cursor.getString(5));
                list.add(a);
            } while (cursor.moveToNext());
        }
        cursor.close();

        for (Article art: list) {
            if (art.getTitle().equals(article.getTitle()) && art.getUrl().equals(article.getUrl())) {
                String deleteQurery = "DELETE FROM article WHERE id = " + art.getId();
                db = this.getWritableDatabase();
                db.execSQL(deleteQurery);
            }
        }
        db.close();
    }

    public void clearPreferences() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from preference");
    }

    public long insertPreference(Preference preference) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("country", preference.getCountry());
        values.put("language", preference.getLanguage());
        values.put("position", preference.getPosition());

        long id = db.insert("preference", null, values);
        db.close();
        return id;
    }

    public Preference getPreference() {
        List<Preference> list = new ArrayList<Preference>();

        String selectQuery = "SELECT * FROM preference";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Preference preference = new Preference(cursor.getString(0), cursor.getString(1), cursor.getInt(2));
                list.add(preference);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list.get(0);
    }
}
