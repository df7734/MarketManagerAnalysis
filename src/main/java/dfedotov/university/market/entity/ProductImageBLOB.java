package dfedotov.university.market.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_images_blob")
public class ProductImageBLOB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    @Lob
    @Column(name = "image_data", nullable = false)
    private byte[] imageData;

    @Column(name = "image_type", nullable = false)
    private String imageType;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
