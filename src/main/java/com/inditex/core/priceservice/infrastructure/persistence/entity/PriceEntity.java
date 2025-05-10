package com.inditex.core.priceservice.infrastructure.persistence.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Entity class representing a price record in the "prices" table.
 * <p>
 * This class maps the price data stored in the database to a Java object. It includes all the fields that are stored
 * in the "prices" table, such as the brand ID, product ID, start and end dates, price list, priority, price, and currency.
 * It is annotated with JPA annotations to define how the entity is mapped to the database.
 * </p>
 *
 * @author Imanol Villalba Rodríguez
 * @since 2025-05-10
 */
@Entity
@Table(name = "prices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceEntity {

    /**
     * The unique identifier of the price record.
     * <p>
     * This field is mapped to the "id" column in the "prices" table and is automatically generated using the
     * {@link GenerationType#IDENTITY} strategy.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * The ID of the brand associated with the price.
     * <p>
     * This field is mapped to the "brand_id" column in the "prices" table.
     * </p>
     */
    @Column(name = "brand_id")
    private Integer brandId;

    /**
     * The date and time when the price becomes valid.
     * <p>
     * This field is mapped to the "start_date" column in the "prices" table.
     * </p>
     */
    @Column(name = "start_date")
    private LocalDateTime startDate;

    /**
     * The date and time when the price expires.
     * <p>
     * This field is mapped to the "end_date" column in the "prices" table.
     * </p>
     */
    @Column(name = "end_date")
    private LocalDateTime endDate;

    /**
     * The price list ID associated with this price.
     * <p>
     * This field is mapped to the "price_list" column in the "prices" table.
     * </p>
     */
    @Column(name = "price_list")
    private Integer priceList;

    /**
     * The ID of the product associated with the price.
     * <p>
     * This field is mapped to the "product_id" column in the "prices" table.
     * </p>
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * The priority of the price in case multiple prices are available for the same product.
     * <p>
     * This field is mapped to the "priority" column in the "prices" table.
     * </p>
     */
    @Column(name = "priority")
    private Integer priority;

    /**
     * The price value for the product.
     * <p>
     * This field is mapped to the "price" column in the "prices" table.
     * </p>
     */
    @Column(name = "price")
    private Double price;

    /**
     * The currency in which the price is represented (e.g., EUR, USD).
     * <p>
     * This field is mapped to the "curr" column in the "prices" table.
     * </p>
     */
    @Column(name = "curr")
    private String currency;
}