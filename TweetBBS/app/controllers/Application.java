package controllers;

import models.Tweet;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {
	static Form<Tweet> tweetForm = Form.form(Tweet.class);

	/**
	 * デフォルト
	 * 
	 * @return
	 */
	public static Result index() {
		return redirect(routes.Application.tweets(0));
	}

	/**
	 * 一覧
	 * 
	 * @return
	 */
	public static Result tweets(int page) {
		return ok(views.html.index.render(Tweet.page(page), tweetForm));
	}

	/**
	 * 投稿アクション
	 * 
	 * @return
	 */
	public static Result newTweet() {
		Form<Tweet> filledForm = tweetForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(views.html.index.render(Tweet.page(0), tweetForm));
		} else {
			Tweet.create(filledForm.get());
			return redirect(routes.Application.tweets(0));
		}
	}

	/**
	 * 削除アクション
	 * 
	 * @param id
	 * @return
	 */
	public static Result deleteTweet(Long id) {
		Tweet.delete(id);
		return redirect(routes.Application.tweets(0));
	}
}
