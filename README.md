
# Easy Shop

The EasyShop Backend API is a Spring Boot-based application designed to power the backend for an e-commerce website. This project is the second version of the EasyShop platform, extending its functionality to provide a robust and feature-rich API for handling products, categories, shopping carts, user profiles, and orders. The database is powered by MySQL, ensuring reliable data storage and management.

This project is part of a capstone exercise to develop, test, and integrate API endpoints for a fully functional e-commerce system.


## Authors

- [@sharianakram](https://www.github.com/sharianakram)


## Screenshots

![Screenshot1](https://github.com/YearUp-sharianakram/EasyShop/blob/main/ScreenshotsForC3/img1.png)

Users reported incorrect results when using the product search functionality, particularly when filtering by price range. Upon investigation, it was discovered that the SQL query responsible for processing the search logic contained a critical error. Specifically, the minimum price (minPrice) parameter was mistakenly treated as the maximum price, and the query did not include any handling for the maximum price (maxPrice) parameter. This caused the search results to return incorrect or incomplete products when users applied price filters. The issue has been resolved by correcting the SQL query to properly compare product prices against both the minPrice and maxPrice parameters, ensuring accurate filtering of results based on the intended price range. This fix improves the reliability and accuracy of the product search functionality.


![Screenshot2](https://github.com/YearUp-sharianakram/EasyShop/blob/main/ScreenshotsForC3/img2.png)

Users reported product duplication issues, where the same product appeared multiple times in the database with slight variations in attributes such as price or description. Upon investigation, it was discovered that the update functionality was incorrectly implemented. Instead of updating an existing product in the database, each update request created a new product entry. This resulted in multiple entries for the same product when editing details like price or description. The issue has been resolved by ensuring the update functionality correctly identifies and modifies the existing product in the database rather than creating a new entry. This fix eliminates duplicate products and ensures accurate updates.

## Interesting Piece of Code
![Screenshot3](https://github.com/YearUp-sharianakram/EasyShop/blob/main/ScreenshotsForC3/img3.png)

![Screenshot4](https://github.com/YearUp-sharianakram/EasyShop/blob/main/ScreenshotsForC3/img4.png)

The CategoriesController uses the @RequestMapping("/categories") annotation at the class level to simplify endpoint definitions. This eliminates the need to explicitly include the /categories prefix in each method's mapping. For example, instead of defining @GetMapping("/categories"), the method can simply use @GetMapping. This approach reduces redundancy, improves code readability, and ensures consistent endpoint structure for the controller.