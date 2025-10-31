import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Contact {
    
    private String id;
    private String name;
    private String phone;
    private String email;
    private String category;
    private String notes;
    private boolean isPriority;
    private LocalDateTime timestamp;
    
    public Contact(String name, String phone, String email, String category) {
        this.id = "ID" + System.currentTimeMillis();
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.category = category;
        this.notes = "";
        this.isPriority = false;
        this.timestamp = LocalDateTime.now();
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getCategory() {
        return category;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public boolean isPriority() {
        return isPriority;
    }
    
    public void setName(String name) {
        this.name = name;
        this.timestamp = LocalDateTime.now();
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
        this.timestamp = LocalDateTime.now();
    }
    
    public void setEmail(String email) {
        this.email = email;
        this.timestamp = LocalDateTime.now();
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public void setPriority(boolean priority) {
        this.isPriority = priority;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    public String toDisplay() {
        String priority = isPriority ? "[*]" : "[ ]";
        return String.format("%s %-20s | %-12s | %-25s | %s", 
            priority, name, phone, email, category);
    }
    
    public String toDetailedView() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n");
        sb.append("Phone: ").append(phone).append("\n");
        sb.append("Email: ").append(email).append("\n");
        sb.append("Category: ").append(category).append("\n");
        sb.append("Priority: ").append(isPriority ? "Yes" : "No").append("\n");
        if (!notes.isEmpty()) {
            sb.append("Notes: ").append(notes).append("\n");
        }
        sb.append("ID: ").append(id).append("\n");
        sb.append("Last Updated: ").append(timestamp.format(fmt));
        return sb.toString();
    }
    
    public String toFileString() {
        DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return id + "|" + name + "|" + phone + "|" + email + "|" + 
               category + "|" + notes + "|" + isPriority + "|" + timestamp.format(fmt);
    }
    
    public static Contact fromFileString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length < 8) return null;
        
        Contact c = new Contact(parts[1], parts[2], parts[3], parts[4]);
        c.setId(parts[0]);
        c.setNotes(parts[5]);
        c.setPriority(Boolean.parseBoolean(parts[6]));
        c.setTimestamp(LocalDateTime.parse(parts[7], DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return c;
    }
    
    public boolean matches(String query) {
        String q = query.toLowerCase();
        return name.toLowerCase().contains(q) || 
               phone.contains(q) || 
               email.toLowerCase().contains(q) ||
               category.toLowerCase().contains(q) ||
               notes.toLowerCase().contains(q);
    }
}