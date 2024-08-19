package com.example.goldmarket.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Type is required")
    @Size(max = 50, message = "Type cannot exceed 50 characters")
    private String type;

    @NotBlank(message = "Color is required")
    @Size(max = 30, message = "Color cannot exceed 30 characters")
    private String color;

    @NotNull(message = "Karat is required")
    @Enumerated(EnumType.STRING)
    private GoldKarat karat;

    @DecimalMin(value = "0.0", inclusive = true, message = "Weight must be zero or positive")
    private double weight;

    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be zero or positive")
    private double price;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    @NotNull(message = "Seller is required")
    private Seller seller;

    @NotNull(message = "Stone type is required")
    @Enumerated(EnumType.STRING)
    private StoneType stoneType;

    @NotNull(message = "Stone color is required")
    @Enumerated(EnumType.STRING)
    private StoneColor stoneColor;

    @NotNull(message = "Stone shape is required")
    @Enumerated(EnumType.STRING)
    private StoneShape stoneShape;

    @DecimalMin(value = "0.0", inclusive = true, message = "Stone weight must be zero or positive")
    private double stoneWeight;

    @OneToMany
    private List<Rating> ratings;

    private boolean inStock;

     public enum StoneColor {
        WHITE,
        YELLOW,
        ROSE,
        RED,
        BLUE,
        GREEN,
        PINK,
        PURPLE;
         @JsonCreator
         public static StoneColor addString(String key) {
             return key == null ? null : StoneColor.valueOf(key.toUpperCase());
         }
    }

    public enum StoneType {
        DIAMOND,
        RUBY,
        EMERALD,
        SAPPHIRE,
        AMETHYST,
        TOPAZ,
        PEARL,
        OPAL,
        OTHER;
        @JsonCreator
        public static StoneType getString(String key) {
            return key == null ? null : StoneType.valueOf(key.toUpperCase());
        }
    }


    public enum StoneShape {
        ROUND,
        OVAL,
        OCTAGON,
        MARQUISE,
        BAGUETTE,
        HEART,
        PEAR,
        SQUARE,
        PRINCESS;
        @JsonCreator
        public static StoneShape fromString(String key) {
            return key == null ? null : StoneShape.valueOf(key.toUpperCase());
        }
    }

    public enum GoldKarat {
        K14(14, 58.3),
        K15(15, 62.5),
        K18(18, 75.0),
        K20(20, 83.3),
        K21(21, 87.5),
        K22(22, 91.7),
        K24(24, 99.9);

        private final int karat;
        private final double purity;

        GoldKarat(int karat, double purity) {
            this.karat = karat;
            this.purity = purity;
        }

        public int getKarat() {
            return karat;
        }

        public double getPurity() {
            return purity;
        }

    }

}




