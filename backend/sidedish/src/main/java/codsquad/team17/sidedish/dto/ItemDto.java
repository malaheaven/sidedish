package codsquad.team17.sidedish.dto;

import codsquad.team17.sidedish.domain.Image;
import codsquad.team17.sidedish.domain.Item;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ItemDto {
    private Long item_id;
    private String title;
    private String description;

    private BigDecimal n_price;
    private BigDecimal s_price;

    private List<String> badge;
    private String image;

    public ItemDto() {
    }

    public ItemDto(Item entity, Image image) {
        this.item_id = entity.getItemId();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.n_price = entity.getnPrice();
        this.s_price = entity.getsPrice();
        this.badge = parseBadge(entity.getBadge());
        this.image = image.getUrl();
    }

    private List<String> parseBadge(String badge) {
        return Arrays.asList(badge.split(", "));
    }

    public Long getItem_id() {
        return item_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getN_price() {
        return n_price;
    }

    public BigDecimal getS_price() {
        return s_price;
    }

    public List<String> getBadge() {
        return badge;
    }

    public String getImage() {
        return image;
    }
}
