package com.example.education.services.kafka;

import com.example.education.dto.productcart.ProductCartKafkaDTO;
import com.example.education.dto.productcart.ProductCartReadDTO;
import com.example.education.services.productcart.ProductCartService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SendProductsServiceImpl implements SendProductsService {
    private final KafkaTemplate<String, ProductCartKafkaDTO> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final ProductCartService productCartService;

    @SneakyThrows
    @Override
    public String sendProducts(UUID cartId) {
        List<ProductCartReadDTO> products = productCartService.findAllProductCartByCartId(cartId);
        List<ProductCartKafkaDTO> productDTOList = products.stream()
                .map(product -> new ProductCartKafkaDTO(
                        product.getId(),
                        product.getProductid().getId(),
                        product.getProductid().getName(),
                        product.getProductid().getPrice(),
                        product.getProductid().getDetails(),
                        product.getProductid().getImageURL(),
                        product.getCartid().getId(),
                        product.getQuantity()
                ))
                .toList();

        for (ProductCartKafkaDTO dto : productDTOList) {
            kafkaTemplate.send("cook-created-events-topic", cartId.toString(), dto);
        }

        LOGGER.info("Return: {}", productDTOList);
        return productDTOList.toString();
    }
}
