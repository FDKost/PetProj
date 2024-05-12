package com.example.Education.RestControllers;

import com.example.Education.Cart;
import com.example.Education.DAO.CartDao;
import com.example.Education.DAO.ProductCartDao;
import com.example.Education.DAO.ProductDao;
import com.example.Education.DAO.UserDao;
import com.example.Education.ProductCart;
import com.example.Education.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@AllArgsConstructor
public class ProductCartController {
    private final ProductCartDao dao;
    private final ProductDao productDao;
    private final CartDao cartDao;
    private final UserDao userDao;

    @PostMapping("/api/create_productCart")
    public ModelAndView createProductCart(@RequestParam Long product_id, ProductCart productCart, @AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        User user = userDao.getUserByLogin(username);
        Cart cart = cartDao.getCartByUserId(user.getId());
        productCart.setId_cart(cart.getId_cart());
        productCart.setProduct_id(product_id);
        /*List<Product> products = productDao.getAllProducts();

        // Цикл для поиска продукта по имени
        Product selectedProduct = null;
        for (Product product : products) {
            if (product.getName().equals(name)) {
                selectedProduct = product;
                break; // Прерываем цикл, если найден нужный продукт
            }
        }

        // Если продукт найден, добавляем его в корзину
        if (selectedProduct != null) {
            // Добавляем информацию о продукте в объект productCart
            productCart.setProduct_id(selectedProduct.getProduct_id());
            // Создаем запись в корзине
            return dao.createProductCart(productCart);
        } else {
            System.out.println("fucking null");
            return null;
        }*/
        dao.createProductCart(productCart);
        String orderUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/order").toUriString();
        return new ModelAndView("redirect:" + orderUrl);
    }
    @GetMapping("/api/getProductCart")
    public ProductCart readProductCart(@RequestParam long cart_item_id){
        return dao.getProductCartById(cart_item_id);
    }
    @PutMapping("/api/editProductCart")
    public void editProductCart(@RequestBody ProductCart productCart){
        dao.editProductCart(productCart);
    }
    @DeleteMapping("/api/deleteProductCart")
    public void deleteProductCart(@RequestParam long cart_item_id){
        dao.deleteProductCart(cart_item_id);
    }
}
