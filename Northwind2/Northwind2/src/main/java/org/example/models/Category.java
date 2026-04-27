package org.example.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "Categories")
@Data
public class Category {
    @Id()
    @GeneratedValue(generator = "categories")
    @TableGenerator(name = "categories", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="Categories",
            initialValue=1, allocationSize=1)

    @Column(name = "categoryid")
    private Long categoryId;

    @Column(name = "categoryname")
    private String categoryName;

    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
