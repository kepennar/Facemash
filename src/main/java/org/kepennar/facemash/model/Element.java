package org.kepennar.facemash.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Element {

    @Id
    private String id;
    private String family;
    private String imgUrl;
    private String name;
    private String description;
    private int score;
    private int played;

    @Transient
    private transient int totalPLayed;

    public Element() {
        super();
    }

    public Element(String name) {
		super();
		this.name = name;
	}

	public Element(String family, String name, String imgUrl, String description) {
        this.family = family;
        this.name = name;
        this.imgUrl = imgUrl;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getPlayed() {
		return played;
	}

	public void setPlayed(int played) {
		this.played = played;
	}

	public void play() {
		this.setPlayed(this.getPlayed() + 1);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Name: ").append(this.name)
			.append(", description: ").append(this.description)
			.append(", imgUrl: ").append(this.imgUrl);
		return sb.toString();
	}

	public int getTotalPLayed() {
		return totalPLayed;
	}

	public void setTotalPLayed(int totalPLayed) {
		this.totalPLayed = totalPLayed;
	}
    
	
	
}
