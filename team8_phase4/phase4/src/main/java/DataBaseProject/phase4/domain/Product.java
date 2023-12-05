package DataBaseProject.phase4.domain;

public class Product {
    Long productId;
    Long productCategoryId;
    String brandUserId;
    String productName;
    Long productPrice;
    String productDesc;

    String productPhotoImage;

    public Product(Long productId, Long productCategoryId, String brandUserId, String productName, Long productPrice,
            String productDesc) {
        this.productId = productId;
        this.productCategoryId = productCategoryId;
        this.brandUserId = brandUserId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDesc = productDesc;
    }

    public Product(Long productId, Long productCategoryId, String brandUserId, String productName, Long productPrice,
            String productDesc, String productPhotoImage) {
        this.productId = productId;
        this.productCategoryId = productCategoryId;
        this.brandUserId = brandUserId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDesc = productDesc;
        this.productPhotoImage = productPhotoImage;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getBrandUserId() {
        return brandUserId;
    }

    public void setBrandUserId(String brandUserId) {
        this.brandUserId = brandUserId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductPhotoImage() {
        return productPhotoImage;
    }
    public void setProductPhotoImage(String productPhotoImage) {
        this.productPhotoImage = productPhotoImage;
    }

}
