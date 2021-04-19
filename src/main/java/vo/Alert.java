package vo;

public class Alert {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "id=" + id +
                '}';
    }
}
