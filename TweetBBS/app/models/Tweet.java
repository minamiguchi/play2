package models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

import com.avaje.ebean.Page;

@Entity
public class Tweet extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PAGING_SIZE = 10;
	private static final int MAX_MSG_LENGTH = 140;

	@Id
	public Long id;
	public String content;
	public String postDate;

	public static Finder<Long, Tweet> find = new Finder(Long.class, Tweet.class);

	public String validate() {
		if (content != null && content.length() > MAX_MSG_LENGTH) {
			return "Please input under " + MAX_MSG_LENGTH + "characters.";
		}
		return null;
	}

	/**
	 * 全件取得
	 * 
	 * @return つぶやき全件
	 */
	public static List<Tweet> all() {
		return find.all();
	}

	public static Page<Tweet> page(int pageNo) {
		return find.where().orderBy("post_date ASC")
				.findPagingList(PAGING_SIZE).getPage(pageNo);
	}

	/**
	 * つぶやき新規作成
	 * 
	 * @param tweet
	 *            投稿するつぶやき
	 */
	public static void create(Tweet tweet) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm");
		tweet.postDate = sdf.format(Calendar.getInstance().getTime());
		tweet.save();
	}

	/**
	 * つぶやき削除
	 * 
	 * @param id
	 *            削除するつぶやきID
	 */
	public static void delete(Long id) {
		find.ref(id).delete();
	}
}
