package org.example.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Products")
public class Product {
    @Id()
    @GeneratedValue(generator = "products")
    @TableGenerator(name = "products", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="Products",
            initialValue=1, allocationSize=1)
    @Column(name = "productid")
    private Long productId;

    @Column(name = "productname")
    private String productName;

    @ManyToOne
    @JoinColumn(name = "categoryid", referencedColumnName = "categoryid")
    private Category category;

    @Column(name = "quantityperunit")
    private String quantityPerUnit;

    @Column(name = "unitprice")
    private Double unitPrice;

    @Column(name = "unitsinstock")
    private Long unitsInStock;

    @Column(name = "unitsonorder")
    private Long unitsOnOrder;

    @Column(name = "reorderlevel")
    private Long reorderLevel;

    @Column(name = "discontinued")
    private String discontinued;
}
