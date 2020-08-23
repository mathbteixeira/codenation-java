package br.com.codenation.service;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

public class OrderServiceImpl implements OrderService {

	private ProductRepository productRepository = new ProductRepositoryImpl();

	/**
	 * Calculate the sum of all OrderItems
	 */
	@Override
	public Double calculateOrderValue(List<OrderItem> items) {
		return items.stream ().filter (item->
				productRepository.findById (item.getProductId ()).isPresent ()
		).mapToDouble (item -> {
			Product product = productRepository.findById (item.getProductId ()).get ();
			return product.getValue () * (
					item.getQuantity () * (
							product.getIsSale () ? .8 : 1));
		}).sum ();
	}

	/**
	 * Map from idProduct List to Product Set
	 */
	@Override
	public Set<Product> findProductsById(List<Long> ids) {
		return productRepository
				.findAll ().stream ()
				.filter (product -> ids.contains (product.getId ()))
				.collect (Collectors.toSet ());
	}

	/**
	 * Calculate the sum of all Orders(List<OrderIten>)
	 */
	@Override
	public Double calculateMultipleOrders(List<List<OrderItem>> orders) {
		return orders
				.stream ()
				.mapToDouble (this::calculateOrderValue)
				.sum ();
	}

	/**
	 * Group products using isSale attribute as the map key
	 */
	@Override
	public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {
		return findProductsById (productIds)
				.stream ()
				.collect (Collectors.groupingBy (Product::getIsSale));
	}

}