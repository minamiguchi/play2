package models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import play.db.ebean.Model;
import play.data.validation.*;

import javax.persistence.*;

@Entity
public class Tweet extends Model {
	@Id
	public Long id;
	
	@Constraints.Required
	public String content;
	
	public String postDate;
	
	public String validate() {
		if(content != null && content.length() > 140) {
			return "Please input under 140characters.";
		}
        return null;
    }
	
	public static Finder<Long,Tweet> find = new Finder(
		    Long.class, Tweet.class
		  );
	
	/**
	 * 全件取得
	 * @return つぶやき全件
	 */
	public static List<Tweet> all() {
		return find.all();
	}
	
	/**
	 * つぶやき新規作成
	 * @param tweet 投稿するつぶやき
	 */
	public static void create(Tweet tweet) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm");
        tweet.postDate = sdf.format(Calendar.getInstance().getTime());
		tweet.save();
	}
	
	/**
	 * つぶやき削除
	 * @param id 削除するつぶやきID
	 */
	public static void delete(Long id) {
		find.ref(id).delete();
	}
}
