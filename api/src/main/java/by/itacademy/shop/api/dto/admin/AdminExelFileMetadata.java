package by.itacademy.shop.api.dto.admin;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminExelFileMetadata {
    private Long shortDescriptionColumn;
    private Long barcodeColumn;
    private Long quantityColumn;
    private Long priceColumn;
    private Long photoColumn;
}
