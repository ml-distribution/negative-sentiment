package zx.soft.negative.sentiment.domain;

import java.util.List;

public class EmotionDictionary {

	// 程度副词
	private List<String> insufficiently;
	private List<String> ish;
	private List<String> more;
	private List<String> most;
	private List<String> over;
	private List<String> very;
	// 负性词表
	private List<String> catewords;
	// 否定副词
	private List<String> inverse;

	@Override
	public String toString() {
		return "EmotionDictionary:[insufficiently=" + insufficiently + ",ish=" + ish + ",more=" + more + ",most="
				+ most + ",over=" + over + ",very=" + very + ",inverse=" + inverse + ",negwords=" + catewords + "]";
	}

	public List<String> getInsufficiently() {
		return insufficiently;
	}

	public void setInsufficiently(List<String> insufficiently) {
		this.insufficiently = insufficiently;
	}

	public List<String> getIsh() {
		return ish;
	}

	public void setIsh(List<String> ish) {
		this.ish = ish;
	}

	public List<String> getMore() {
		return more;
	}

	public void setMore(List<String> more) {
		this.more = more;
	}

	public List<String> getMost() {
		return most;
	}

	public void setMost(List<String> most) {
		this.most = most;
	}

	public List<String> getOver() {
		return over;
	}

	public void setOver(List<String> over) {
		this.over = over;
	}

	public List<String> getVery() {
		return very;
	}

	public void setVery(List<String> very) {
		this.very = very;
	}

	public List<String> getCatewords() {
		return catewords;
	}

	public void setCatewords(List<String> catewords) {
		this.catewords = catewords;
	}

	public List<String> getInverse() {
		return inverse;
	}

	public void setInverse(List<String> inverse) {
		this.inverse = inverse;
	}

}
