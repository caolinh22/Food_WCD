package entity;


import java.util.Date;

public class Food {
    private String id;
    private String fullName;
    private String categoryId;
    private String description;
    private String image;
    private String price;
    private Date createdAt;
    private Date updatedAt;
    private String status;

    public Food() {
    }

    public Food(String id) {
        this.id = id;
    }

    public Food(String id, String fullName, String categoryId, String description, String image, String price, Date createdAt, Date updatedAt, String status) {
        this.id = id;
        this.fullName = fullName;
        this.categoryId = categoryId;
        this.description = description;
        this.image = image;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
