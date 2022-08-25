package treinamentorestassured.jsonObjects.pet;

public class Pet {
    private int id = 0;
    private Category CategoryObject = new Category();
    private String name = "doggie";
    private String[] photoUrls = new String[]{"string"};
    private Tag[] tags = new Tag[]{new Tag()};
    private String status = "available";


    // Getter Methods

    public int getId() {
        return id;
    }

    public Category getCategory() {
        return CategoryObject;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    // Setter Methods

    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(Category categoryObject) {
        this.CategoryObject = categoryObject;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhotoUrls(String[] photoUrls) {
        this.photoUrls = photoUrls;
    }

    public void setTags(Tag[] tags) {
        this.tags = tags;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
