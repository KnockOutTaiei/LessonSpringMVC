/*
 *履歴検索時に使用
 *検索に使う日時が注文日時なのか期限なのか支払い確認日時なのか判別するためのえにゅむれーた
 */
package rl.knockout.taiei.model;

public enum DateKind{
	ORDER,LIMIT,CONFIRM,NONE
}